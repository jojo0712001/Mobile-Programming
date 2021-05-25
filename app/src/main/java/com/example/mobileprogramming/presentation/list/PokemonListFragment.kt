package com.example.mobileprogramming.presentation.list

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogramming.R
import com.example.mobileprogramming.presentation.Singletons
import com.example.mobileprogramming.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)

    private fun onClickedPokemon(pokemon: Pokemon){
        findNavController().navigate(R.id.navigateToPokemonDetailFragment)
    }
    //7min38 Créer méthode onClickedPokemon

    //private val sharedPref : SharedPreferences? = activity?.getSharedPreferences("app", Context.MODE_PRIVATE)

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
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }


        callApi()

    }



    private fun callApi() {
        Singletons.pokeApi.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                //TODO("not implemented")
            }
            //49min

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val pokemonResponse = response.body()!!
                    showList(pokemonResponse.results)
                }
            }
        })
    }

    private fun showList (pokeList: List<Pokemon>) {
        adapter.updateList(pokeList)
    }

    private fun onClickedPokemon(id: Int) {
            findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf(
                "pokemonId" to (id+1)
            ))
        }
}
