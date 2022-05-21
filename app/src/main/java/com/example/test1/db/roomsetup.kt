package com.example.test1.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [RoomModel_users::class, RoomModel_cart::class, RoomModel_top::class, RoomModel_save::class], version = 2, exportSchema = false)
abstract class roomsetup: RoomDatabase(){
    abstract fun myDao(): Dao

    companion object {

        private var INSTANCE: roomsetup? = null


        @Synchronized
        fun getInstance(ctx: Context): roomsetup {
            if(INSTANCE == null)
                INSTANCE = Room.databaseBuilder(ctx.applicationContext, roomsetup::class.java,
                    "test_db")
                    .fallbackToDestructiveMigration()
                    .build()

            return INSTANCE!!

        }
    }

}