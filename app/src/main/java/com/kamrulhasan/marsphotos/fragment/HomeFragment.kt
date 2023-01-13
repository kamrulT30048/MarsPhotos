package com.kamrulhasan.marsphotos.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kamrulhasan.marsphotos.R
import com.kamrulhasan.marsphotos.adapter.MarsPhotosAdapter
import com.kamrulhasan.marsphotos.databinding.FragmentHomeBinding
import com.kamrulhasan.marsphotos.databinding.ItemBinding
import com.kamrulhasan.marsphotos.model.MarsPhotos
import com.kamrulhasan.marsphotos.viewmodel.MarsPhotosViewModel
import kotlin.math.log

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var list = emptyList<MarsPhotos>()


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

        viewModel.status.observe(viewLifecycleOwner) {
            binding.tvMessage.text = it
//            adapter.setData(viewModel.photos.value!!)
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            list = it

            val adapter = MarsPhotosAdapter(requireContext(), it)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            binding.tvMessage.visibility = View.GONE

            Log.d(TAG, "onViewCreated: ${it}")
        }

        if (verifyAvailableNetwork(activity as AppCompatActivity)) {
            viewModel.hitServer()
        } else {
            viewModel.InternetNotConnected()
        }

    }

    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


}