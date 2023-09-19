package com.example.valodata.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valodata.R
import com.example.valodata.model.Agent

class AgentAdapter(var list : ArrayList<Agent>) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    lateinit var agentName : TextView
    lateinit var agentImage : ImageView

    class AgentViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.character_row_item,parent,false)
        return AgentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        agentName = holder.view.findViewById(R.id.agentRowItemNameTextview)
        agentImage = holder.view.findViewById(R.id.agentRowItemimageview)

        agentName.text = list.get(position).displayName
        Glide.with(holder.view).load(list.get(position).displayIcon).into(agentImage)
    }

}