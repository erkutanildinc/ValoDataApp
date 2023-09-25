package com.example.valodata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valodata.model.Weapon
import com.example.valodata.service.ValoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeaponViewModel : ViewModel(){

    val weapons = MutableLiveData<List<Weapon>>()
    val weaponProgressBar = MutableLiveData<Boolean>()
    private var job : Job? = null
    private var valoApi = ValoAPI()

    fun getData(){
        getDataFromAPI()
    }

    fun getDataFromAPI(){

        weaponProgressBar.value = true

        job = CoroutineScope(Dispatchers.IO).launch {
            val weaponResponse = valoApi.getWeapons()

            withContext(Dispatchers.Main){
                weaponResponse.body()?.let {
                    val weaponsList = it.data
                    weapons.value = weaponsList
                    weaponProgressBar.value = false
                }
            }
        }
    }
}