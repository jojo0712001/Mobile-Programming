package com.example.mobileprogramming.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.mobileprogramming.R

class CatDetailFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigateToCatListFragment)
        }

        val name: String? = arguments?.getString("catname")
        val origin: String = arguments?.getString("cat-origin") ?:"ne"
        val lifespan = arguments?.getString("cat-life-span") ?:"marche"
        val temperament = arguments?.getString("cat-temperament") ?:"pas"
        val affectionlevel = arguments?.getInt("cat-affection-level") ?:0
        val image = arguments?.getString("cat-image") ?:""

        val textViewName: TextView = view.findViewById(R.id.textview_name)
        val textViewOrigin: TextView = view.findViewById(R.id.textview_origin)
        val textViewLifeSpan: TextView = view.findViewById(R.id.textview_life_span)
        val textViewTemperament: TextView = view.findViewById(R.id.textview_temperament)
        val textViewAffectionLevel: TextView = view.findViewById(R.id.textview_affection_level)
        val imageViewImage: ImageView = view.findViewById(R.id.imageview_image)

        textViewName.text = name
        textViewAffectionLevel.text = "Affection Level: " + affectionlevel
        textViewLifeSpan.text = "Life Span: " + lifespan + " years"
        textViewOrigin.text = "Origin: " + origin
        textViewTemperament.text = "Teamperament: " +temperament

        Glide
                .with(this)
                .load(image)
                .centerCrop()
                .into(imageViewImage)
    }

}
