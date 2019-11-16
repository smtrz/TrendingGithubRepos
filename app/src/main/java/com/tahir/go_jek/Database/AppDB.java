package com.tahir.go_jek.Database;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tahir.go_jek.Models.BaseTrending;

@Database(entities = {BaseTrending.class}, version = 1,exportSchema = false)
public abstract class AppDB extends RoomDatabase {


   public abstract ArticlesDao articleDao();
}
