package com.example.apigato.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.apigato.databinding.FragmentInformationBinding
import com.example.apigato.ui.MyViewModel

class Fragment_Information : Fragment() {

    private var _binding: FragmentInformationBinding? = null

    private val binding get() = _binding!!

    val charModel by activityViewModels<MyViewModel>()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        _binding = FragmentInformationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charModel.michiSeleccionado.observe(viewLifecycleOwner){

            binding.weight.text = "${it.weight?.metric} Kg"

            binding.temperament.text = it.temperament

            binding.origin.text = it.origin

            binding.lifeSpan.text = it.lifeSpan

            binding.description.text = it.description

        }

    }
}