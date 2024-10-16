package com.silva.nexuside.ui.components

import android.content.Context
import android.content.res.TypedArray
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.content.withStyledAttributes
import androidx.customview.view.AbsSavedState
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder

class ContainerPreference @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : Preference(context, attrs, defStyleAttr) {
    
    private var cornerRadiusTop: Int = 0
    private var cornerRadiusBottom: Int = 0
    private var cornerRadiusTopLeft: Int = 0
    private var cornerRadiusTopRight: Int = 0
    private var cornerRadiusBottomLeft: Int = 0
    private var cornerRadiusBottomRight: Int = 0
    
    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.PreferenceContainer,
            defStyleAttr
        ) {
            cornerRadiusTop = getFloat(R.styleable.ContainerPreference_android_cornerRadiusTop, cornerRadiusTop.toFloat()).toInt()
            cornerRadiusBottom = getFloat(R.styleable.ContainerPreference_android_cornerRadiusBottom, cornerRadiusBottom.toFloat()).toInt()
            cornerRadiusTopLeft = getFloat(R.styleable.ContainerPreference_android_cornerRadiusTopLeft, cornerRadiusTop.toFloat()).toInt()
            cornerRadiusTopRight = getFloat(R.styleable.ContainerPreference_android_cornerRadiusTopRight, cornerRadiusTop.toFloat()).toInt()
            cornerRadiusBottomLeft = getFloat(R.styleable.ContainerPreference_android_cornerRadiusBottomLeft, cornerRadiusBottom.toFloat()).toInt()
            cornerRadiusBottomRight = getFloat(R.styleable.ContainerPreference_android_cornerRadiusBottomRight, cornerRadiusBottom.toFloat()).toInt()
        }
    }
    
    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        val shape = ShapeView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        if (shape == null) {
            return
        }
        shape.cornerRadiusTopLeft(if (cornerRadiusTopLeft != 0) cornerRadiusTopLeft else cornerRadiusTop)
        shape.cornerRadiusTopRight(if (cornerRadiusTopRight != 0) cornerRadiusTopRight else cornerRadiusTop)
        shape.cornerRadiusBottomLeft(if (cornerRadiusBottomLeft != 0) cornerRadiusBottomLeft else cornerRadiusBottom)
        shape.cornerRadiusBottomRight(if (cornerRadiusBottomRight != 0) cornerRadiusBottomRight else cornerRadiusBottom)
        (holder.itemView as ViewGroup).addView(shape)
    }
    
}