package com.luja93.dbms_performance_benchmark

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.luja93.dbms_performance_benchmark.room.RoomHelpers
import java.util.Arrays


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

        helpers.loadVectors(this)
        val cities = helpers.getVectors(10000)

        val createTime = System.currentTimeMillis();
        Log.d(TAG, "Create time: " + (buildTime - startTime).toString())

        helpers.deleteVectors(db)
        helpers.insertVectors(db, cities)

        val insertTime = System.currentTimeMillis();
        Log.d(TAG, "Insert time: " + (insertTime - createTime).toString())

        val citiesFromDb = helpers.readVectors(db)

        val readTime = System.currentTimeMillis();
        Log.d(TAG, "Read time: " + (readTime - insertTime).toString())

        val citiesUpdated = cities.map { it.name = it.name + "_updated"; it }
        helpers.updateVectors(db, citiesUpdated)

        val updateTime = System.currentTimeMillis();
        Log.d(TAG, "Update time: " + (updateTime - readTime).toString())

        helpers.deleteVectors(db)

        val deleteTime = System.currentTimeMillis();
        Log.d(TAG, "Delete time: " + (deleteTime - updateTime).toString())

        val gson = Gson()
        val floatList = Arrays.asList(1.0f, 2.0f, 3.0f)
        val jsonOutput = gson.toJson(floatList)

        Log.d(TAG, "json output - " + jsonOutput)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
