package com.oceanbrasil.pokedex_ocean_15_07_2020.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.oceanbrasil.pokedex_ocean_15_07_2020.R
import com.oceanbrasil.pokedex_ocean_15_07_2020.api.PokemonApiRepository
import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonInfoResult
import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonResult
import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonsResult
import kotlinx.android.synthetic.main.pokemon_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonsAdapter(private val items: List<PokemonResult>) :
    RecyclerView.Adapter<PokemonsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: PokemonResult) = with(itemView) {
            tvNome.text = item.name

            val imageView = ivPokemon

            val repository = PokemonApiRepository()

            val call = repository.visualizarPokemon(item.getId())

            call.enqueue(object : Callback<PokemonInfoResult> {
                override fun onFailure(call: Call<PokemonInfoResult>, t: Throwable) {
                    Log.e("POKEMONS_API", "Erro ao carregar o pokémon com ID $id.", t)
                }

                override fun onResponse(
                    call: Call<PokemonInfoResult>,
                    response: Response<PokemonInfoResult>
                ) {
                    response.body()?.let {
                        Log.d("POKEMONS_API", it.toString())

                        Glide
                            .with(imageView.context)
                            .load(it.sprites.front_default)
                            .into(imageView)
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }
}
