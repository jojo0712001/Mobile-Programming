package com.example.mobileprogramming.presentation.list

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = CatAdapter(listOf(), ::onClickedCat)

    //private val sharedPref : SharedPreferences? = activity?.getSharedPreferences("app", Context.MODE_PRIVATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.cat_recyclerview)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CatListFragment.adapter
        }
        callApi()
    }

    private fun callApi() {
        Singletons.catApi.getCatList().enqueue(object : Callback<List<Cat>> {
            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                //TODO("not implemented")
            }
            //49min

            override fun onResponse(
                call: Call<List<Cat>>,
                response: Response<List<Cat>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val catResponse = response.body()!!
                    showList(catResponse)
                }
            }
        })
    }

    private fun showList (catList: List<Cat>) {
        adapter.updateList(catList)
    }

    private fun onClickedCat(cat: Cat) {
            findNavController().navigate(R.id.navigateToCatDetailFragment, bundleOf(
                "catname" to cat.name,
                "cat-origin" to cat.origin,
                "cat-life-span" to cat.life_span,
                "cat-affection-level" to cat.affection_level,
                "cat-temperament" to cat.temperament,
                "cat-image" to cat.image.url
            ))
        }
}