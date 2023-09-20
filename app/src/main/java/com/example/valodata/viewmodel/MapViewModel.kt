package com.example.valodata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valodata.model.Map
import com.example.valodata.service.ValoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel : ViewModel() {

    val maps = MutableLiveData<List<Map>>()
    val mapProgressBar = MutableLiveData<Boolean>()
    private var job : Job? = null
    private var valoApi = ValoAPI()

    fun refreshData(){
        getMapsFromAPI()
    }

    fun getMapsFromAPI(){
        mapProgressBar.value = true

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = valoApi.getMaps()

            withContext(Dispatchers.Main){
                response.body()?.let {
                        val mapsResponse = it
                        maps.value = mapsResponse.data
                        mapProgressBar.value = false
                }
            }
        }
    }

}