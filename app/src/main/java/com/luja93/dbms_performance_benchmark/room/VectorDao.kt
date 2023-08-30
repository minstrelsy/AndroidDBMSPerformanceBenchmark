package com.luja93.dbms_performance_benchmark.room

import androidx.room.*

/**
 * \brief [Vector_Room] data access object.
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
@Dao
interface VectorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVectors(vectors: List<Vector_Room>)

    @Query("SELECT * FROM Vector_Room")
    fun readVectors(): List<Vector_Room>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateVectors(vectors: List<Vector_Room>)

    @Query("DELETE FROM Vector_Room")
    fun deleteVectors()

}