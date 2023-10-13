package com.example.valodata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.valodata.R
import com.example.valodata.viewmodel.MapDetailViewModel
import com.example.valodata.viewmodel.WeaponDetailViewModel
import org.w3c.dom.Text

class MapDetailFragment : Fragment() {

    private lateinit var mapDetailLiveModel: MapDetailViewModel
    private lateinit var mapDetailProgressBar : ProgressBar
    private lateinit var mapImage : ImageView
    private lateinit var mapTacticalImage : ImageView
    private lateinit var mapName : TextView
    private lateinit var mapDescription : TextView
    private lateinit var mapSites : TextView
    private lateinit var mapUUID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_detail, container, false)

        mapImage = view.findViewById(R.id.mapDetailMapImageView)
        mapTacticalImage = view.findViewById(R.id.mapDetailMapTacticalImageView)
        mapName = view.findViewById(R.id.mapDetailMapTextView)
        mapDescription = view.findViewById(R.id.mapDetailMapDescription)
        mapSites = view.findViewById(R.id.mapDetailSitesTextView)
        mapDetailProgressBar = view.findViewById(R.id.mapDetailprogressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            mapUUID = MapDetailFragmentArgs.fromBundle(it).mapUUID
        }

        mapDetailLiveModel = ViewModelProvider(this).get(MapDetailViewModel::class.java)
        mapDetailLiveModel.getMapData(mapUUID)

        observeLiveData()
    }

    fun observeLiveData(){

        mapImage.visibility = View.GONE
        mapTacticalImage.visibility = View.GONE
        mapName.visibility = View.GONE
        mapDescription.visibility = View.GONE
        mapSites.visibility = View.GONE

        mapDetailLiveModel.mapLiveData.observe(viewLifecycleOwner, Observer { map ->

            mapImage.visibility = View.VISIBLE
            mapTacticalImage.visibility = View.VISIBLE
            mapName.visibility = View.VISIBLE
            mapDescription.visibility = View.VISIBLE
            mapSites.visibility = View.VISIBLE

            mapName.text = map.displayName
            mapDescription.text = map.narrativeDescription
            mapSites.text = map.tacticalDescription.toString()
            context?.let {
                Glide.with(it).load(map.splash).into(mapImage)
                Glide.with(it).load(map.displayIcon).into(mapTacticalImage)
            }
        })

        mapDetailLiveModel.mapDetailProgressbar.observe(viewLifecycleOwner, Observer { progress ->
            progress?.let {
                if (progress) {
                    mapDetailProgressBar.visibility = View.VISIBLE
                } else {
                    mapDetailProgressBar.visibility = View.GONE
                }
            }
        })
    }
}