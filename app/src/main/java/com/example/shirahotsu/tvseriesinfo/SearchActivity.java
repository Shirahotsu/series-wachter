package com.example.shirahotsu.tvseriesinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shirahotsu.tvseriesinfo.model.TVShow;
import com.example.shirahotsu.tvseriesinfo.pojo.pojoTVShowResult.Nextepisode;
import com.example.shirahotsu.tvseriesinfo.pojo.pojoTVShowResult.Previousepisode;
import com.example.shirahotsu.tvseriesinfo.pojo.pojoTVShowResult.TvShowResult;
import com.example.shirahotsu.tvseriesinfo.requester.SearchRequester;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ListView listView;
    ListSearchAdapter listSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.listViewSearch);
        listSearchAdapter = new ListSearchAdapter(new ArrayList<TvShowResult>(), this);
        final Context context = getApplicationContext();
        listView.setAdapter(listSearchAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TvShowResult itemAtPosition = (TvShowResult) listView.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), ShowDetailsActivity.class);
                intent.putExtra("Show", getNewTVShow(itemAtPosition));
                startActivity(intent);

            }
        });
        final EditText searchText = findViewById(R.id.searchText);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                confirmHandler(searchText, context);
            }
        });
    }

    private void confirmHandler(EditText searchText, Context context) {
        String search = searchText.getText().toString();
        if (search.isEmpty()) {
            return;
        }
        SearchRequester.searchRequest(search, listView, context);
    }

    private TVShow getNewTVShow(TvShowResult showResult) {
        TVShow tvShow = new TVShow();
        tvShow.setName(showResult.getShow().getName());
        tvShow.setStatus(showResult.getShow().getStatus());
        tvShow.setPremiere(showResult.getShow().getPremiered());
        tvShow.setSummary(showResult.getShow().getSummary());
        tvShow.setSelfLink(String.valueOf(showResult.getShow().getLinks().getSelf().getHref()));
        tvShow.setImgLink(showResult.getShow().getImage().getMedium());
        Nextepisode nextepisode = showResult.getShow().getLinks().getNextepisode();
        if (nextepisode != null) {
            String href = nextepisode.getHref();
            tvShow.setNextEpisodeLink(href.substring(href.lastIndexOf("/") + 1));
        }
        Previousepisode previousepisode = showResult.getShow().getLinks().getPreviousepisode();
        if (previousepisode != null) {
            String href = previousepisode.getHref();
            tvShow.setLastEpisodeLink(href.substring(href.lastIndexOf("/") + 1));
        }
        return tvShow;
    }
}
