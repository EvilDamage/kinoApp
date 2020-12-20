package com.example.kinoapp.ui.form

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
import com.example.kinoapp.ui.home.HomeFragment
import com.example.kinoapp.ui.summary.SummaryFragment

class FormFragment : Fragment() {

    private lateinit var formViewModel: FormViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        formViewModel =
                ViewModelProvider(this).get(FormViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_form, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
        formViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        val button: Button = root.findViewById(R.id.button)

        button.setOnClickListener(){
            val newFragment = SummaryFragment()
            val mainActivityView = (activity as MainActivity)
            mainActivityView.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, newFragment)
                .addToBackStack(null)
                .commit()
        }

        return root
    }


}