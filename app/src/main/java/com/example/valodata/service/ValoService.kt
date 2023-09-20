package com.example.valodata.service

import com.example.valodata.model.AgentResponse
import com.example.valodata.model.MapsResponse
import com.example.valodata.model.WeaponsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ValoService {

    @GET("v1/agents")
    suspend fun getAgents() : Response<AgentResponse>

    @GET("v1/maps")
    suspend fun getMaps() : Response<MapsResponse>

    @GET("v1/weapons")
    suspend fun getWeapons(): Response<WeaponsResponse>


}