package com.archrahkshi.civrandomizer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Civ(
    val leader: String,
    val nation: String,
    val author: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)