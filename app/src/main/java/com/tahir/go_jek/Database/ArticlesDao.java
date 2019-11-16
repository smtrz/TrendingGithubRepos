package com.tahir.go_jek.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tahir.go_jek.Models.BaseTrending;

import java.util.List;

@Dao
public interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(List<BaseTrending> items);

    @Query("SELECT * from articles")
    LiveData<List<BaseTrending>> getallItems();

    @Query("DELETE FROM articles")
    void delete();
}

