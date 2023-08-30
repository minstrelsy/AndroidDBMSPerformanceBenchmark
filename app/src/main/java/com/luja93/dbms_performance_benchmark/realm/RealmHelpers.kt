package com.luja93.dbms_performance_benchmark.realm

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luja93.dbms_performance_benchmark.BaseHelpers
import com.luja93.dbms_performance_benchmark.ListFloatDeserializer
import com.luja93.dbms_performance_benchmark.R
import com.luja93.dbms_performance_benchmark.room.RoomHelpers
import com.luja93.dbms_performance_benchmark.room.Vector_Room
import io.realm.Realm

/**
 * \brief Helper class for initializing, loading and performing Realm queries.
 * \details
 *
 * @author  Luka Leopoldović
 * @version 1.0
 * \date 13/04/2020
 * \copyright
 *     This code and information is provided "as is" without warranty of
 *     any kind, either expressed or implied, including but not limited to
 *     the implied warranties of merchantability and/or fitness for a
 *     particular purpose.
 */
object RealmHelpers : BaseHelpers<Vector_Realm, Realm>() {

    //region SETUP
    override fun buildDb(context: Context): Realm {
        Realm.init(context)

        return Realm.getDefaultInstance()
    }

    override fun loadVectors(context: Context) {
        val bufferReader = context.resources.openRawResource(R.raw.data).bufferedReader()

        var vectorString = bufferReader.readLine()

        val gson = GsonBuilder().registerTypeAdapter(
            List::class.java,
            ListFloatDeserializer()
        ).create()

        while (vectorString != null) {
            val vector = gson.fromJson<Vector_Realm>(vectorString, Vector_Realm::class.java)
            vectors.add(vector)
            vectorString = bufferReader.readLine()
        }

        isVectorsLoaded = true

        bufferReader.close()
    }
    //endregion


    //region BENCHMARK HELPERS
    override fun insertVectors(db: Realm, vectors: List<Vector_Realm>) {
        db.beginTransaction()
        db.insert(vectors)
        db.commitTransaction()
    }

    override fun readVectors(db: Realm): List<Vector_Realm> {
        return db.where(Vector_Realm::class.java).findAll()
    }

    override fun updateVectors(db: Realm, vectors: List<Vector_Realm>) {
        db.beginTransaction()
        db.insertOrUpdate(vectors)
        db.commitTransaction()
    }

    override fun deleteVectors(db: Realm) {
        db.beginTransaction()
        db.delete(Vector_Realm::class.java)
        db.commitTransaction()
    }
    //endregion
}