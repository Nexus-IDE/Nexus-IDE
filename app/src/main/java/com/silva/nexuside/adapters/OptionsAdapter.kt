package com.silva.nexuside.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.silva.nexuside.databinding.LayoutCheckOptionsBinding

class OptionsAdapter(
    private val arr: List<String>
    ) : BaseAdapter() {
    
    override fun getCount(): Int {
        return arr.size
    }
    
    override fun getItem(position: Int): Any {
        return arr[position]
    }
    
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: LayoutCheckOptionsBinding = LayoutCheckOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.text.text = arr[position]
        return binding.root
    }
    
}