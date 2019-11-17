package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(
            R.id.frameLayout,
            BlankFragment()
        ).commit()

        button3.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout,
                BlankFragment()
            ).commit()
        }

        button4.setOnClickListener {
            val fragment = BlankFragment2()
            val bundle = Bundle()
            bundle.putString("someData", "Ще не вмерла України\nНи слава, ни воля.\nЩе нам, браття українцi,\nУсмехнеться доля.")
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
        }
    }
}
