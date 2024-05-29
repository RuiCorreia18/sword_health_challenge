package com.example.swordhealthchallenge.ui.catsList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swordhealthchallenge.databinding.CatListItemBinding
import com.example.swordhealthchallenge.domain.model.Cat

class CatsListAdapter(
    private var catsList: List<Cat>,
    private val postFavouriteCat: (String) -> Unit = {},
    private val deleteFavouriteCat: (String) -> Unit = {},
    private val onCardClick:(String,String,String) -> (Unit)
) : RecyclerView.Adapter<CatsListAdapter.CatsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsListViewHolder {
        val itemBinding = CatListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CatsListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CatsListViewHolder, position: Int) {
        holder.bind(catsList[position])
    }

    override fun getItemCount(): Int = catsList.size

    fun updateCatsList(cats: List<Cat>) {
        catsList = cats
        notifyDataSetChanged()
    }

    inner class CatsListViewHolder(
        private val binding: CatListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            with(binding) {
                catBreedTextView.text = cat.breed
                Glide.with(root.context).load(cat.imageUrl).into(catImageView)
                if(cat.favouriteId.isNotEmpty()){
                    catFavouriteImageView.setColorFilter(Color.GREEN)
                }else{
                    catFavouriteImageView.setColorFilter(Color.BLACK)
                }
            }

            binding.catFavouriteImageView.setOnClickListener {
                if(cat.favouriteId.isEmpty()){
                    postFavouriteCat.invoke(cat.imageId)
                }else{
                    deleteFavouriteCat.invoke(cat.favouriteId)
                }
            }

            binding.catItem.setOnClickListener {
                onCardClick.invoke(cat.id, cat.imageUrl, cat.favouriteId)

            }
        }
    }
}
