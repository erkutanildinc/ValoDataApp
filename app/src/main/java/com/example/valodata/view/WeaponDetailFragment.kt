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
import com.example.valodata.viewmodel.AgentDetailViewModel
import com.example.valodata.viewmodel.WeaponDetailViewModel

class WeaponDetailFragment : Fragment() {

    private lateinit var weaponDetailProgressBar : ProgressBar
    private lateinit var weaponDetailLiveModel: WeaponDetailViewModel
    private lateinit var weaponImageView : ImageView
    private lateinit var weaponNameTextView : TextView
    private lateinit var weaponCategoryTextView: TextView
    private lateinit var weaponCostTextView : TextView
    private lateinit var weaponReloadTextView : TextView
    private lateinit var weaponFireTextView : TextView
    private lateinit var weaponMagazineTextView : TextView
    private lateinit var weaponHeadDamTextView : TextView
    private lateinit var weaponBodyDamTextView : TextView
    private lateinit var weaponLegDamTextView : TextView
    private lateinit var weaponUUID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weapon_detail, container, false)

        weaponImageView = view.findViewById(R.id.weaponDetailImageView)
        weaponNameTextView = view.findViewById(R.id.weaponDetailNameTextView)
        weaponCategoryTextView = view.findViewById(R.id.weaponDetailCategoryTextView)
        weaponCostTextView = view.findViewById(R.id.weaponDetailCostTextView)
        weaponReloadTextView = view.findViewById(R.id.weaponDetailReloadTimeTextView)
        weaponFireTextView = view.findViewById(R.id.weaponDetailFireRateTextView)
        weaponMagazineTextView = view.findViewById(R.id.weaponDetailMagazineTextView)
        weaponHeadDamTextView = view.findViewById(R.id.weaponDetailHeadDamTextView)
        weaponBodyDamTextView = view.findViewById(R.id.weaponDetailBodyDamTextView)
        weaponLegDamTextView = view.findViewById(R.id.weaponDetailLegDamTextView)
        weaponDetailProgressBar = view.findViewById(R.id.weaponDetailProgressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            weaponUUID = WeaponDetailFragmentArgs.fromBundle(it).weaponUUID
        }

        weaponDetailLiveModel = ViewModelProvider(this).get(WeaponDetailViewModel::class.java)
        weaponDetailLiveModel.getWeaponData(weaponUUID)

        observeLiveData()
    }

    fun observeLiveData(){


        weaponNameTextView.visibility = View.GONE
        weaponCategoryTextView.visibility = View.GONE
        weaponCostTextView.visibility = View.GONE
        weaponReloadTextView.visibility = View.GONE
        weaponFireTextView.visibility = View.GONE
        weaponMagazineTextView.visibility = View.GONE
        weaponImageView.visibility = View.GONE
        weaponHeadDamTextView.visibility = View.GONE
        weaponBodyDamTextView.visibility = View.GONE
        weaponLegDamTextView.visibility = View.GONE

        weaponDetailLiveModel.weaponLiveData.observe(viewLifecycleOwner, Observer {weapon->

            weaponNameTextView.visibility = View.VISIBLE
            weaponCategoryTextView.visibility = View.VISIBLE
            weaponCostTextView.visibility = View.VISIBLE
            weaponReloadTextView.visibility = View.VISIBLE
            weaponFireTextView.visibility = View.VISIBLE
            weaponMagazineTextView.visibility = View.VISIBLE
            weaponImageView.visibility = View.VISIBLE
            weaponHeadDamTextView.visibility = View.VISIBLE
            weaponBodyDamTextView.visibility = View.VISIBLE
            weaponLegDamTextView.visibility = View.VISIBLE

            weaponNameTextView.text = weapon.displayName.toString().uppercase()
            weaponCategoryTextView.text = weapon.shopData?.categoryText ?: "Category"
            weaponCostTextView.text = "Cost:" + weapon.shopData?.cost.toString()
            weaponReloadTextView.text = "Reload Time: "+weapon.weaponStats?.reloadTimeSeconds.toString()
            weaponMagazineTextView.text = "Magazine: " +weapon.weaponStats?.magazineSize.toString()
            weaponFireTextView.text = "Fire Rate: "+weapon.weaponStats?.fireRate.toString()
            if(weapon.weaponStats?.damageRanges?.size!! >=2){
                weaponHeadDamTextView.text = weapon.weaponStats?.damageRanges?.get(1)?.headDamage?.toInt()
                    .toString() + " - " + weapon.weaponStats?.damageRanges?.get(0)?.headDamage?.toInt().toString()
                weaponBodyDamTextView.text = weapon.weaponStats?.damageRanges?.get(1)?.bodyDamage?.toInt()
                    .toString() + " - " + weapon.weaponStats?.damageRanges?.get(0)?.bodyDamage?.toInt().toString()
                weaponLegDamTextView.text = weapon.weaponStats?.damageRanges?.get(1)?.legDamage?.toInt()
                    .toString() + " - " + weapon.weaponStats?.damageRanges?.get(0)?.legDamage?.toInt().toString()
            }
            else{
                weaponHeadDamTextView.text = weapon.weaponStats?.damageRanges?.get(0)?.headDamage?.toInt().toString()
                weaponBodyDamTextView.text = weapon.weaponStats?.damageRanges?.get(0)?.bodyDamage?.toInt().toString()
                weaponLegDamTextView.text =  weapon.weaponStats?.damageRanges?.get(0)?.legDamage?.toInt().toString()
            }
            context?.let {
                Glide.with(it).load(weapon.displayIcon).into(weaponImageView)
            }
        })

        weaponDetailLiveModel.weaponDetailProgressbar.observe(viewLifecycleOwner, Observer { progress ->
            progress?.let {
                if (progress) {
                    weaponDetailProgressBar.visibility = View.VISIBLE
                } else {
                    weaponDetailProgressBar.visibility = View.GONE
                }
            }
        })
    }

}