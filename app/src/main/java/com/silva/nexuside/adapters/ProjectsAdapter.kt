package com.silva.nexuside.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silva.nexuside.databinding.LayoutProjectItemBinding

class ProjectsAdapter(private val arr: List<String>) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {
    
    private var itemClickListener: ((List<String>, Int) -> Unit) ? = null

    class ViewHolder(var binding: LayoutProjectItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutProjectItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = Uri.parse(arr[position]).lastPathSegment
        holder.binding.summary.text = arr[position]
        holder.binding.rootView.setOnClickListener { itemClickListener?.invoke(arr, position) }
    }
    
    fun setOnClickListener(listener: (List<String>, Int) -> Unit) {
        itemClickListener = listener
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}
