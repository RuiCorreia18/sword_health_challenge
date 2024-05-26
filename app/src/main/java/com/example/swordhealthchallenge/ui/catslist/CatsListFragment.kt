package com.example.swordhealthchallenge.ui.catslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swordhealthchallenge.databinding.FragmentCatsListBinding

class CatsListFragment : Fragment() {

    private var _binding: FragmentCatsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val catsListAdapter = CatsListAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val catsListViewModel = ViewModelProvider(this).get(CatsListViewModel::class.java)

        _binding = FragmentCatsListBinding.inflate(inflater, container, false)

        /*val textView: TextView = binding.textHome
        catsListViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.catListRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = catsListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}