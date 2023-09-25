package com.example.valodata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valodata.R
import com.example.valodata.adapter.WeaponAdapter
import com.example.valodata.model.Weapon
import com.example.valodata.viewmodel.WeaponViewModel

class WeaponListFragment : Fragment() {


    private lateinit var weaponsViewModel : WeaponViewModel
    private lateinit var weaponsRecyclerView : RecyclerView
    private lateinit var weaponsProgressBar : ProgressBar
    private lateinit var weaponAdapter : WeaponAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weapon_list, container, false)
        weaponsRecyclerView = view.findViewById(R.id.weaponListRecyclerView)
        weaponsProgressBar = view.findViewById(R.id.weaponProgressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weaponsViewModel = ViewModelProvider(this).get(WeaponViewModel::class.java)
        weaponsViewModel.getData()

        observeLiveData()
    }

    private fun observeLiveData(){

        weaponsViewModel.weapons.observe(viewLifecycleOwner, Observer { weapons ->
            weapons?.let {

                weaponsRecyclerView.visibility = View.VISIBLE
                weaponsRecyclerView.layoutManager = LinearLayoutManager(context)
                weaponAdapter = WeaponAdapter(it as ArrayList<Weapon>)
                weaponsRecyclerView.adapter = weaponAdapter
            }
        })

        weaponsViewModel.weaponProgressBar.observe(viewLifecycleOwner, Observer { progress ->
            progress?.let {
                if(progress){
                    weaponsProgressBar.visibility = View.VISIBLE
                    weaponsRecyclerView.visibility = View.GONE
                }
                else{
                    weaponsProgressBar.visibility = View.GONE
                    weaponsRecyclerView.visibility = View.VISIBLE
                }
            }
        })
    }


}