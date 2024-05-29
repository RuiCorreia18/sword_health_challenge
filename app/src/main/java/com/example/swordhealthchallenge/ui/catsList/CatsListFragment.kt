package com.example.swordhealthchallenge.ui.catsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swordhealthchallenge.MainApplication
import com.example.swordhealthchallenge.R
import com.example.swordhealthchallenge.databinding.FragmentCatsListBinding
import javax.inject.Inject

class CatsListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var catsListAdapter: CatsListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CatsListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as MainApplication).appComponent.inject(this)

        catsListAdapter = CatsListAdapter(
            postFavouriteCat = { viewModel.favouriteCat(it) },
            deleteFavouriteCat = { viewModel.deleteFavouriteCat(it) },
            onCardClick = { id, url, fav -> openCatDetails(id, url, fav) }
        )
        binding.catListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = catsListAdapter
        }

        viewModel.catsList.observe(viewLifecycleOwner) { catsList ->
            catsListAdapter.updateCatsList(catsList)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        hookSearchListener()

        viewModel.getCatList()
    }

    private fun hookSearchListener() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.searchCats(query)
                    return true
                } else {
                    return false
                }
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    viewModel.getCatList()
                } else {
                    viewModel.filterCatWithLocalSearch(query)
                }
                return true
            }
        })

    }

    private fun openCatDetails(catId: String, catImageUrl: String, favouriteId: String) {
        val bundle = Bundle().apply {
            putString("catId", catId)
            putString("catImageUrl", catImageUrl)
            if (favouriteId.isNotEmpty()) putString("catFavouriteId", favouriteId)
        }

        findNavController().navigate(R.id.action_navigation_list_to_navigation_details, bundle)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
