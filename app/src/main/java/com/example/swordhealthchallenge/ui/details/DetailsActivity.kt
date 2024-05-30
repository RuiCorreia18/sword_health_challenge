package com.example.swordhealthchallenge.ui.details

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.swordhealthchallenge.MainApplication
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.ActivityDetailsBinding
import com.example.swordhealthchallenge.domain.model.CatDetailsDomainModel
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    private var _binding: ActivityDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        (this.application as MainApplication).appComponent.inject(this)

        _binding = ActivityDetailsBinding.bind(findViewById(R.id.activityDetailsLayout))

        viewModel.catDetails.observe(this) { catDetails ->
            updateDetails(catDetails)
        }
        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        val catId = intent.getStringExtra("catId").orEmpty()
        val catImageUrl = intent.getStringExtra("catImageUrl").orEmpty()
        val catFavouriteId = intent.getStringExtra("catFavouriteId").orEmpty()

        viewModel.getCatDetails(catId, catImageUrl, catFavouriteId)
    }

    private fun updateDetails(cat: CatDetailsDomainModel) {
        with(binding) {
            Glide.with(applicationContext).load(cat.imageUrl).into(catImageView)
            catBreedTextView.text = cat.breed
            catOriginTextView.text = getString(R.string.cat_origin, cat.origin)
            catTemperamentTextView.text = getString(R.string.cat_temperament, cat.temperament)
            catDescriptionTextView.text = getString(R.string.cat_description, cat.description)
            if (cat.favouriteId.isNotEmpty()) {
                catFavouriteImageView.setColorFilter(Color.GREEN)
            } else {
                catFavouriteImageView.setColorFilter(Color.BLACK)
            }

            catFavouriteImageView.setOnClickListener {
                if (cat.favouriteId.isEmpty()) {
                    viewModel.favouriteCat(cat.imageId)
                } else {
                    viewModel.deleteFavouriteCat(cat.favouriteId)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
