package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.OurThread
import com.example.myapplication.data.SimpleAdapter
import com.example.myapplication.data.SimpleRepository
import kotlinx.android.synthetic.main.fragment_coroutines.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FragmentCoroutines(
    override val coroutineContext: CoroutineContext = Dispatchers.Default
) : Fragment(), CoroutineScope {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coroutines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = SimpleRepository()
        val adapter = SimpleAdapter()

        recyclerView.adapter = adapter   // Привязка
        repo.getAllPosts {
            adapter.addPosts(it)
        }

        OurThread("OneThread").start()
        val thread = OurThread("AnotherThread")
        thread.start()
        thread.join()
        Thread {
            repeat(5) {
                Log.wtf("From lambda", it.toString())
            }
        }.start()
        Log.wtf("mainThread", "Йоу")

        GlobalScope.launch {
            repeat(5) {
                Log.wtf("From coroutine", it.toString())
            }
        }

        launch {
            repeat(5) {
                Log.wtf("From CoroutineScope", it.toString())
            }
        }

//        Glide.with(this)
//            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ee/Emblem_of_Kazakhstan_latin.svg/250px-Emblem_of_Kazakhstan_latin.svg.png")
//            .apply(RequestOptions().circleCrop())
//            .into(imageView2)
    }
}
