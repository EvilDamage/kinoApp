package com.example.kinoapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.RoomActivity
import kotlinx.android.synthetic.main.seat_item.view.*


class SeatAdapter(private val movieList: Array<String>, val context: Context) : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    val list: ArrayList<String> = ArrayList()
    val sharedPreferences = context.getSharedPreferences("SP", Context.MODE_PRIVATE).edit()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.seat_item,
            parent,
            false
        )

        return SeatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val currentItem = movieList[position]

        holder.button.text = position.toString()

        holder.button.setOnClickListener(){
            if(position.toString() in list){

            }
            else{
                holder.button.setBackgroundColor(Color.parseColor("#009900"))
                list.add(position.toString());
                val txtView = (context as RoomActivity).findViewById<View>(R.id.seatsSummary) as TextView
                val priceView = (context as RoomActivity).findViewById<View>(R.id.priceSummary) as TextView
                txtView.text = "Miejsca: " + list.toString()
                priceView.text = (list.size * 15).toString() + ",00 PLN"

                sharedPreferences.putString("PRICE", (list.size * 15).toString())
                sharedPreferences.putString("SEATS", list.toString())
                sharedPreferences.apply();
            }

        }
    }

    override fun getItemCount() = movieList.size

    class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val button: TextView = itemView.info_text

    }
}
