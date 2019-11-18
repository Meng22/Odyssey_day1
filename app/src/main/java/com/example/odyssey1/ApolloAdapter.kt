package com.example.odyssey1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ApolloAdapter: RecyclerView.Adapter<ApolloAdapter.ViewHolder>() {
    private val item_list: MutableList<GodItem> = arrayListOf()

    private var worshipListener : ItemClickListener? = null

    interface ItemClickListener{
        fun toClick(item: GodItem)
    }
    fun setToClick(listener: ItemClickListener){
        worshipListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.goditem_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item_list[position])
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val chk_item = view.findViewById<CheckBox>(R.id.chk_item)
        val img_item = view.findViewById<ImageView>(R.id.img_item)
        val tv_itemname = view.findViewById<TextView>(R.id.tv_itemname)

        fun bind(item: GodItem){
            tv_itemname.setText(item.name)
            Glide.with(img_item.context).load("https://7df94714.ngrok.io/storage/${item.image}").into(img_item)
            chk_item.isChecked = item.isWorshiped
            chk_item.setOnClickListener{
                worshipListener?.toClick(item)
            }
        }
    }
    fun update(newList: MutableList<GodItem>){
        item_list.clear()
        item_list.addAll(newList)
        notifyDataSetChanged()
    }

}