package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        button2.setOnClickListener {
//            val fragment = BlankFragment2()
//            val bundle = Bundle()
//            bundle.putString("someData", editText.text.toString())
//            fragment.arguments = bundle
//            fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, fragment)?.commit()
//        }
    }
}
