package com.example.apigato.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.apigato.databinding.FragmentStatsBinding
import com.example.apigato.ui.MyViewModel

class Fragment_Stats : Fragment() {

    private var _binding: FragmentStatsBinding? = null

    private val binding get() = _binding!!

    val charModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charModel.michiSeleccionado.observe(viewLifecycleOwner){

            binding.adaptability.text = it.adaptability.toString()

            binding.affectionLevel.text = it.affectionLevel.toString()

            binding.childFriendly.text = it.childFriendly.toString()

            binding.dogFriendly.text = it.dogFriendly.toString()

            binding.energyLevel.text = it.energyLevel.toString()

            binding.grooming.text = it.grooming.toString()

            binding.healthIssues.text = it.healthIssues.toString()

            binding.intelligence.text = it.intelligence.toString()

            binding.sheddingLevel.text = it.sheddingLevel.toString()

            binding.socialNeeds.text = it.socialNeeds.toString()

            binding.strangerFriendly.text = it.strangerFriendly.toString()

        }

    }
}