package com.luja93.dbms_performance_benchmark

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

/**
 * \brief Base helper class for initializing, loading and performing database queries.
 * \details
 *
 * @author  Luka Leopoldović
 * @version 1.0
 * \date 05/04/2020
 * \copyright
 *     This code and information is provided "as is" without warranty of
 *     any kind, either expressed or implied, including but not limited to
 *     the implied warranties of merchantability and/or fitness for a
 *     particular purpose.
 */
abstract class BaseHelpers<Vector, Database> {

    //region CLASS PROPERTIES
    protected val gson: Gson = GsonBuilder().create()
    protected val vectors: MutableList<Vector> = mutableListOf()
    protected var isVectorsLoaded: Boolean = false
    //endregion


    //region PUBLIC FUNCTIONS
    /**
     * Returns a list of [Vector] with the size of [take].
     *
     * @param [take] The count of the elements in the return list. If 0 or nothing is passed,
     *  returns the entire list of cities acquired with [loadVectors].
     */
    fun getVectors(take: Int = 0): List<Vector> {
        return if (take < 1)
            vectors
        else
            vectors.take(take)
    }
    //endregion

    //region SETUP
    /**
     * Use this function to create and return an instance of [Database].
     */
    abstract fun buildDb(context: Context): Database

    /**
     * Use this function to load cities via [loadVectorsData].
     */
    abstract fun loadVectors(context: Context)
    //endregion


    //region CRUD
    /**
     * Use this function to inserts the given list of cities to the database.
     *
     * @param db [Database] created via [buildDb]
     * @param vectors List of [Vector] to save.
     */
    abstract fun insertVectors(db: Database, vectors: List<Vector>)

    /**
     * Use this function to read the cities from the database and return a list of [Vector].
     *
     * @param db [Database] created via [buildDb]
     */
    abstract fun readVectors(db: Database): List<Vector>

    /**
     * Use this function to updates the database with the given list of cities.
     *
     * @param db [Database] created via [buildDb]
     * @param vectors List of [Vector] to update the database.
     */
    abstract fun updateVectors(db: Database, vectors: List<Vector>)

    /**
     * Deletes all the rows from the [Vector] related database table.
     *
     * @param db [Database] created via [buildDb]
     */
    abstract fun deleteVectors(db: Database)
    //endregion
}