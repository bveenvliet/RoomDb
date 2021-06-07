package com.example.roomdb

import android.app.Application
import androidx.room.Room
import com.example.roomdb.db.AppDatabase

class MainApplication : Application() {

    lateinit var myDb: AppDatabase

    override fun onCreate() {
        super.onCreate()
        myDb = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "myDatabase"
        ).build()
    }

    fun getMyDatabase(): AppDatabase {
        return myDb
    }

}