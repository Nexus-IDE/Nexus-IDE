package com.silva.nexuside.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.preference.Preference
import android.content.res.TypedArray
import com.silva.nexuside.resources.Layouts
import com.silva.nexuside.resources.R

class Preference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
    defStyle: Int = 0
    ) : Preference(context, attrs, defStyle) {
    
    private var mIcon: Drawable? = null
    
    init {
        layoutResource = Layouts.preference
        var a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.Preference, defStyle, 0)
        mIcon = a.getDrawable(R.styleable.Preference_icon)
        a.recycle()
    }
    
    override fun onBindView(view: View) {
        super.onBindView(view)
        val imageView = view.findViewById<ImageView>(R.id.icon)
        imageView?.setImageDrawable(mIcon)
    }
    
    fun setIcon(icon: Drawable?) {
        if ((icon == null && mIcon != null) || (icon != null && icon != mIcon)) {
            mIcon = icon
            notifyChanged()
        }
    }
    
    fun getIcon(): Drawable? {
        return mIcon
    }
    
}