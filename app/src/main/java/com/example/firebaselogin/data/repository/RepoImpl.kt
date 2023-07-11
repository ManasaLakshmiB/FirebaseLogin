package com.example.firebaselogin.data.repository

import com.example.firebaselogin.data.model.Rooms.RoomsModel
import com.example.firebaselogin.data.remote.ApiCall
import javax.inject.Inject

class RepoImpl @Inject constructor(
    val dataCall:ApiCall
):Repository {

    override suspend fun  getPeopleModel()=dataCall.getPeopleModel()
    override suspend fun getRoomsModel() =dataCall.getRoomsModel()

}