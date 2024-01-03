package com.example.apigato.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.apigato.data.model.Breeds
import com.example.apigato.databinding.VistaceldaBinding

class MyAdapter(val listener: OnPersonajeClickListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(), Filterable {

    interface OnPersonajeClickListener {

        fun onClick(michis: Breeds)

    }

    private var dataList = ArrayList<Breeds?>()

    private var listacopia = ArrayList<Breeds?>()

    inner class MyViewHolder(val binding: VistaceldaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = VistaceldaBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {

        return dataList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val michis = dataList[position]

        holder.itemView.setOnClickListener {
            if (michis != null) {
                listener.onClick(michis)
            }
        }

        holder.binding.nombre.text = michis?.name

        holder.binding.origen.text = michis?.origin


    }

    fun ascendente(){

        dataList.sortBy { it?.name }

        notifyDataSetChanged()

    }

    fun descendente(){

        dataList.sortByDescending { it?.name }

        notifyDataSetChanged()

    }

    fun update(list: List<Breeds?>){

        dataList.clear()

        dataList.addAll(list)

        listacopia.clear()

        listacopia.addAll(list)

        notifyDataSetChanged()

    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val busqueda = constraint.toString()

                if(busqueda .isEmpty()){

                    dataList = listacopia

                }else{

                    dataList = listacopia.filter {

                        it?.name?.lowercase()?.contains(busqueda.lowercase()) ?: false

                    } as ArrayList<Breeds?>

                }

                val filterResult = FilterResults()

                filterResult.values = dataList

                return filterResult

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                dataList = results?.values as ArrayList<Breeds?>

                notifyDataSetChanged()

            }
        }
    }

}