package com.example.mobileprogramming.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogramming.R
import com.example.mobileprogramming.presentation.api.PokeApi
import com.example.mobileprogramming.presentation.api.PokemonResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = PokemonAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)

        recyclerView.apply {
            layoutManager = this@PokemonListFragment.layoutManager
            adapter = this@PokemonListFragment.adapter
        }

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl( "https://pokeapi.co/api/v2/")
            .build()

        val pokeApi: PokeApi = retrofit.create(PokeApi::class.java)

        pokeApi.getPokemonList().enqueue(object: Callback<PokemonResponse>{
            override fun onfailure(
                call: Call<PokemonResponse>,
                t: Throwable
            )

        })

        val pokeList : List<Pokemon> = arrayListOf<Pokemon>().apply {
            add(Pokemon("pikatchu"))
            add(Pokemon("Salameche"))
            add(Pokemon("Carapuce"))
        }

        adapter.updateList(pokeList)

    }
}