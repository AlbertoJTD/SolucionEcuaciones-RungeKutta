package com.example.rungekutta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_resultados.view.*

class ResultadosAdapter (val resultados:ArrayList<String>): RecyclerView.Adapter<ResultadosAdapter.ResultHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResultHolder(layoutInflater.inflate(R.layout.item_resultados, parent, false))
    }

    override fun getItemCount(): Int {
        return resultados.size
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.render(resultados[position])
    }


    class ResultHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(resultados: String){
            view.tvResul.text = resultados
        }
    }
}