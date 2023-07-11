package com.example.firebaselogin.ui.People


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebaselogin.data.model.people.PeopleItemModel
import com.example.firebaselogin.databinding.FragmentPeopleDetailBinding
import com.google.gson.Gson

class PeopleDetailFragment:Fragment() {
    lateinit var  binding: FragmentPeopleDetailBinding

    lateinit var data:PeopleItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = Gson().fromJson(arguments?.getString("item"),PeopleItemModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentPeopleDetailBinding.inflate(inflater,container,false)

        binding.apply{
            tVemail.text= data.email
            tVjobtitle.text=  data.jobtitle
            tVfavcolor.text= data.favouriteColor


        }
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(Gson().toJson(data))
    }
}