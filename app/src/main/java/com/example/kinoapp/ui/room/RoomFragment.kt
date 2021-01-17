package com.example.kinoapp.ui.room

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kinoapp.R
import com.example.kinoapp.RoomActivity

class RoomFragment : Fragment() {

    private lateinit var roomViewModel: RoomViewModel

    @SuppressLint("ResourceType")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        roomViewModel =
                ViewModelProvider(this).get(RoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_room, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
        roomViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        val button: Button = root.findViewById(R.id.returnButton)

        button.setOnClickListener(){
            val intent = Intent(this.context, RoomActivity::class.java)
            this.startActivity(intent)
        }

        return root
    }


}