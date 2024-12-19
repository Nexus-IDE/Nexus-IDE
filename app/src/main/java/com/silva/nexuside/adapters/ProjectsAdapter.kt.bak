package com.silva.nexuside.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silva.nexuside.databinding.LayoutProjectItemBinding

class ProjectsAdapter(val arr: List<String>, val mOnClickListener: ProjectClickListener) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {
    
    companion object {
        var onClickListener: ProjectClickListener? = null
    }

    class ViewHolder(var binding: LayoutProjectItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutProjectItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onClickListener = mOnClickListener
        holder.binding.title.text = Uri.parse(arr[position]).lastPathSegment
        holder.binding.summary.text = arr[position]
        holder.binding.rootView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onClick(arr, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
    
    open interface ProjectClickListener() {
        fun onClick(arr: List<String>, position: Int)
    }
}
