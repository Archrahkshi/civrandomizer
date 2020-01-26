package com.archrahkshi.civrandomizer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.archrahkshi.civrandomizer.R
import com.archrahkshi.civrandomizer.data.CivsRepository
import com.archrahkshi.civrandomizer.data.RandomizerAdapter
import kotlinx.android.synthetic.main.fragment_randomizer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Suppress("IMPLICIT_CAST_TO_ANY")
class RandomizerFragment(
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(), CoroutineScope {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_randomizer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonRandomize.setOnClickListener {
            launch {
                val playerCount = spinnerPlayers.selectedItem.toString().toInt()
                val playerCivs = CivsRepository().getNRandomCivsAsync(
                    playerCount * spinnerCivsPerPlayer.selectedItem.toString().toInt()
                ).await()
                recyclerViewRandomizer.adapter = RandomizerAdapter(
                    List(playerCount + playerCivs.size) {
                        if (it % playerCount == 0) {
                            "Player ${it / playerCount + 1}"
                        } else {
                            playerCivs.elementAt(it - it / playerCount - 1)
                        }
                    }
                )
            }
        }
    }
}
