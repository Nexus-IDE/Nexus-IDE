package com.silva.nexuside.adapters

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
        val binding: LayoutCheckOptionsBinding
        val view: View

        if (convertView == null) {
            binding = LayoutCheckOptionsBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as LayoutCheckOptionsBinding
        }

        binding.text.text = arr[position]

        return view
    }
}