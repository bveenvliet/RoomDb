package com.example.roomdb

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.db.Location
import com.example.roomdb.db.LocationDao

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locationDao: LocationDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeDatabase()
        bindButtons()
        testDatabase()
    }

    private fun initializeDatabase() {
        locationDao = (applicationContext as MainApplication).getMyDatabase().locationDao()
    }

    private fun bindButtons() {
        with(binding) {
            btnSave.setOnClickListener {
                val epoch: Int = (System.currentTimeMillis() / 1000).toInt()
                val location = Location(
                    dt = epoch,
                    latitude = etLatitude.text.toString().toFloat(),
                    longitude = etLongitude.text.toString().toFloat(),
                    label = etLabel.text.toString()
                )
                Toast.makeText(binding.root.context, "Saved to database", Toast.LENGTH_SHORT).show()
                AsyncTask.execute {
                    locationDao.insertAll(location)
                }
            }
        }
    }

    private fun testDatabase() {
        // test getting all and print to logcat
        AsyncTask.execute {
            val allLocations: List<Location> = locationDao.getAll()
            println("allLocations")
            allLocations.forEach(::println)

            val workLocations: List<Location> = locationDao.getAll(label = "Work")
            println("work")
            workLocations.forEach(::println)

            val homeLocations: List<Location> = locationDao.getAll(label = "Home")
            println("home")
            homeLocations.forEach(::println)
        }
    }

}
