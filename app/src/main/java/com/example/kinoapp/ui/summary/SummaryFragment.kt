package com.example.kinoapp.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kinoapp.MainActivity
import com.example.kinoapp.R
import com.example.kinoapp.ui.form.FormFragment
import com.example.kinoapp.ui.home.HomeFragment

class SummaryFragment : Fragment() {

    private lateinit var roomViewModel: SummaryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        roomViewModel =
                ViewModelProvider(this).get(SummaryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_summary, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
        roomViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        val button: Button = root.findViewById(R.id.button)

        button.setOnClickListener(){
            val newFragment = HomeFragment()
            val mainActivityView = (activity as MainActivity)
            mainActivityView.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, newFragment)
                .addToBackStack(null)
                .commit()
        }

        return root
    }


}