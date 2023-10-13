package com.example.valodata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valodata.R
import com.example.valodata.adapter.AgentAdapter
import com.example.valodata.model.Agent
import com.example.valodata.viewmodel.CharacterViewModel

class CharacterListFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var agentRecylerView : RecyclerView
    private lateinit var agentsProgress : ProgressBar
    lateinit var agentAdapter : AgentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_character_list, container, false)
        agentRecylerView = view.findViewById(R.id.charactersRecylerView)
        agentsProgress = view.findViewById(R.id.characterListProgressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        characterViewModel.refreshData()

        observeLiveData()
    }

    fun observeLiveData(){
        characterViewModel.agents.observe(viewLifecycleOwner, Observer {agents->
            var agentList = ArrayList<Agent>()
            agents?.let {
                for(agent in agents){
                    if(agent.isPlayableCharacter){
                        agentList.add(agent)
                    }
                }
                agentRecylerView.visibility = View.VISIBLE
                agentRecylerView.layoutManager = GridLayoutManager(context,2)
                agentAdapter = AgentAdapter(agentList)
                agentRecylerView.adapter = agentAdapter
            }
        })

        characterViewModel.agentsProgressbar.observe(viewLifecycleOwner, Observer {progress->
            progress?.let {
                if(progress){
                    agentsProgress.visibility = View.VISIBLE
                    agentRecylerView.visibility = View.GONE
                }
                else{
                    agentsProgress.visibility = View.GONE
                    agentRecylerView.visibility = View.VISIBLE
                }
            }
        })
    }

}