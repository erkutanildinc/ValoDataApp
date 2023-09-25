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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valodata.R
import com.example.valodata.adapter.AgentAdapter
import com.example.valodata.adapter.MapAdapter
import com.example.valodata.model.Agent
import com.example.valodata.model.Map
import com.example.valodata.viewmodel.CharacterViewModel
import com.example.valodata.viewmodel.MapViewModel

class MapListFragment : Fragment() {

    private lateinit var mapsViewModel: MapViewModel
    private lateinit var mapsRecyclerView: RecyclerView
    private lateinit var mapsProgressBar: ProgressBar
    lateinit var mapAdapter: MapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_list, container, false)
        mapsRecyclerView = view.findViewById(R.id.mapsListRecyclerView)
        mapsProgressBar = view.findViewById(R.id.mapsProgressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapsViewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        mapsViewModel.refreshData()

        observeLiveData()
    }

    private fun observeLiveData() {
        mapsViewModel.maps.observe(viewLifecycleOwner, Observer { maps ->
            maps?.let {
                mapsRecyclerView.visibility = View.VISIBLE
                mapsRecyclerView.layoutManager = LinearLayoutManager(context)
                mapAdapter = MapAdapter(maps as ArrayList<Map>)
                mapsRecyclerView.adapter = mapAdapter
            }
        })

        mapsViewModel.mapProgressBar.observe(viewLifecycleOwner, Observer { progress ->
            if (progress) {
                mapsProgressBar.visibility = View.VISIBLE
                mapsRecyclerView.visibility = View.GONE
            } else {
                mapsProgressBar.visibility = View.GONE
                mapsRecyclerView.visibility = View.VISIBLE
            }
        })
    }
}