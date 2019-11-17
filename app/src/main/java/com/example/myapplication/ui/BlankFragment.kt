package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.data.SimpleRepository
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

        SimpleRepository().getAllPosts { posts ->
            textView3.text = posts[0].title
            textView4.text = posts[1].title
        }


//        button2.setOnClickListener {
//            val fragment = BlankFragment2()
//            val bundle = Bundle()
//            bundle.putString("someData", editText.text.toString())
//            fragment.arguments = bundle
//            fragmentManager?.beginTransaction()?.replace(R.id.frameLayout, fragment)?.commit()
//        }
    }
}
