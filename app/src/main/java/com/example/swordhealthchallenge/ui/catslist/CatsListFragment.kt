package com.example.swordhealthchallenge.ui.catslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swordhealthchallenge.MainApplication
import com.example.swordhealthchallenge.databinding.FragmentCatsListBinding
import javax.inject.Inject

class CatsListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val catsListAdapter = CatsListAdapter(emptyList())

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

        binding.catListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = catsListAdapter
        }

        viewModel.catsList.observe(viewLifecycleOwner) { catsList ->
            catsListAdapter.updateCatsList(catsList)
        }

        hookSearchListener()

        viewModel.getCatList()
    }

    private fun hookSearchListener(){
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.searchCats(query)
                    return true
                } else {

                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}