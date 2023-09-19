package com.example.valodata.service

import com.example.valodata.model.AgentResponse
import retrofit2.Response
import retrofit2.http.GET

interface ValoService {

    @GET("v1/agents")
    suspend fun getAgents() : Response<AgentResponse>
}