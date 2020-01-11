package com.example.myapplication.data

import com.example.myapplication.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class PeopleRepository(override val coroutineContext: CoroutineContext = Dispatchers.IO)
    : CoroutineScope {
    fun insertPerson(people: People) = async {
        App.db.peopleDao().insertPeople(people)
    }

    fun deletePerson(people: People) = async {
        App.db.peopleDao().deletePeople(people)
    }

    fun getPeople(option: String) = async {
        when(option) {
            "All" -> App.db.peopleDao().getAll()
            "Adults" -> App.db.peopleDao().getAllAdults()
            "Greater than 10" -> App.db.peopleDao().getAgeGreaterThan(10)
            "Range 15..25" -> App.db.peopleDao().getAgeBetween(15, 25)
            else -> App.db.peopleDao().getAll()
        }

    }
}