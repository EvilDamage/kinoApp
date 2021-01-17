package com.example.kinoapp


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class FormActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//////         Passing each menu ID as a set of Ids because each
//////         menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        val sharedPreferences = getSharedPreferences("SP", Context.MODE_PRIVATE).edit()
        val getsharedPreferences = getSharedPreferences("SP", Context.MODE_PRIVATE)

        val button: Button = findViewById<Button>(R.id.returnButton)
        val places: TextView = findViewById<TextView>(R.id.seatsSummary)

            println(getsharedPreferences.getString("TITLE", "").toString())
        places.text = "Miejsca: " + getsharedPreferences.getString("SEATS", "").toString()

        val titleForm: TextView =  findViewById<TextView>(R.id.titleForm)
        titleForm.text = getsharedPreferences.getString("TITLE", "").toString()

        button.setOnClickListener(){
            val name = findViewById<EditText>(R.id.name)
            val surname = findViewById<EditText>(R.id.surname)
            val email = findViewById<EditText>(R.id.email)

            sharedPreferences.putString("NAME", name.text.toString())
            sharedPreferences.putString("SURNAME", surname.text.toString())
            sharedPreferences.putString("EMAIL", email.text.toString())
            sharedPreferences.apply()

                Thread {
                sendJson()
            }.start()

            val intent = Intent(this, SummaryActivity::class.java)
            this.startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun sendJson() {
        val getsharedPreferences = getSharedPreferences("SP", Context.MODE_PRIVATE)

        val time :String = getsharedPreferences.getString("TIME", "").toString()
        val id :String = getsharedPreferences.getString("ID", "").toString()
        val seats :String = getsharedPreferences.getString("SEATS", "").toString()
        val price :String = getsharedPreferences.getString("PRICE", "").toString()
        val name :String = getsharedPreferences.getString("NAME", "").toString()
        val surname :String = getsharedPreferences.getString("SURNAME", "").toString()
        val email :String = getsharedPreferences.getString("EMAIL", "").toString()


        val client = OkHttpClient()

        val array = JSONArray()
        array.put(seats);

        val rootObject= JSONObject()
        rootObject.put("id", id)
        rootObject.put("time", time)
        rootObject.put("price", price)
        rootObject.put("standardSeats", array)
        rootObject.put("vipSeats", array)
        rootObject.put("name", name)
        rootObject.put("surname", surname)
        rootObject.put("email", email)

//        val formBody = FormBody.Builder()
//            .add("id", "1")
//            .add("time", "10:00")
//            .add("price", "10")
//            .add("standardSeats","[\"A1\"]")
//            .add("vipSeats", "[\"A1\"]")
//            .add("name", "1")
//            .add("surname", "1")
//            .add("email", "1")
//            .build()


//        id: this.props.location.state.id,
//        time: this.props.location.state.hour,
//        price: this.props.location.state.totalPrice,
//        standardSeats: this.props.location.state.standardSeat,
//        vipSeats: this.props.location.state.vipSeat,
//        name: this.state.name,
//        surname: this.state.surname,
//        email: this.state.email

//        val json = Gson().toJson(rootObject)

        val body = rootObject.toString().toRequestBody()


        val request = Request.Builder()
            .url("http://192.168.56.1:8080/reservation")
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
        }
    }
}