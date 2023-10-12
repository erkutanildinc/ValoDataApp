package com.example.valodata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valodata.model.Map
import com.example.valodata.model.Weapon
import com.example.valodata.service.ValoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapDetailViewModel : ViewModel() {

    val mapLiveData = MutableLiveData<Map>()
    val mapDetailProgressbar = MutableLiveData<Boolean>()
    private var job: Job? = null
    private var valoApi = ValoAPI()

    fun getMapData(uuid:String){
        mapDetailProgressbar.value = true

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = valoApi.getMapByUUID(uuid)

            withContext(Dispatchers.Main){
                response.body()?.let {
                    val mapDetail = response.body()!!.data
                    mapLiveData.value = mapDetail
                    mapDetailProgressbar.value = false
                }
            }
        }
    }
}