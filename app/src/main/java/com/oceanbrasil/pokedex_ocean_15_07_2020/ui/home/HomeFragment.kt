package com.oceanbrasil.pokedex_ocean_15_07_2020.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.oceanbrasil.pokedex_ocean_15_07_2020.R
import com.oceanbrasil.pokedex_ocean_15_07_2020.api.PokemonApiRepository
import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonsResult
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val repository = PokemonApiRepository()

        val call = repository.listarPokemons()

        call.enqueue(object : Callback<PokemonsResult> {
            override fun onFailure(call: Call<PokemonsResult>, t: Throwable) {
                container?.rootView?.let {
                    Snackbar.make(root, "Erro ao carregar os pokémons.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Tentar novamente", View.OnClickListener {
                            Toast.makeText(context, "Carregar API novamente.", Toast.LENGTH_LONG).show()
                        }).show()
                }

                Log.e("POKEMONS_API", "Erro ao carregar os pokémons.", t)
            }

            override fun onResponse(
                call: Call<PokemonsResult>,
                response: Response<PokemonsResult>
            ) {
                val listaPokemons = response.body()?.results ?: emptyList()

                Log.d("POKEMONS_API", listaPokemons.count().toString())

                val recyclerView: RecyclerView = root.findViewById(R.id.rvPokemons)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = PokemonsAdapter(listaPokemons)
            }
        })

//        val textView: TextView = root.findViewById(R.id.text_home)
//
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }
}
