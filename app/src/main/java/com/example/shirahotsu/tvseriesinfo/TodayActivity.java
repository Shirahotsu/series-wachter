package com.example.shirahotsu.tvseriesinfo;

import android.support.v4.app.Fragment;

public class TodayActivity extends TodaySingleFragment {

    @Override
    protected Fragment createFragment() {
        return new TodayRecyclerFragment().newInstance();
    }

}
