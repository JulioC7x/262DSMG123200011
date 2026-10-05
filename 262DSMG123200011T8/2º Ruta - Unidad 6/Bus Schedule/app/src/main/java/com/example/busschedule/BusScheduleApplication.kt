package com.example.busschedule

import android.app.Application
import androidx.room.Room
import com.example.busschedule.data.AppDatabase

class BusScheduleApplication : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "bus_schedule.db"
        )
            .createFromAsset("database/bus_schedule.db")
            .build()
    }

}