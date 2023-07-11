package com.example.firebaselogin.ui.Room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselogin.R
import com.example.firebaselogin.data.model.Rooms.RoomsItemModel
import com.example.firebaselogin.data.model.Rooms.RoomsModel
import com.example.firebaselogin.databinding.ItemRoomBinding

class RoomAdapter(val roomsModel: RoomsModel):

RecyclerView.Adapter<RoomAdapter.ViewHolder>(){


    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){

        val binding = ItemRoomBinding.bind(view)

        fun updateUI(roomdata:RoomsItemModel){
            binding.apply {
                tvid.text = roomdata.id
                tvisoccupied.text = if (roomdata.isOccupied == true)
                    "yes"
                else
                    "no"
              //  tvmaxoccupied.text = roomdata.maxOccupancy





                roomcontent.setOnClickListener{
                    val navController = Navigation.findNavController(view)

                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_room,parent,false
            )
        )
    }

    override fun getItemCount()= roomsModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateUI(roomsModel[position])
    }

}