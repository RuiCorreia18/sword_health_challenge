package com.example.swordhealthchallenge.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swordhealthchallenge.MainApplication
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.FragmentFavouritesBinding
import javax.inject.Inject

private const val RECYCLER_GRID_COLUMN_NUMBER = 3

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var catsListAdapter: FavouritesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavouritesViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as MainApplication).appComponent.inject(this)

        catsListAdapter = FavouritesAdapter(
            onCardClick = { id, url, favId -> openCatDetails(id, url, favId) },
            onFavouriteClick = { viewModel.deleteFavouriteCat(it) },
        )

        binding.catListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, RECYCLER_GRID_COLUMN_NUMBER)
            adapter = catsListAdapter
        }

        viewModel.favouriteCatsList.observe(viewLifecycleOwner) { catsList ->
            catsListAdapter.updateCatsList(catsList)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        viewModel.getFavouriteCats()
    }

    private fun openCatDetails(catId: String, catImageUrl: String, favouriteId: String) {
        val bundle = Bundle().apply {
            putString("catId", catId)
            putString("catImageUrl", catImageUrl)
            putString("catFavouriteId", favouriteId)
        }

        findNavController().navigate(
            R.id.action_navigation_favourites_to_navigation_details,
            bundle
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
