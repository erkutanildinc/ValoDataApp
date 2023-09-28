package com.example.valodata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valodata.model.Agent
import com.example.valodata.service.ValoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentDetailViewModel : ViewModel() {

    val agentLiveData = MutableLiveData<Agent>()
    val agentDetailProgressBar = MutableLiveData<Boolean>()
    private var job: Job? = null
    private var valoApi = ValoAPI()

    fun getAgentData(uuid : String){
        agentDetailProgressBar.value = true

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = valoApi.getAgentByUUID(uuid)

            withContext(Dispatchers.Main){
                response.body()?.let {
                    agentLiveData.value = response.body()!!.data
                    agentDetailProgressBar.value = false
                }
            }
        }
    }
}