package com.example.kinoapp


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import kotlinx.android.synthetic.main.content_room.*


class SummaryActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summery)

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

//        val sharedPreferences = getSharedPreferences("SP", Context.MODE_PRIVATE).edit()
        val getsharedPreferences = getSharedPreferences("SP", Context.MODE_PRIVATE)

        val movie :String = getsharedPreferences.getString("TITLE", "").toString()
        val seats :String = getsharedPreferences.getString("SEATS", "").toString()
        val price :String = getsharedPreferences.getString("PRICE", "").toString()
        val name :String = getsharedPreferences.getString("NAME", "").toString()

        val title: TextView = findViewById<TextView>(R.id.titleForm)
        title.text = movie

        val seatsView: TextView = findViewById<TextView>(R.id.seatsSummary)
        seatsView.text = "Miejsca: " + seats

        val priceView: TextView = findViewById<TextView>(R.id.priceSummary)
        priceView.text = price + ",00 PLN"

        val thanks: TextView = findViewById<TextView>(R.id.thanks)
        thanks.text = "Dziękujemy " + name + " za zakup!"

        val tickeButton: Button = findViewById<Button>(R.id.ticketDownload)
        val fvButton: Button = findViewById<Button>(R.id.fvDownload)

        tickeButton.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.56.1:8080/download/bill/" + name + "B.pdf"))
            startActivity(browserIntent)
        }

        fvButton.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.56.1:8080/download/pdf/" + name + "H.pdf"))
            startActivity(browserIntent)
        }

        returnButton.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}