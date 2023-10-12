package com.example.valodata.service

import com.example.valodata.model.AgentDetailResponse
import com.example.valodata.model.AgentResponse
import com.example.valodata.model.MapsDetailResponse
import com.example.valodata.model.MapsResponse
import com.example.valodata.model.WeaponDetailResponse
import com.example.valodata.model.WeaponsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ValoService {

    @GET("v1/agents")
    suspend fun getAgents() : Response<AgentResponse>

    @GET("v1/maps")
    suspend fun getMaps() : Response<MapsResponse>

    @GET("v1/weapons")
    suspend fun getWeapons(): Response<WeaponsResponse>

    @GET("v1/agents/{agentUuid}")
    suspend fun getAgentByUuid(@Path("agentUuid") agentUuid : String) : Response<AgentDetailResponse>

    @GET("v1/weapons/{weaponUuid}")
    suspend fun getWeaponByUuid(@Path("weaponUuid") weaponUuid : String) : Response<WeaponDetailResponse>

    @GET("v1/weapons/{mapUuid}")
    suspend fun getMapByUuid(@Path("mapUuid") mapUuid : String) : Response<MapsDetailResponse>

}