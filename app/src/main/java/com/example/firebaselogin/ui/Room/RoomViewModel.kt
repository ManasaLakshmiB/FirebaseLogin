package com.example.firebaselogin.ui.Room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaselogin.data.model.Rooms.RoomsModel
import com.example.firebaselogin.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel(){

    val RoomliveData: MutableLiveData<RoomsModel> by lazy{
        MutableLiveData<RoomsModel>()
    }
    fun getRoomData(){
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getRoomsModel()
            RoomliveData.postValue(result)
        }
    }
}