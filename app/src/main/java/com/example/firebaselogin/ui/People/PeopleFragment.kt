package com.example.firebaselogin.ui.People

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselogin.data.model.people.PeopleModel
import com.example.firebaselogin.databinding.FragmentPeopleBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    lateinit var binding: FragmentPeopleBinding
    lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth

    val viewmodel: PeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getPeopleData()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvpeople

        viewmodel.peopleLiveData.observe(viewLifecycleOwner) {
            var adapter = PeopleAdapter(it)
            recyclerView.adapter = adapter
        }
    }
}

