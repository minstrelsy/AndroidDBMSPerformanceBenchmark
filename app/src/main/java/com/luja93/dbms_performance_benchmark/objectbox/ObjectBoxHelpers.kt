package com.luja93.dbms_performance_benchmark.objectbox

import android.content.Context
import com.luja93.dbms_performance_benchmark.BaseHelpers
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
        loadVectorsData<List<Vector_ObjectBox>>(context)
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