package com.example.apigato.ui.adapters

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apigato.R
import com.example.apigato.data.Repository
import com.example.apigato.data.network.RetrofitHelper
import com.example.apigato.data.votos.Peticion
import com.example.apigato.data.votos.Votes
import com.example.apigato.databinding.VistaVotosBinding
import com.example.apigato.ui.MyViewModel
import com.example.apigato.ui.fragments.Fragment_Votos


class VotosAdapter: RecyclerView.Adapter<VotosAdapter.MyViewHolder>() {

    private var dataList = ArrayList<Peticion?>()

    inner class MyViewHolder(val binding: VistaVotosBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = VistaVotosBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {

        return dataList.size

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val votos = dataList[position]

        holder.binding.fecha.text = votos?.createdAt

        Glide.with(holder.binding.root.context).load(votos?.image?.url).into(holder.binding.foto)

        holder.binding.eliminar.setOnClickListener {



        }

        if(votos?.value == 1){

            holder.binding.opinion.setImageResource(R.drawable.boton_like)

        }else{

            holder.binding.opinion.setImageResource(R.drawable.boton_dislike)

        }

    }



    fun update(list: List<Peticion?>){

        dataList.clear()

        dataList.addAll(list)

        notifyDataSetChanged()

    }

}