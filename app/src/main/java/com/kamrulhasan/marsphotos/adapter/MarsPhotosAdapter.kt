package com.kamrulhasan.marsphotos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kamrulhasan.marsphotos.R
import com.kamrulhasan.marsphotos.model.MarsPhotos
import com.kamrulhasan.marsphotos.viewmodel.MarsPhotosViewModel

class MarsPhotosAdapter(
    private val context: Context, private val viewModel: MarsPhotosViewModel)
    : RecyclerView.Adapter<MarsPhotosAdapter.MarsPhotoHolder>(){

    private var liveData = emptyList<MarsPhotos>()

    class MarsPhotoHolder(binding: View) : RecyclerView.ViewHolder(binding.rootView){
        val img_holder = binding.findViewById<ImageView>(R.id.iv_mars_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent,false)
        return MarsPhotoHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPhotoHolder, position: Int) {
        holder.img_holder.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun getItemCount(): Int {
        return 5
    }

}