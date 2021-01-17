package com.example.kinoapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinoapp.MainActivity
import com.example.kinoapp.R
import com.example.kinoapp.adapters.MovieAdapter
import com.example.kinoapp.items.MovieItem
import com.example.kinoapp.ui.room.RoomFragment
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//        fetchJson()
//        main_recycle_view.layoutManager = LinearLayoutManager(activity)

//        val button3: Button = root.findViewById(R.id.button3)
//
//        button3.setOnClickListener(){

//            val newFragment = RoomFragment()
//            val mainActivityView = (activity as MainActivity)
//            mainActivityView.supportFragmentManager.beginTransaction()
//                .replace(R.id.nav_host_fragment, newFragment)
//                .addToBackStack(null)
//                .commit()
//        }


        return root
    }
//    private fun fetchJson(){
//
//        val url = "http://192.168.56.1:8080/show"
//        val request = Request.Builder().url(url).build()
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call?, response: Response?) {
//                val body = response?.body()?.string()
//
//                val gson = GsonBuilder().create()
//
//                val films = JSONObject(body).getJSONArray("films")
//
//                val message: List<MovieItem> =
//                    gson.fromJson(films.toString(), Array<MovieItem>::class.java).toList()
//
////                runOnUiThread {
//                    main_recycle_view.adapter = MovieAdapter(message)
////                }
//            }
//
//            override fun onFailure(call: Call?, e: IOException?) {
//                println("Failed to execute request")
//                if (e != null) {
//                    println(e.message)
//                }
//            }
//        })
//    }
}

