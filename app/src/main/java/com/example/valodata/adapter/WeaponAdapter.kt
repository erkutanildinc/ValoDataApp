package com.example.valodata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valodata.R
import com.example.valodata.model.Weapon

class WeaponAdapter(var list : ArrayList<Weapon>) : RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {

    lateinit var weaponName: TextView
    lateinit var weaponImage: ImageView

    class WeaponViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.weapon_row_item, parent, false)
        return WeaponViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        weaponName = holder.view.findViewById(R.id.weaponItemNameTextView)
        weaponImage = holder.view.findViewById(R.id.weaponItemImageView)

        weaponName.text = list.get(position).displayName.toString().uppercase()
        Glide.with(holder.view).load(list.get(position).displayIcon).into(weaponImage)
    }
}