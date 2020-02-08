package com.archrahkshi.civrandomizer.data

import androidx.room.*

@Dao
interface CivsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCiv(civ: Civ)

    @Delete
    fun deleteCiv(civ: Civ)

    @Query("SELECT * FROM civ ORDER BY civ.leader")
    fun getAll(): List<Civ>

    @Query("""SELECT * FROM civ WHERE civ.author == :author OR civ.author == """"")
    fun getByAuthor(author: String): List<Civ>

    @Query("SELECT * FROM civ ORDER BY RANDOM() LIMIT :n")
    fun getNRandom(n: Int): List<Civ>
}