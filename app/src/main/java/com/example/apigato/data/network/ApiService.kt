package com.example.apigato.data.network

import com.example.apigato.data.model.Breeds
import com.example.apigato.data.votos.Boton_enviar
import com.example.apigato.data.votos.Peticion
import com.example.apigato.data.votos.Votes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @Headers("Content-Type:application/json","x-api-key:live_V2BTuwtgvLj8PIBZpyj07ChbQaO3TooNKq2lRUVoxVSMkraCQ3EJk8bRvGLMy9e5")
    @GET("breeds")
    suspend fun todoslosmichis(): Response<List<Breeds>>

    @Headers("Content-Type:application/json","x-api-key:live_V2BTuwtgvLj8PIBZpyj07ChbQaO3TooNKq2lRUVoxVSMkraCQ3EJk8bRvGLMy9e5")
    @GET("votes")
    suspend fun listavotos(): Response<List<Peticion>>

    @Headers("Content-Type:application/json","x-api-key:live_V2BTuwtgvLj8PIBZpyj07ChbQaO3TooNKq2lRUVoxVSMkraCQ3EJk8bRvGLMy9e5")
    @POST("votes")
    suspend fun enviarvoto(@Body votacion: Boton_enviar): Response<Votes>

    @Headers("Content-Type:application/json","x-api-key:live_V2BTuwtgvLj8PIBZpyj07ChbQaO3TooNKq2lRUVoxVSMkraCQ3EJk8bRvGLMy9e5")
    @DELETE("votes/{id_voto}")
    suspend fun eliminarvoto(@Path("id_voto") id_voto: Votes,): Response<Votes>





}