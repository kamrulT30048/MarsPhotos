package com.kamrulhasan.marsphotos.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamrulhasan.marsphotos.R
import com.kamrulhasan.marsphotos.model.MarsPhotos

private const val TAG = "MarsPhotosAdapter"

class MarsPhotosAdapter(
    private val context: Context, private val listData: List<MarsPhotos> )
    : RecyclerView.Adapter<MarsPhotosAdapter.MarsPhotoHolder>(){

//    private var liveData = emptyList<MarsPhotos>()

    class MarsPhotoHolder(binding: View) : RecyclerView.ViewHolder(binding.rootView){
        val img_holder = binding.findViewById<ImageView>(R.id.iv_mars_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent,false)
        return MarsPhotoHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPhotoHolder, position: Int) {
        val item = listData[position]
//        holder.img_holder.setImageResource(R.drawable.ic_launcher_foreground)
        Log.d(TAG, "onBindViewHolder: called")
        Glide
            .with(holder.itemView.context)
            .load(item.imgSrcUrl)
            .centerCrop()
            .into(holder.img_holder)
//            .placeholderDrawable(R.drawable.ic_baseline_change_circle_24)
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"position is ${position + 1}", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: called  ${listData.size}")

        return listData.size
    }

}