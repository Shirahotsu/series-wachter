package com.example.shirahotsu.tvseriesinfo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.shirahotsu.tvseriesinfo.dao.TVShowDao;
import com.example.shirahotsu.tvseriesinfo.model.TVShow;

@Database(entities = {TVShow.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TVShowDao tvShowDao();
}
