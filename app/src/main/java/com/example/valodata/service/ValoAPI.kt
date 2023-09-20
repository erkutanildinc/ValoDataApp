package com.example.valodata.service

import com.example.valodata.model.AgentResponse
import com.example.valodata.model.MapsResponse
import com.example.valodata.model.WeaponsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ValoAPI {

    private val Base_URL = "https://valorant-api.com/"
    private val api =
        Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ValoService::class.java)

    suspend fun getAgents() : Response<AgentResponse> {
        return api.getAgents()
    }

    suspend fun getMaps() : Response<MapsResponse>{
        return api.getMaps()
    }

    suspend fun getWeapons() : Response<WeaponsResponse>{
        return api.getWeapons()
    }
}