package com.example.kinoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinoapp.adapters.MovieAdapter
import com.example.kinoapp.items.MovieItem
//import com.example.kinoapp.ui.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fetchJson()


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun fetchJson() {

//        val url = "http://192.168.56.1:8080/show"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.56.1:8080/show")
            //.addHeader("Authorization", "Bearer $jwtToken")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Unexpected code $response")
                    }
                    if (response.code == 200) {
                        val body = response.body?.string();
                        val gson = Gson();
                        val films = JSONObject(body).getJSONArray("films")

                        val message: List<MovieItem> =
                            gson.fromJson(films.toString(), Array<MovieItem>::class.java).toList()

                        runOnUiThread {
                            main_recycle_view.adapter = MovieAdapter(message)
                            main_recycle_view.layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                    }
                }
            }
        })


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
//                runOnUiThread {
//                    main_recycle_view.adapter = MovieAdapter(message)
//                    main_recycle_view.layoutManager = LinearLayoutManager(this@MainActivity)
//                }
//            }
//
//            override fun onFailure(call: Call?, e: IOException?) {
//                println("Failed to execute request")
//                if (e != null) {
//                    println(e.message)
//                }
//            }
//        })
    }

}