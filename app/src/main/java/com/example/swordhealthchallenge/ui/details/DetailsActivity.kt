package com.example.swordhealthchallenge.ui.details

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.swordhealthchallenge.MainApplication
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.ActivityDetailsBinding
import com.example.swordhealthchallenge.domain.Model.CatDetails
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        (this.application as MainApplication).appComponent.inject(this)

        binding = ActivityDetailsBinding.bind(findViewById(R.id.activityDetailsLayout))

        viewModel.catDetails.observe(this) { catDetails ->
            updateDetails(catDetails)
        }

        val catId = intent.getStringExtra("catId")!!
        val catImageUrl = intent.getStringExtra("catImageUrl").orEmpty()
        val isCatFavourite = intent.getBooleanExtra("isCatFavourite", false)

        viewModel.getCatDetails(catId, catImageUrl, isCatFavourite)

    }

    private fun updateDetails(cat: CatDetails) {
        with(binding) {
            Glide.with(root.context).load(cat.imageUrl).into(catImageView)
            catBreedTextView.text = cat.breed
            catOriginTextView.text = getString(R.string.cat_origin, cat.origin)
            catTemperamentTextView.text = getString(R.string.cat_temperament, cat.temperament)
            catDescriptionTextView.text = getString(R.string.cat_description, cat.description)
            if(cat.favourite){
                catFavouriteImageView.setColorFilter(Color.GREEN)
            }else{
                catFavouriteImageView.setColorFilter(Color.BLACK)
            }
        }
    }

}