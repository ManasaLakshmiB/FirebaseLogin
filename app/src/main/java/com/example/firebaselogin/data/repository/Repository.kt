package com.example.firebaselogin.data.repository

import com.example.firebaselogin.data.model.Rooms.RoomsModel
import com.example.firebaselogin.data.model.people.PeopleModel

interface Repository {
suspend fun getPeopleModel(): PeopleModel
    suspend fun getRoomsModel(): RoomsModel

}