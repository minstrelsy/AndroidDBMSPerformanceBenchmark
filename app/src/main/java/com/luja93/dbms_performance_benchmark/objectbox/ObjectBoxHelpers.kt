package com.luja93.dbms_performance_benchmark.objectbox

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luja93.dbms_performance_benchmark.BaseHelpers
import com.luja93.dbms_performance_benchmark.ListFloatDeserializer
import com.luja93.dbms_performance_benchmark.R
import io.objectbox.BoxStore


/**
 * \brief Helper class for initializing, loading and performing ObjectBox queries.
 * \details
 *
 * @author  Luka Leopoldović
 * @version 1.0
 * \date 14/04/2020
 * \copyright
 *     This code and information is provided "as is" without warranty of
 *     any kind, either expressed or implied, including but not limited to
 *     the implied warranties of merchantability and/or fitness for a
 *     particular purpose.
 */
object ObjectBoxHelpers : BaseHelpers<Vector_ObjectBox, BoxStore>() {

    //region SETUP
    override fun buildDb(context: Context): BoxStore {
        ObjectBoxDB.init(context)

        return ObjectBoxDB.boxStore
    }

    override fun loadVectors(context: Context) {
        val bufferReader = context.resources.openRawResource(R.raw.data).bufferedReader()

        var vectorString = bufferReader.readLine()

        val gson = GsonBuilder().registerTypeAdapter(
            List::class.java,
            ListFloatDeserializer()
        ).create()

        while (vectorString != null) {
            val vector = gson.fromJson<Vector_ObjectBox>(vectorString, Vector_ObjectBox::class.java)
            vectors.add(vector)
            vectorString = bufferReader.readLine()
        }

        isVectorsLoaded = true

        bufferReader.close()
    }
    //endregion


    //region BENCHMARK HELPERS
    override fun insertVectors(db: BoxStore, vectors: List<Vector_ObjectBox>) {
        db.boxFor(Vector_ObjectBox::class.java).put(vectors)
    }

    override fun readVectors(db: BoxStore): List<Vector_ObjectBox> {
        return db.boxFor(Vector_ObjectBox::class.java).all
    }

    override fun updateVectors(db: BoxStore, vectors: List<Vector_ObjectBox>) {
        db.boxFor(Vector_ObjectBox::class.java).put(vectors)
    }

    override fun deleteVectors(db: BoxStore) {
        db.boxFor(Vector_ObjectBox::class.java).removeAll()
    }
    //endregion
}