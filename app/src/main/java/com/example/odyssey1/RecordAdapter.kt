package com.example.odyssey1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordAdapter: RecyclerView.Adapter<RecordAdapter.ViewHolder>() {
    private val item_list: MutableList<Record> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recorditem_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item_list[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val record_name = view.findViewById<TextView>(R.id.worship_name)
        val record_item = view.findViewById<TextView>(R.id.worship_item)
        val record_time = view.findViewById<TextView>(R.id.worship_time)

        fun bind(item: Record){
            record_name.setText(item.name)
            record_item.setText(item.item)
            record_time.setText(item.time)
        }
    }
    fun update(newList: MutableList<Record>){
        item_list.clear()
        item_list.addAll(newList)
        notifyDataSetChanged()
    }
}