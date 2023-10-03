package com.example.valodata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valodata.model.Weapon
import com.example.valodata.model.WeaponDetailResponse
import com.example.valodata.service.ValoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeaponDetailViewModel : ViewModel() {

    val weaponLiveData = MutableLiveData<Weapon>()
    val weaponDetailProgressbar = MutableLiveData<Boolean>()
    private var job: Job? = null
    private var valoApi = ValoAPI()

    fun getWeaponData(uuid:String){
        weaponDetailProgressbar.value = true

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = valoApi.getWeaponByUUID(uuid)

            withContext(Dispatchers.Main){
                response.body()?.let {
                    val weaponDetail = response.body()!!.data
                    weaponLiveData.value = weaponDetail
                    weaponDetailProgressbar.value = false
                }
            }
        }
    }
}