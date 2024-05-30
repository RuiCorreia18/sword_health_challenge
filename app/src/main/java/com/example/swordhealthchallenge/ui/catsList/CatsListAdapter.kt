package com.example.swordhealthchallenge.ui.catsList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swordhealthchallenge.databinding.CatListItemBinding
import com.example.swordhealthchallenge.domain.model.CatDomainModel

class CatsListAdapter(
    private val postFavouriteCat: (String) -> Unit = {},
    private val deleteFavouriteCat: (String) -> Unit = {},
    private val onCardClick: (String, String, String) -> (Unit)
) : RecyclerView.Adapter<CatsListAdapter.CatsListViewHolder>() {

    private var catsList = emptyList<CatDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsListViewHolder {
        val itemBinding = CatListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CatsListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CatsListViewHolder, position: Int) {
        holder.bind(catsList[position])
    }

    override fun getItemCount(): Int = catsList.size

    fun updateCatsList(catDomainModels: List<CatDomainModel>) {
        catsList = catDomainModels
        notifyDataSetChanged()
    }

    inner class CatsListViewHolder(
        private val binding: CatListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(catDomainModel: CatDomainModel) {
            with(binding) {
                catBreedTextView.text = catDomainModel.breed
                Glide.with(root.context).load(catDomainModel.imageUrl).into(catImageView)
                favouriteImageViewColorPick(catDomainModel)
            }

            favouriteImageViewClickListener(catDomainModel)
            catCardClickListener(catDomainModel)
        }

        private fun CatListItemBinding.favouriteImageViewColorPick(catDomainModel: CatDomainModel) {
            if (catDomainModel.favouriteId.isNotEmpty()) {
                catFavouriteImageView.setColorFilter(Color.GREEN)
            } else {
                catFavouriteImageView.setColorFilter(Color.BLACK)
            }
        }

        private fun favouriteImageViewClickListener(catDomainModel: CatDomainModel) {
            binding.catFavouriteImageView.setOnClickListener {
                if (catDomainModel.favouriteId.isEmpty()) {
                    postFavouriteCat.invoke(catDomainModel.imageId)
                } else {
                    deleteFavouriteCat.invoke(catDomainModel.favouriteId)
                }
            }
        }

        private fun catCardClickListener(catDomainModel: CatDomainModel) {
            binding.catItem.setOnClickListener {
                onCardClick.invoke(
                    catDomainModel.id,
                    catDomainModel.imageUrl,
                    catDomainModel.favouriteId
                )
            }
        }
    }
}
