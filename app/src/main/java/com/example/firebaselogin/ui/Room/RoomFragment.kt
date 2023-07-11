package com.example.firebaselogin.ui.Room

import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselogin.MainActivity
import com.example.firebaselogin.data.model.Rooms.RoomsItemModel
import com.example.firebaselogin.databinding.FragmentRoomBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment:Fragment() {

    lateinit var binding: FragmentRoomBinding
    lateinit var recyclerView: RecyclerView
    lateinit var Roomdata: RoomsItemModel

    val viewmodel by viewModels<RoomViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel.getRoomData()

//
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvroom

        viewmodel.RoomliveData.observe(viewLifecycleOwner) {
            var adapter = RoomAdapter(it)
            recyclerView.adapter = adapter
        }
    }


}