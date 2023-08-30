package com.luja93.dbms_performance_benchmark.realm

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * \brief Model used as the data holder for objects saved/read from the database.
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
open class Vector_Realm : RealmObject() {
    @PrimaryKey
    @SerializedName("name")
    var name: String = ""
    @SerializedName("vector")
    var vector: RealmList<Float> = RealmList()
}