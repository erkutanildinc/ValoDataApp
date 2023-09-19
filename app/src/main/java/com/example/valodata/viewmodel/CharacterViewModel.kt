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

class CharacterViewModel : ViewModel() {

    val agents = MutableLiveData<List<Agent>>()
    val agentsProgressbar = MutableLiveData<Boolean>()
    private var job : Job? = null
    private var valoApi = ValoAPI()


    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        agentsProgressbar.value = true

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = valoApi.getAgents()

            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    response.body()?.let {
                            val agents = it
                            print("LALALALALALA")
                        }
                    }
                }
            }
        }

    }
