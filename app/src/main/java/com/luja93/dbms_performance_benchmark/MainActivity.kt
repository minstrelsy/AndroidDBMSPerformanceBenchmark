package com.luja93.dbms_performance_benchmark

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luja93.dbms_performance_benchmark.room.RoomHelpers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Change the helpers object to any of defined DBMS helpers to try out the implementations.
         */
        val helpers = RoomHelpers

        /**
         * The following flow will guide you through the implemented DBMS operations. Feel free to
         * run it to ensure everything works as intended. In that case, you will have to change the
         * <code>apply plugin: 'com.android.library'</code> line from build.gradle(app) to
         * <code>apply plugin: 'com.android.application'</code> and comment out the
         * <code>androidTestImplementation project(":app")</code> from build.gradle(DBMS-benchmark).
         */

        val startTime = System.currentTimeMillis()

        val db = helpers.buildDb(this)
        val buildTime = System.currentTimeMillis()

        helpers.loadCities(this)
        val cities = helpers.getCities(10000)

        val createTime = System.currentTimeMillis();
        Log.d(TAG, "Create time: " + (buildTime - startTime).toString())

        helpers.deleteCities(db)
        helpers.insertCities(db, cities)

        val insertTime = System.currentTimeMillis();
        Log.d(TAG, "Insert time: " + (insertTime - createTime).toString())

        val citiesFromDb = helpers.readCities(db)

        val readTime = System.currentTimeMillis();
        Log.d(TAG, "Read time: " + (readTime - insertTime).toString())

        val citiesUpdated = cities.map { it.name = it.name + "_updated"; it }
        helpers.updateCities(db, citiesUpdated)

        val updateTime = System.currentTimeMillis();
        Log.d(TAG, "Update time: " + (updateTime - readTime).toString())

        helpers.deleteCities(db)

        val deleteTime = System.currentTimeMillis();
        Log.d(TAG, "Delete time: " + (deleteTime - updateTime).toString())
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
