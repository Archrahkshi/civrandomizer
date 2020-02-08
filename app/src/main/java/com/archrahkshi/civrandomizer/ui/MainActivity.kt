package com.archrahkshi.civrandomizer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.archrahkshi.civrandomizer.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar

        supportFragmentManager.beginTransaction().add(
            R.id.frameLayout,
            RandomizerFragment()
        ).commit()

        buttonFragmentRandomizer.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout,
                RandomizerFragment()
            ).commit()
        }

        buttonFragmentCivs.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout,
                CivsFragment()
            ).commit()
        }
    }
}
