package com.luja93.dbms_performance_benchmark.room

import android.content.Context
import androidx.room.Room
import com.luja93.dbms_performance_benchmark.BaseHelpers

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
        loadVectorsData<List<Vector_Room>>(context)
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