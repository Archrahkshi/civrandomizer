package com.archrahkshi.civrandomizer.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.civrandomizer.R
import kotlinx.android.synthetic.main.item_civ.view.*
import kotlinx.android.synthetic.main.item_player.view.*

class RandomizerAdapter(
    private val players: List<Any>
) : RecyclerView.Adapter<RandomizerAdapter.RandomizerHolder>() {

    private val CIV = 0
    private val PLAYER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RandomizerHolder(
        LayoutInflater.from(parent.context).inflate(
            if (viewType == PLAYER) R.layout.item_player else R.layout.item_civ,
            parent,
            false
        )
    )

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: RandomizerHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemViewType(position: Int) = if (players[position] is String) PLAYER else CIV

    class RandomizerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Any) = itemView.apply {
            when (item) {
                is Civ -> item.apply {
                    textViewLeader.text = leader
                    textViewNation.text = nation
                    textViewAuthor.text = if (author != "") author else "Vanilla"
                }
                is String -> textViewPlayerN.text = item
            }
        }
    }
}