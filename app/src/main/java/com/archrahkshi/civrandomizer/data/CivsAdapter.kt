package com.archrahkshi.civrandomizer.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.archrahkshi.civrandomizer.R
import kotlinx.android.synthetic.main.item_civ.view.*

class CivsAdapter(
    private val civs: MutableList<Civ>,
    private val actionEdit: (Civ) -> Unit,
    private val actionDelete: (Civ) -> Unit
) : RecyclerView.Adapter<CivsAdapter.CivsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CivsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_civ, parent, false)
        )

    override fun getItemCount() = civs.size

    override fun onBindViewHolder(holder: CivsHolder, position: Int) {
        holder.bind(
            civ = civs[position],
            actionEdit = actionEdit,
            actionDelete = {
                actionDelete(it)
                civs.remove(it)
                notifyDataSetChanged()
            }
        )
    }

    fun updateCivList(civList: List<Civ>) {
        civs.clear()
        civs.addAll(civList)
        notifyDataSetChanged()
    }

    class CivsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            civ: Civ,
            actionEdit: (Civ) -> Unit,
            actionDelete: (Civ) -> Unit
        ) = itemView.apply {
            civ.apply {
                textViewLeader.text = leader
                textViewNation.text = nation
                textViewAuthor.text = if (author != "") author else "Vanilla"
            }

            imageViewAvatar.setOnLongClickListener {
                actionDelete(civ)
                true
            }

            imageViewAvatar.setOnClickListener {
                actionEdit(civ)
            }
        }
    }
}