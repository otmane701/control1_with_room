package com.example.control1_with_room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Formation.class},version = 1)
public abstract class DB_Formation extends RoomDatabase {

    public abstract DAO_Formation getDao();
    public static DB_Formation INSTANCE;

    public static DB_Formation getInstance(Context context){
        if(INSTANCE==null){
            synchronized (DAO_Formation.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),DB_Formation.class,"DB").build();
                }
            }
        }
        return INSTANCE;
    }
}
