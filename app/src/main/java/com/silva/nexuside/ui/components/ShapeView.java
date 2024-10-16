package com.silva.nexuside.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.google.android.material.drawable.DrawableUtils;
import com.silva.nexuside.resources.R;

public class ShapeView extends View {

    private final float[] corners = new float[8];
    private final Path outlinePath = new Path();
    private final Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Drawable backgroundDrawable;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.ShapeView,
            defStyleAttr,
            0
        );

        try {
            float cornerSize = a.getDimension(R.styleable.ShapeView_cornerSize, 0f);
            setCorners(
                a.getDimension(R.styleable.ShapeView_cornerSizeTopLeft, cornerSize),
                a.getDimension(R.styleable.ShapeView_cornerSizeTopRight, cornerSize),
                a.getDimension(R.styleable.ShapeView_cornerSizeBottomRight, cornerSize),
                a.getDimension(R.styleable.ShapeView_cornerSizeBottomLeft, cornerSize)
            );
            strokePaint.setColor(a.getColor(R.styleable.ShapeView_strokeColor, Color.TRANSPARENT));
            strokePaint.setStrokeWidth(a.getDimension(R.styleable.ShapeView_strokeWidth, 0f));
            strokePaint.setStyle(Paint.Style.STROKE);
        } finally {
            a.recycle();
        }

        setOutlineProvider(new OutlineProvider());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            rebuildPath();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(outlinePath);
        if (backgroundDrawable != null) {
            backgroundDrawable.setBounds(0, 0, getWidth(), getHeight());
            backgroundDrawable.draw(canvas);
        }
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (strokePaint.getStrokeWidth() > 0f) {
            canvas.drawPath(outlinePath, strokePaint);
        }
    }

    private void rebuildPath() {
        outlinePath.reset();
        int w = getWidth();
        int h = getHeight();
        if (w > 0 && h > 0) {
            outlinePath.addRoundRect(0f, 0f, w, h, corners, Path.Direction.CW);
        }
    }

    public void setCorners(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        corners[0] = topLeft;
        corners[1] = topLeft;
        corners[2] = topRight;
        corners[3] = topRight;
        corners[4] = bottomRight;
        corners[5] = bottomRight;
        corners[6] = bottomLeft;
        corners[7] = bottomLeft;
        rebuildPath();
        invalidate();
    }

    public void cornerTopLeft(float size) {
        corners[0] = size;
        corners[1] = size;
        rebuildPath();
        invalidate();
    }

    public void cornerTopRight(float size) {
        corners[2] = size;
        corners[3] = size;
        rebuildPath();
        invalidate();
    }

    public void cornerBottomRight(float size) {
        corners[4] = size;
        corners[5] = size;
        rebuildPath();
        invalidate();
    }

    public void cornerBottomLeft(float size) {
        corners[6] = size;
        corners[7] = size;
        rebuildPath();
        invalidate();
    }

    public void customBackground(Drawable drawable) {
        this.backgroundDrawable = drawable;
        invalidate();
    }

    private class OutlineProvider extends ViewOutlineProvider {
        @SuppressLint("RestrictedApi")
        @Override
        public void getOutline(View view, Outline outline) {
            float corner = corners[0];
            boolean isRoundRect = true;
            for (float item : corners) {
                if (item != corner) {
                    isRoundRect = false;
                    break;
                }
            }
            if (isRoundRect) {
                outline.setRoundRect(0, 0, getWidth(), getHeight(), corner);
            } else {
                DrawableUtils.setOutlineToPath(outline, outlinePath);
            }
        }
    }
}