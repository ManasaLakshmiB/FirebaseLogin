package com.example.firebaselogin.ui.People

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaselogin.R
import com.example.firebaselogin.data.model.people.PeopleItemModel
import com.example.firebaselogin.data.model.people.PeopleModel
import com.example.firebaselogin.databinding.ItemPeopleBinding
import com.google.gson.Gson

class PeopleAdapter(val peopleModel: PeopleModel):
RecyclerView.Adapter<PeopleAdapter.ViewHolder>(){

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val binding = ItemPeopleBinding.bind(view)
        fun updateUI(data: PeopleItemModel){
            binding.apply {
                tvpeople.text = data.firstName+""+data.lastName


                Glide
                    .with(view)
                    .load(data.avatar)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivAvatar)


                detailcontent.setOnClickListener {
                   val navController= Navigation.findNavController(view)
                   navController.navigate(
                       R.id.action_navigation_home_to_peopledetailfragment,
                       bundleOf(
                           "item" to Gson().toJson(data)
                       )
                   )
                 }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_people,
                parent,false
            )
        )
    }

    override fun getItemCount() = peopleModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateUI(peopleModel[position])
    }

}