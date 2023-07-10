package com.example.firebaselogin.data.model.people


import com.example.firebaselogin.data.model.people.DataModel
import com.google.gson.annotations.SerializedName

data class PeopleItemModel(
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("data")
    val `data`: DataModel? = DataModel(),
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("favouriteColor")
    val favouriteColor: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("fromName")
    val fromName: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("jobtitle")
    val jobtitle: String? = "",
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("size")
    val size: String? = "",
    @SerializedName("to")
    val to: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("{}")
    val x: String? = ""
)