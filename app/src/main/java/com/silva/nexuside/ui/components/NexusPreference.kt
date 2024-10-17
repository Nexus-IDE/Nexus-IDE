package com.silva.nexuside.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import android.content.res.TypedArray
import com.silva.nexuside.resources.Layouts
import com.silva.nexuside.resources.R

class NexusPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
    ) : Preference(context, attrs, defStyle) {
    
    private var mIcon: Drawable? = null
    
    init {
        layoutResource = Layouts.preference
        var a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.Preference, defStyle, 0)
        mIcon = a.getDrawable(R.styleable.Preference_icon)
        a.recycle()
    }
    
    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindView(holder)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.icon)
        imageView?.setImageDrawable(mIcon)
    }
    
    override fun setIcon(icon: Drawable?) {
        if ((icon == null && mIcon != null) || (icon != null && icon != mIcon)) {
            mIcon = icon
            notifyChanged()
        }
    }
    
    override fun getIcon(): Drawable? {
        return mIcon
    }
    
}