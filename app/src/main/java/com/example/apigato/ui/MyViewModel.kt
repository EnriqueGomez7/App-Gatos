package com.example.apigato.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apigato.data.Repository
import com.example.apigato.data.model.Breeds
import com.example.apigato.data.votos.Boton_enviar
import com.example.apigato.data.votos.Peticion
import com.example.apigato.data.votos.Votes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel (val context: Context): ViewModel() {

    private val repository = Repository(context)

    val listaLiveData = MutableLiveData<List<Breeds?>?>()

    val listaLivePeticion = MutableLiveData<List<Peticion>?>()

    val listaLiveVotos = MutableLiveData<Votes?>()

    val michiSeleccionado = MutableLiveData<Breeds>()

    fun cargar(){

        CoroutineScope(Dispatchers.IO).launch {

            val respuesta = repository.getmichis()

            val code = respuesta.code()

            if(code == 200){

                val mirespuesta = respuesta.body()

                val listmichi = mirespuesta

                listaLiveData.postValue(listmichi)

            }

        }



    }

    fun enviarvotos(imageId: String, sub_id: String, value: Int){

        val votacion = Boton_enviar(imageId, sub_id, value)

        CoroutineScope(Dispatchers.IO).launch {

            val respuesta = repository.enviarfoto(votacion)

            val code = respuesta.code()

            if(code == 200){

                val mirespuesta = respuesta.body()

                val listmichi = mirespuesta

                listaLiveVotos.postValue(listmichi)

            }

        }

    }

    fun eliminarvoto(vote_id: Int, message: String){

        val voto = Votes(vote_id, message)

        CoroutineScope(Dispatchers.IO).launch {

            val respuesta = repository.eliminarvoto(voto)

            val code = respuesta.code()

            if(code == 200){

                val mirespuesta = respuesta.body()

                val listmichi = mirespuesta

                listaLiveVotos.postValue(listmichi)

            }

        }

    }

    fun cargarvotos(){

        CoroutineScope(Dispatchers.IO).launch {

            val respuesta = repository.getvotes()

            val code = respuesta.code()

            if(code == 200){

                val mirespuesta = respuesta.body()

                val listmichi = mirespuesta

                listaLivePeticion.postValue(listmichi)

            }

        }

    }

    fun elegirMichi(michis: Breeds) {

        michiSeleccionado.value = michis

    }

    class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }

    }

}