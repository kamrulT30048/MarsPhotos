package com.kamrulhasan.marsphotos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kamrulhasan.marsphotos.R
import com.kamrulhasan.marsphotos.adapter.MarsPhotosAdapter
import com.kamrulhasan.marsphotos.databinding.FragmentHomeBinding
import com.kamrulhasan.marsphotos.databinding.ItemBinding
import com.kamrulhasan.marsphotos.viewmodel.MarsPhotosViewModel


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[MarsPhotosViewModel::class.java]
        val adapter = MarsPhotosAdapter(requireContext(),viewModel)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.status.observe(viewLifecycleOwner){
            binding.tvMessage.text = it
        }

    }
}