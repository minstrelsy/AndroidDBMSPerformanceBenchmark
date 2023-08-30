package com.luja93.dbms_performance_benchmark.realm

import android.content.Context
import com.luja93.dbms_performance_benchmark.BaseHelpers
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
        loadVectorsData<List<Vector_Realm>>(context)
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