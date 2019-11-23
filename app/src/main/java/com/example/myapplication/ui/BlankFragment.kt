package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.data.Post
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

        val repo = SimpleRepository()

        repo.getAllPosts { posts ->
            textView3.text = posts[0].title
            textView4.text = posts[1].title
        }

        repo.getPostsByUserId(1) { posts ->
            textView3.text = posts[2].title
        }

        val post = Post(12, 1, "title", "body")
        repo.createNewPost(post) {
            if(it) {
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
            }
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
