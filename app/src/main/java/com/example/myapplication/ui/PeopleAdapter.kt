package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.People
import kotlinx.android.synthetic.main.item_people.view.*

class PeopleAdapter(
    private val people: MutableList<People>,
    private val actionEdit: (People) -> Unit,
    private val actionDelete: (People) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people, parent, false)
        return PeopleHolder(view)
    }

    override fun getItemCount() = people.size
    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        holder.bind(people[position], actionEdit) {
            actionDelete(it)
            people.remove(it)
            notifyDataSetChanged()
        }
    }

    fun updatePeopleList(peopleList: List<People>) {
        people.clear()
        people.addAll(peopleList)
        notifyDataSetChanged()
    }

    class PeopleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(
            people: People,
            actionEdit: (People) -> Unit,
            actionDelete: (People) -> Unit
        ) = itemView.apply {
            people.apply {
//                textViewDbId.text = "$id."
                textViewFirstName.text = firstName
                textViewLastName.text = lastName
                textViewAge.text = age.toString()
            }

            imageViewDelete.setOnClickListener {
                actionDelete(people)
            }

            imageViewEdit.setOnClickListener {
                actionEdit(people)
            }
        }
    }
}