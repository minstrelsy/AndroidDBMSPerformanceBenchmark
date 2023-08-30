package com.luja93.dbms_performance_benchmark.room

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.luja93.dbms_performance_benchmark.BaseHelpers
import com.luja93.dbms_performance_benchmark.R

/**
 * \brief Helper class for initializing, loading and performing Room queries.
 * \details
 *
 * @author  Luka Leopoldović
 * @version 1.0
 * \date 23/03/2020
 * \copyright
 *     This code and information is provided "as is" without warranty of
 *     any kind, either expressed or implied, including but not limited to
 *     the implied warranties of merchantability and/or fitness for a
 *     particular purpose.
 */
object RoomHelpers : BaseHelpers<Vector_Room, RoomDB>() {

    //region SETUP
    override fun buildDb(context: Context): RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, "RoomDB")
            .allowMainThreadQueries()
            .build()
    }

    override fun loadVectors(context: Context) {
        val bufferReader = context.resources.openRawResource(R.raw.data).bufferedReader()

        var vectorString = bufferReader.readLine()

        while (vectorString != null) {
            val vector = Gson().fromJson<Vector_Room>(vectorString, Vector_Room::class.java)
            vectors.add(vector)
            vectorString = bufferReader.readLine()
        }

        isVectorsLoaded = true

        bufferReader.close()
    }
    //endregion


    //region BENCHMARK HELPERS
    override fun insertVectors(db: RoomDB, vectors: List<Vector_Room>) {
        db.vectorDao().insertVectors(vectors)
    }

    override fun readVectors(db: RoomDB): List<Vector_Room> {
        return db.vectorDao().readVectors()
    }

    override fun updateVectors(db: RoomDB, vectors: List<Vector_Room>) {
        db.vectorDao().updateVectors(vectors)
    }

    override fun deleteVectors(db: RoomDB) {
        db.vectorDao().deleteVectors()
    }
    //endregion
}