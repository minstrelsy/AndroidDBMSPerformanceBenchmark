package com.luja93.dbms_performance_benchmark.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

/**
 * \brief Model used as the data holder for objects saved/read from the database.
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
@Entity
data class Vector_Room(
    @PrimaryKey
    @SerializedName("id")
    var id: Long = 0,
    @SerializedName("name")
    var name: String,
    @SerializedName("vector")
    var vector: List<Float>,
)