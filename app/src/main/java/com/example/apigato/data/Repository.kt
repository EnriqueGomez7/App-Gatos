package com.example.apigato.data

import android.content.Context
import com.example.apigato.data.network.RetrofitHelper
import com.example.apigato.data.votos.Boton_enviar
import com.example.apigato.data.votos.Votes

class Repository (val context: Context) {

    private val apiService = RetrofitHelper.getRetrofit()

    suspend fun getmichis() = apiService.todoslosmichis()

    suspend fun getvotes() = apiService.listavotos()

    suspend fun enviarfoto(votacion: Boton_enviar) = apiService.enviarvoto(votacion)

    suspend fun eliminarvoto(votes: Votes) = apiService.eliminarvoto(votes)

}