package com.example.apigato.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.apigato.R
import com.example.apigato.databinding.FragmentDetalleBinding
import com.example.apigato.ui.activities.MainActivity
import com.example.apigato.ui.MyViewModel
import com.example.apigato.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class Fragment_Detalle : Fragment() {

    private var _binding: FragmentDetalleBinding? = null

    private val binding get() = _binding!!

    val charModel by activityViewModels<MyViewModel>()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        _binding = FragmentDetalleBinding.inflate(inflater, container, false)

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

        charModel.michiSeleccionado.observe(viewLifecycleOwner){

            binding.toolbar.title = it.name

            val url = it.wikipediaUrl

            val imageid = it.image?.id

            Glide.with(binding.root.context).load(it.image?.url).into(binding.imageView2)

            binding.Wiki.setOnClickListener {

                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                startActivity(intent)

            }

            val adapter = ViewPagerAdapter(this@Fragment_Detalle)
            binding.viewpager.adapter = adapter

            TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
                tab.text = if (position == 1) "Stats" else "Information"
            }.attach()

            binding.like.setOnClickListener {

                charModel.enviarvotos("$imageid", "Usuario", 1)

                Toast.makeText(context, "Miau!", Toast.LENGTH_SHORT).show()

            }

            binding.dislike.setOnClickListener {

                charModel.enviarvotos("$imageid", "Usuario", 0)

                Toast.makeText(context, "Miau...", Toast.LENGTH_SHORT).show()

            }

        }

    }
}