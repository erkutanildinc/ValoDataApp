package com.example.valodata.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import com.example.valodata.R
import com.example.valodata.viewmodel.CategoriesViewModel

class CategoriesFragment : Fragment() {

    private lateinit var categoriesViewModel: CategoriesViewModel

    private lateinit var agentsButton : AppCompatButton
    private lateinit var mapsButton : AppCompatButton
    private lateinit var weaponsButton : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        agentsButton = view.findViewById(R.id.charactersButton)
        mapsButton = view.findViewById(R.id.mapsButton)
        weaponsButton = view.findViewById(R.id.weaponsButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        agentsButton.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToCharacterListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        mapsButton.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToMapListFragment()
            Navigation.findNavController(it).navigate(action)
        }

        weaponsButton.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToWeaponListFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}