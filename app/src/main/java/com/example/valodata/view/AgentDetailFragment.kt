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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valodata.R
import com.example.valodata.adapter.AbilityAdapter
import com.example.valodata.adapter.AgentAdapter
import com.example.valodata.model.Ability
import com.example.valodata.model.Agent
import com.example.valodata.viewmodel.AgentDetailViewModel
import com.example.valodata.viewmodel.CharacterViewModel


class AgentDetailFragment : Fragment() {

    private lateinit var agentDetailProgressBar : ProgressBar
    private lateinit var agentDetailLiveModel: AgentDetailViewModel
    private lateinit var agentBackgroundImageView : ImageView
    private lateinit var agentPortraitImageView : ImageView
    private lateinit var agentCategoryImageView : ImageView
    private lateinit var agentNameTextView : TextView
    private lateinit var agentRoleTextView: TextView
    private lateinit var agentDescriptionTextView : TextView
    private lateinit var agentAbilityRecyclerList : RecyclerView
    private lateinit var agentUUID : String
    private lateinit var abilityAdapter: AbilityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_agent_detail, container, false)

        agentBackgroundImageView = view.findViewById(R.id.agentDetailBackgroundPic)
        agentPortraitImageView = view.findViewById(R.id.agentDetailPortrait)
        agentNameTextView = view.findViewById(R.id.agentDetailName)
        agentRoleTextView = view.findViewById(R.id.agentDetailRoleText)
        agentDescriptionTextView = view.findViewById(R.id.agentDetailDescription)
        agentDetailProgressBar = view.findViewById(R.id.agentDetailProgressBar)
        agentAbilityRecyclerList = view.findViewById(R.id.agentDetailAbilityRecyclerView)
        agentCategoryImageView = view.findViewById(R.id.agentDetailCategoryImage)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            agentUUID = AgentDetailFragmentArgs.fromBundle(it).agentUUID
        }

        agentDetailLiveModel = ViewModelProvider(this).get(AgentDetailViewModel::class.java)
        agentDetailLiveModel.getAgentData(agentUUID)

        observeLiveData()
    }

    fun observeLiveData() {

        agentBackgroundImageView.visibility = View.GONE
        agentPortraitImageView.visibility = View.GONE
        agentNameTextView.visibility = View.GONE
        agentDescriptionTextView.visibility = View.GONE
        agentRoleTextView.visibility = View.GONE
        agentCategoryImageView.visibility = View.GONE


        agentDetailLiveModel.agentLiveData.observe(viewLifecycleOwner, Observer { agent ->
            agentBackgroundImageView.visibility = View.VISIBLE
            agentPortraitImageView.visibility = View.VISIBLE
            agentNameTextView.visibility = View.VISIBLE
            agentDescriptionTextView.visibility = View.VISIBLE
            agentRoleTextView.visibility = View.VISIBLE
            agentCategoryImageView.visibility = View.VISIBLE
            agentNameTextView.text = agent.displayName.uppercase()
            agentDescriptionTextView.text = agent.description
            agentAbilityRecyclerList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            abilityAdapter = AbilityAdapter(agent.abilities as ArrayList<Ability>)
            agentAbilityRecyclerList.adapter = abilityAdapter
            agentRoleTextView.text = agent.role?.displayName?.name ?: "Bom"
            context?.let {
                Glide.with(it).load(agent.fullPortrait).into(agentPortraitImageView)
                Glide.with(it).load(agent.background).into(agentBackgroundImageView)
                Glide.with(it).load(agent.role?.displayIcon).into(agentCategoryImageView)
                print("asdasdasd")
            }
        })
        agentDetailLiveModel.agentDetailProgressBar.observe(
            viewLifecycleOwner,
            Observer { progress ->
                progress?.let {
                    if (progress) {
                        agentDetailProgressBar.visibility = View.VISIBLE
                    } else {
                        agentDetailProgressBar.visibility = View.GONE
                    }
                }
            })
    }
}