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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apigato.R
import com.example.apigato.data.model.Breeds
import com.example.apigato.databinding.FragmentPrincipalBinding
import com.example.apigato.ui.activities.MainActivity
import com.example.apigato.ui.adapters.MyAdapter
import com.example.apigato.ui.MyViewModel

class Fragment_Principal : Fragment() {

    private lateinit var binding: FragmentPrincipalBinding

    private lateinit var adapter: MyAdapter

    val charModel by activityViewModels<MyViewModel>(){

        MyViewModel.MyViewModelFactory(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentPrincipalBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)

        binding.toolbar.setTitleTextColor(Color.WHITE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu, menu)

                val menuItem = menu.findItem(R.id.app_bar_search)

                val searchView = menuItem.actionView as android.widget.SearchView

                searchView.setOnQueryTextListener(object :

                    android.widget.SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {

                        adapter.filter.filter(query)

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        adapter.filter.filter(newText)

                        return true

                    }

                })

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                when(menuItem.itemId){

                    R.id.arriba -> adapter.ascendente()

                    R.id.abajo -> adapter.descendente()

                }

                return false
            }

        }, viewLifecycleOwner, androidx.lifecycle.Lifecycle.State.RESUMED)

        val recyclerView = binding.recyclerview

        val llm = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)

        recyclerView.layoutManager = llm

        binding.swipe.setOnRefreshListener {

            charModel.cargar()

        }

        binding.listavotos.setOnClickListener {

            findNavController().navigate(R.id.action_fragment__Principal_to_fragment_Votos)

        }

        adapter = MyAdapter (object : MyAdapter.OnPersonajeClickListener {

            override fun onClick(michis: Breeds) {

                charModel.elegirMichi(michis)

                findNavController().navigate(R.id.action_fragment_Principal_to_fragment_Detalle)

            }

        })

        recyclerView.adapter = adapter

        charModel.cargar()

        charModel.listaLiveData.observe(viewLifecycleOwner) { lista ->

            binding.swipe.isRefreshing = false

            if (lista != null)
                adapter.update(lista)
        }

    }

}