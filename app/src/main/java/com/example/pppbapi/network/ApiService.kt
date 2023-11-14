package com.example.pppbapi.network

import com.example.pppbapi.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(".")
    fun getAllOrganizations() : Call<List<Data>>
}