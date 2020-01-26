package com.archrahkshi.civrandomizer.ui


import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.archrahkshi.civrandomizer.R
import com.archrahkshi.civrandomizer.data.Civ
import com.archrahkshi.civrandomizer.data.CivsRepository
import kotlinx.android.synthetic.main.fragment_add_civ.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddCivFragment(
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_civ, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextLeader.text = SpannableStringBuilder(arguments?.getString("leader") ?: "")
        editTextNation.text = SpannableStringBuilder(arguments?.getString("nation") ?: "")
        editTextAuthor.text = SpannableStringBuilder(arguments?.getString("author") ?: "")

        val repo = CivsRepository()

        buttonDone.setOnClickListener {
            launch {
                repo.insertCivAsync(
                    Civ(
                        leader = editTextLeader.text.toString(),
                        nation = editTextNation.text.toString(),
                        author = editTextAuthor.text.toString(),
                        id = arguments?.getInt("id") ?: 0
                    )
                ).await()

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.frameLayout, CivsFragment())
                    ?.commit()
            }
        }
    }
}
