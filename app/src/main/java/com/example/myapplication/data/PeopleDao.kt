package com.example.myapplication.data

import androidx.room.*

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeople(people: People): Long

    @Delete
    fun deletePeople(people: People)

    @Query("SELECT * FROM people ORDER BY people.age DESC")
    fun getAll(): List<People>

    @Query("SELECT * FROM people WHERE people.age >= 18")
    fun getAllAdults(): List<People>

    @Query("SELECT * FROM people WHERE people.age > :age")
    fun getAgeGreaterThan(age: Int): List<People>

    @Query("SELECT * FROM people WHERE people.age BETWEEN :age1 AND :age2")
    fun getAgeBetween(age1: Int, age2: Int): List<People>

    @Query("SELECT AVG(people.age) FROM people")
    fun getAverageAge(): Double
}