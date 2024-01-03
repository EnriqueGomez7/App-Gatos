package com.example.apigato.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apigato.R
import com.example.apigato.databinding.FragmentVotosBinding
import com.example.apigato.databinding.VistaVotosBinding
import com.example.apigato.ui.MyViewModel
import com.example.apigato.ui.activities.MainActivity
import com.example.apigato.ui.adapters.VotosAdapter

class Fragment_Votos : Fragment() {

    private var _binding: FragmentVotosBinding? = null

    private val binding get() = _binding!!

    val charModel by activityViewModels<MyViewModel>()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        _binding = FragmentVotosBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setTitleTextColor(Color.WHITE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu2, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return false

            }

        }, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)

        val recyclerView = binding.recyclerview

        val llm = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)

        recyclerView.layoutManager = llm

        binding.swipe.setOnRefreshListener {

            charModel.cargarvotos()

        }

        val adapter = VotosAdapter()

        recyclerView.adapter = adapter

        charModel.cargarvotos()

        charModel.listaLivePeticion.observe(viewLifecycleOwner) { lista ->

            binding.swipe.isRefreshing = false

            binding.toolbar.title = "Mi lista de votos"

            if (lista != null)
                adapter.update(lista)
        }

    }
}