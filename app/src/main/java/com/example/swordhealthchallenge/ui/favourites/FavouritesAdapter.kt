package com.example.swordhealthchallenge.ui.favourites

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.FavouriteItemBinding
import com.example.swordhealthchallenge.domain.model.FavouriteCatDomainModel

class FavouritesAdapter(
    private val onCardClick: (String, String, String) -> (Unit),
    private val onFavouriteClick: (String) -> Unit = {},
) : RecyclerView.Adapter<FavouritesAdapter.FavouritesListViewHolder>() {

    private var favouritesList = emptyList<FavouriteCatDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesListViewHolder {
        val itemBinding = FavouriteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return FavouritesListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavouritesListViewHolder, position: Int) {
        holder.bind(favouritesList[position])
    }

    override fun getItemCount(): Int = favouritesList.size

    fun updateCatsList(cats: List<FavouriteCatDomainModel>) {
        favouritesList = cats
        notifyDataSetChanged()
    }


    inner class FavouritesListViewHolder(
        private val binding: FavouriteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: FavouriteCatDomainModel) {
            with(binding) {
                catBreedTextView.text = cat.breed
                catLifespanTextView.text =
                    root.context.getString(R.string.cat_lifespan, cat.lifeSpan)
                Glide.with(root.context).load(cat.imageUrl).into(catImageView)
                if (cat.favouriteId.isNotEmpty()) {
                    catFavouriteImageView.setColorFilter(Color.GREEN)
                } else {
                    catFavouriteImageView.setColorFilter(Color.BLACK)
                }
            }

            binding.catFavouriteImageView.setOnClickListener {
                onFavouriteClick.invoke(cat.favouriteId)
            }


            binding.catItem.setOnClickListener {
                Log.e("CLICK CAT FAV CARD", cat.id)
                onCardClick.invoke(cat.id, cat.imageUrl, cat.favouriteId)
            }
        }
    }
}
