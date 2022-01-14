package com.example.shirahotsu.tvseriesinfo;

import android.support.v4.app.Fragment;

public class FavoritesViewActivity extends FavoriteSingleFragment {


    @Override
    protected Fragment createFragment() {
        return new FavoriteRecyclerFragment().newInstance();
    }
}
