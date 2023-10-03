package com.example.valodata.adapter

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valodata.R
import com.example.valodata.model.Ability

class AbilityAdapter(var list : ArrayList<Ability>) : RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {

    lateinit var abilityName : TextView
    lateinit var abilityDescription : TextView
    lateinit var abilityImage : ImageView

    class AbilityViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ability_column_item,parent,false)
        return AbilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        abilityName = holder.view.findViewById(R.id.abilityNameTextView)
        abilityImage = holder.view.findViewById(R.id.abilityImageView)
        abilityDescription = holder.view.findViewById(R.id.abilityDescriptionTextView)

        abilityName.text = list.get(position).displayName
        abilityDescription.text = list.get(position).description
        Glide.with(holder.view).load(list.get(position).displayIcon).into(abilityImage)
    }
}