package com.example.valodata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valodata.R
import com.example.valodata.model.Map
import com.example.valodata.view.MapListFragmentDirections
import com.example.valodata.view.WeaponListFragmentDirections

class MapAdapter(var list : ArrayList<Map>) : RecyclerView.Adapter<MapAdapter.MapViewHolder>() {

    lateinit var mapName : TextView
    lateinit var mapImage : ImageView

    class MapViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.map_row_item,parent,false)
        return MapViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        mapName = holder.view.findViewById(R.id.mapRowItemNameText)
        mapImage = holder.view.findViewById(R.id.mapRowItemImageView)

        mapName.text = list.get(position).displayName
        Glide.with(holder.view).load(list.get(position).splash).into(mapImage)

        holder.itemView.setOnClickListener {
            val action = MapListFragmentDirections.actionMapListFragmentToMapDetailFragment(list.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
}
