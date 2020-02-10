package com.archrahkshi.civrandomizer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.archrahkshi.civrandomizer.R
import com.archrahkshi.civrandomizer.data.CivsAdapter
import com.archrahkshi.civrandomizer.data.CivsRepository
import kotlinx.android.synthetic.main.fragment_civs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CivsFragment(
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_civs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonAdd.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, AddCivFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        val repo = CivsRepository()
        launch {
            val adapter = CivsAdapter(
                civs = repo.getCivsAsync("All").await().toMutableList(),
                actionEdit = { civ ->
                    val bundle = Bundle()
                    bundle.let {
                        civ.apply {
                            it.putString("leader", leader)
                            it.putString("nation", nation)
                            it.putString("author", author)
                            it.putInt("id", id)
                        }
                    }
                    val fragmentEditCivs = AddCivFragment()
                    fragmentEditCivs.arguments = bundle
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.frameLayout, fragmentEditCivs)
                        ?.addToBackStack(null)
                        ?.commit()
                },
                actionDelete = {
                    repo.deleteCivAsync(it)
                }
            )

            recyclerViewCivs.adapter = adapter

            val civs = repo.getCivsAsync("All").await().toMutableList()

            toggleButtonVanilla.setOnCheckedChangeListener { button, isChecked ->
                if (isChecked) {
                    this@CivsFragment.launch {
                        civs.addAll(repo.getCivsAsync("Vanilla").await())
                        adapter.updateCivList(civs.sortedBy { it.leader })
                        button.alpha = 1f
                    }
                } else {
                    this@CivsFragment.launch {
                        civs.removeAll(repo.getCivsAsync("Vanilla").await())
                        adapter.updateCivList(civs.sortedBy { it.leader })
                        button.alpha = .5f
                    }
                }
            }

            toggleButtonJFD.setOnCheckedChangeListener { button, isChecked ->
                if (isChecked) {
                    this@CivsFragment.launch {
                        civs.addAll(repo.getCivsAsync("JFD").await())
                        adapter.updateCivList(civs.sortedBy { it.leader })
                        button.alpha = 1f
                    }
                } else {
                    this@CivsFragment.launch {
                        civs.removeAll(repo.getCivsAsync("JFD").await())
                        adapter.updateCivList(civs.sortedBy { it.leader })
                        button.alpha = .5f
                    }
                }
            }
        }
    }
}
