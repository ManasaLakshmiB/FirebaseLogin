package com.example.firebaselogin.data.remote

import com.example.firebaselogin.data.model.Rooms.RoomsModel
import com.example.firebaselogin.data.model.people.PeopleModel
import retrofit2.http.GET

interface ApiCall {

    @GET(ApiDetail.END_POINT)
    suspend fun getPeopleModel():PeopleModel

    @GET(ApiDetail.ROOM_URL)
    suspend fun getRoomsModel():RoomsModel

}