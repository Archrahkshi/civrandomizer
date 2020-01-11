package com.example.myapplication.data

import android.util.Log

class OurThread(private val threadName: String) : Thread() {

    override fun run() {
        repeat(10) {
            Log.wtf(threadName, it.toString())
        }
    }
}