package com.archrahkshi.civrandomizer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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
) : Fragment(), CoroutineScope, SeekBar.OnSeekBarChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_randomizer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekBarPlayerCount.setOnSeekBarChangeListener(this)
        textViewPlayerCount.text = (seekBarPlayerCount.progress + 2).toString()

        seekBarCivsPerPlayerCount.setOnSeekBarChangeListener(this)
        textViewCivsPerPlayerCount.text = (seekBarCivsPerPlayerCount.progress + 1).toString()

        buttonRandomize.setOnClickListener {
            launch {
                val playerCount = seekBarPlayerCount.progress + 2
                val civsPerPlayerCount = seekBarCivsPerPlayerCount.progress + 1
                val randomizedCivs = CivsRepository().getNRandomCivsAsync(
                    playerCount * civsPerPlayerCount
                ).await()

                try {
                    var player = 0
                    recyclerViewRandomizer.adapter = RandomizerAdapter(
                        List(playerCount + randomizedCivs.size) {
                            if (it % (civsPerPlayerCount + 1) == 0) {
                                player++
                                getString(R.string.player) + " " + player
                            } else {
                                randomizedCivs.elementAt(it - player)
                            }
                        }
                    )
                } catch (e: IndexOutOfBoundsException) {
                    Toast.makeText(
                        context,
                        getString(R.string.not_enough_civilizations),
                        LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        textViewPlayerCount.text = (seekBarPlayerCount.progress + 2).toString()
        textViewCivsPerPlayerCount.text = (seekBarCivsPerPlayerCount.progress + 1).toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {}

    override fun onStopTrackingTouch(seekBar: SeekBar) {}
}
