package com.example.swordhealthchallenge.ui.catslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swordhealthchallenge.databinding.CatListItemBinding
import com.example.swordhealthchallenge.domain.Model.Cat

class CatsListAdapter(
    private var catsList: List<Cat>
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


    class CatsListViewHolder(
        private val binding: CatListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            with(binding) {
                catBreedTextView.text = cat.breed
                Glide.with(root.context).load(cat.imageUrl).into(catImageView)
            }
        }
    }
}
