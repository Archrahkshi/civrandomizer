package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.WeatherRepository
import kotlinx.android.synthetic.main.fragment_search_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FragmentSearchWeather(
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = WeatherRepository()

        buttonSearch.setOnClickListener {
            launch {
                Log.wtf("SAsfsaf", "sdfsf")
                val weather = repo.getCurrentWeatherForCity("Moscow", "ru").await()
                Log.wtf("Погода", weather?.toString() ?: "None")
            }
            Log.wtf("asfd", "sadff")

            val fragment = BlankFragment4()
            val bundle = Bundle()
            bundle.putString("City", editTextCity.text.toString())
            bundle.putString("Code", editTextCode.text.toString())
            fragment.arguments = bundle


//            fragmentManager?.beginTransaction()
//                ?.replace(R.id.frameLayout, BlankFragment3())
//                ?.addToBackStack(null)
//                ?.commit()
        }
    }
}
