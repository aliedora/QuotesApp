package com.e.quotesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.quotesapp.R
import com.e.quotesapp.model.Quote
import retrofit2.Callback

class MyQuotesAdapter(private val context: Callback<ArrayList<Quote>>, private val quotesList: ArrayList<Quote>):RecyclerView.Adapter<MyQuotesAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_anime: TextView = itemView.findViewById(R.id.txt_anime)
        val txt_character: TextView = itemView.findViewById(R.id.txt_character)
        val txt_quote: TextView = itemView.findViewById(R.id.txt_quote)

        fun bind(listItem: Quote) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = quotesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = quotesList[position]
        holder.bind(listItem)

        holder.txt_anime.text = quotesList[position].anime
        holder.txt_character.text = quotesList[position].character
        holder.txt_quote.text = quotesList[position].quote
    }
}