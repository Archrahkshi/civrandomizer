package com.archrahkshi.civrandomizer.data

import com.archrahkshi.civrandomizer.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class CivsRepository(override val coroutineContext: CoroutineContext = Dispatchers.IO) :
    CoroutineScope {
    fun insertCivAsync(civ: Civ) = async {
        App.db.civsDao().insertCiv(civ)
    }

    fun deleteCivAsync(civ: Civ) = async {
        App.db.civsDao().deleteCiv(civ)
    }

    fun getCivsAsync(option: String) = async {
        when (option) {
            "All" -> App.db.civsDao().getAll()
            "Vanilla" -> App.db.civsDao().getByAuthor("")
            "JFD" -> App.db.civsDao().getByAuthor("JFD")
            else -> App.db.civsDao().getAll()
        }
    }

    fun getNRandomCivsAsync(n: Int) = async {
        App.db.civsDao().getNRandom(n)
    }
}