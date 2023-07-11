package com.example.firebaselogin.ui.People

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaselogin.data.model.people.PeopleModel
import com.example.firebaselogin.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

    val peopleLiveData:MutableLiveData<PeopleModel> by lazy{
        MutableLiveData<PeopleModel>()
    }

    fun getPeopleData(){
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getPeopleModel()
            peopleLiveData.postValue(result)
        }
    }
}