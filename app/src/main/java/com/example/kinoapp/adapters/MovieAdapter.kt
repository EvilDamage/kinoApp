package com.example.kinoapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.MainActivity
import com.example.kinoapp.R
import com.example.kinoapp.RoomActivity
import com.example.kinoapp.items.MovieItem
import com.example.kinoapp.ui.home.HomeFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(private val movieList: List<MovieItem>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.title.text = currentItem.title
        Picasso.get().load(currentItem.url).into(holder.image)
    }

    override fun getItemCount() = movieList.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init{
            itemView.button1.setOnClickListener{
                val intent = Intent(itemView.context, RoomActivity::class.java)
                intent.putExtra("TITLE", itemView.movieTextView.text)
                intent.putExtra("ID", itemView.movieTextView.id)
                intent.putExtra("TIME", "12:00")

                itemView.context.startActivity(intent)
            }
            itemView.button2.setOnClickListener{
                val intent = Intent(itemView.context, RoomActivity::class.java)
                intent.putExtra("TITLE", itemView.movieTextView.text)
                intent.putExtra("ID", itemView.movieTextView.id)
                intent.putExtra("TIME", "18:00")

                itemView.context.startActivity(intent)
            }
            itemView.button3.setOnClickListener{
                val intent = Intent(itemView.context, RoomActivity::class.java)
                intent.putExtra("TITLE", itemView.movieTextView.text)
                intent.putExtra("ID", itemView.movieTextView.id)
                intent.putExtra("TIME", "21:00")

                itemView.context.startActivity(intent)
            }
            itemView.button4.setOnClickListener{
                val intent = Intent(itemView.context, RoomActivity::class.java)
                intent.putExtra("TITLE", itemView.movieTextView.text)
                intent.putExtra("ID", itemView.movieTextView.id)
                intent.putExtra("TIME", "23:00")

                itemView.context.startActivity(intent)
            }
        }
        //        val imageView: ImageView = itemView.movieImageView
        val title: TextView = itemView.movieTextView
        val image: ImageView = itemView.movieImageView

    }
}

private fun Intent.putExtra(s: String, movieTextView: TextView?) {

}
