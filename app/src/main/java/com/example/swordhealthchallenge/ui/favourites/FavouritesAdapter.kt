package com.example.swordhealthchallenge.ui.favourites

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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
                favouriteImageViewColorPick(cat)
            }

            favouriteImageViewClickListener(cat)
            catCardClickListener(cat)
        }

        private fun FavouriteItemBinding.favouriteImageViewColorPick(cat: FavouriteCatDomainModel) {
            if (cat.favouriteId.isNotEmpty()) {
                catFavouriteImageView.setColorFilter(Color.GREEN)
            } else {
                catFavouriteImageView.setColorFilter(Color.BLACK)
            }
        }

        private fun favouriteImageViewClickListener(cat: FavouriteCatDomainModel) {
            binding.catFavouriteImageView.setOnClickListener {
                onFavouriteClick.invoke(cat.favouriteId)
            }
        }

        private fun catCardClickListener(cat: FavouriteCatDomainModel) {
            binding.catItem.setOnClickListener {
                onCardClick.invoke(cat.id, cat.imageUrl, cat.favouriteId)
            }
        }
    }
}

class FavouriteCatDomainModelDiffUtilCallback(
    private val oldList: List<FavouriteCatDomainModel>,
    private val newList: List<FavouriteCatDomainModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
