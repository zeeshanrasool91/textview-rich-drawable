package com.tolstykh.textviewrichdrawable.helper;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

import com.tolstykh.library.R;
import com.tolstykh.textviewrichdrawable.DensityUtils;
import com.tolstykh.textviewrichdrawable.DrawableEnriched;

public class RichDrawableHelper implements DrawableEnriched {

    private static final int START_DRAWABLE_INDEX = 0;
    private static final int TOP_DRAWABLE_INDEX = 1;
    private static final int END_DRAWABLE_INDEX = 2;
    private static final int BOTTOM_DRAWABLE_INDEX = 3;

    private Context mContext;


    private int mDrawableStartWidth;
    private int mDrawableStartHeight;
    private int mDrawableEndWidth;
    private int mDrawableEndHeight;
    private int mDrawableTopWidth;
    private int mDrawableTopHeight;
    private int mDrawableBottomWidth;
    private int mDrawableBottomHeight;

    private boolean allSame;
    private boolean scaleStart;
    private boolean scaleEnd;
    private boolean scaleTop;
    private boolean scaleBottom;
    @ColorInt
    private int drawableTintStart;
    @ColorInt
    private int drawableTintEnd;
    @ColorInt
    private int drawableTintBottom;
    @ColorInt
    private int drawableTintTop;

    private int defaultDrawableWidth;
    private int defaultDrawableHeight;


    public RichDrawableHelper(@NonNull Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mContext = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewRichDrawable, defStyleAttr, defStyleRes);

        try {
            defaultDrawableWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableWidth, UNDEFINED);
            defaultDrawableHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableHeight, UNDEFINED);

            mDrawableStartWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableStartWidth, UNDEFINED);
            mDrawableStartHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableStartHeight, UNDEFINED);

            mDrawableEndWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableEndWidth, UNDEFINED);
            mDrawableEndHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableEndHeight, UNDEFINED);

            mDrawableTopWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableTopWidth, UNDEFINED);
            mDrawableTopHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableTopHeight, UNDEFINED);

            mDrawableBottomWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableBottomWidth, UNDEFINED);
            mDrawableBottomHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableBottomHeight, UNDEFINED);
            //Sizing Attributes
            allSame = array.getBoolean(R.styleable.TextViewRichDrawable_widthHeightAllSame, true);
            scaleStart = array.getBoolean(R.styleable.TextViewRichDrawable_scaleStart, IS_SCALABLE);
            scaleEnd = array.getBoolean(R.styleable.TextViewRichDrawable_scaleEnd, IS_SCALABLE);
            scaleTop = array.getBoolean(R.styleable.TextViewRichDrawable_scaleTop, IS_SCALABLE);
            scaleBottom = array.getBoolean(R.styleable.TextViewRichDrawable_scaleBottom, IS_SCALABLE);

            drawableTintStart = array.getColor(R.styleable.TextViewRichDrawable_drawableTintStart, UNDEFINED);
            drawableTintEnd = array.getColor(R.styleable.TextViewRichDrawable_drawableTintEnd, UNDEFINED);
            drawableTintTop = array.getColor(R.styleable.TextViewRichDrawable_drawableTintTop, UNDEFINED);
            drawableTintBottom = array.getColor(R.styleable.TextViewRichDrawable_drawableTintBottom, UNDEFINED);
        } finally {
            array.recycle();
        }
    }

    public void apply(TextView textView) {
        initCompoundDrawables(textView);
    }

    private void initCompoundDrawables(TextView textView) {
        int mDrawableWidth = UNDEFINED;
        int mDrawableHeight = UNDEFINED;
        int drawableTint = UNDEFINED;
        Drawable[] drawables = textView.getCompoundDrawablesRelative();

        for (int i = 0; i < drawables.length; i++) {
            //Log.d("IndexTest", i + "" + drawables[i]);
            switch (i) {
                case START_DRAWABLE_INDEX:
                    if (scaleStart) {
                        mDrawableWidth = setValue(defaultDrawableWidth, mDrawableStartWidth);
                        mDrawableHeight = setValue(defaultDrawableHeight, mDrawableStartHeight);
                    } else {
                        mDrawableWidth = 0;
                        mDrawableHeight = 0;
                    }
                    drawableTint = drawableTintStart;
                    break;
                case END_DRAWABLE_INDEX:
                    if (scaleEnd) {
                        mDrawableWidth = setValue(defaultDrawableWidth, mDrawableEndWidth);
                        mDrawableHeight = setValue(defaultDrawableHeight, mDrawableEndHeight);
                        drawableTint = drawableTintEnd;
                    } else {
                        mDrawableWidth = 0;
                        mDrawableHeight = 0;
                    }
                    break;
                case TOP_DRAWABLE_INDEX:
                    if (scaleTop) {
                        mDrawableWidth = setValue(defaultDrawableWidth, mDrawableTopWidth);
                        mDrawableHeight = setValue(defaultDrawableHeight, mDrawableTopHeight);
                        drawableTint = drawableTintBottom;
                    } else {
                        mDrawableWidth = 0;
                        mDrawableHeight = 0;
                    }
                    break;
                case BOTTOM_DRAWABLE_INDEX:
                    if (scaleBottom) {
                        mDrawableWidth = setValue(defaultDrawableWidth, mDrawableBottomWidth);
                        mDrawableHeight = setValue(defaultDrawableHeight, mDrawableBottomHeight);
                        drawableTint = drawableTintTop;
                    } else {
                        mDrawableWidth = 0;
                        mDrawableHeight = 0;
                    }
                    break;
            }
            Drawable drawable = drawables[i];
            if (drawable == null) {
                continue;
            }
            scale(drawable, mDrawableHeight, mDrawableWidth);
            Log.d("DrawableTint", drawableTint + "");
            applyDrawableTint(drawable, drawableTint);
        }
        //textView.setCompoundDrawables(drawables[START_DRAWABLE_INDEX], drawables[TOP_DRAWABLE_INDEX], drawables[END_DRAWABLE_INDEX], drawables[BOTTOM_DRAWABLE_INDEX]);
        textView.setCompoundDrawablesRelative(drawables[START_DRAWABLE_INDEX], drawables[TOP_DRAWABLE_INDEX], drawables[END_DRAWABLE_INDEX], drawables[BOTTOM_DRAWABLE_INDEX]);
        //textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[START_DRAWABLE_INDEX], drawables[TOP_DRAWABLE_INDEX], drawables[END_DRAWABLE_INDEX], drawables[BOTTOM_DRAWABLE_INDEX]);
        //textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[START_DRAWABLE_INDEX], drawables[TOP_DRAWABLE_INDEX], drawables[END_DRAWABLE_INDEX], drawables[BOTTOM_DRAWABLE_INDEX]);
    }

    private void scale(Drawable drawable, int mDrawableHeight, int mDrawableWidth) {
        if (mDrawableHeight > 0 || mDrawableWidth > 0) {
            Rect realBounds = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            float actualDrawableWidth = realBounds.width();
            float actualDrawableHeight = realBounds.height();
            float actualDrawableRatio = actualDrawableHeight / actualDrawableWidth;

            float scale;
            // check if both width and height defined then adjust drawable size according to the ratio
            if (mDrawableHeight > 0 && mDrawableWidth > 0) {
                float placeholderRatio = mDrawableHeight / (float) mDrawableWidth;
                if (placeholderRatio > actualDrawableRatio) {
                    scale = mDrawableWidth / actualDrawableWidth;
                } else {
                    scale = mDrawableHeight / actualDrawableHeight;
                }
            } else if (mDrawableHeight > 0) { // only height defined
                scale = mDrawableHeight / actualDrawableHeight;
            } else { // only width defined
                scale = mDrawableWidth / actualDrawableWidth;
            }

            actualDrawableWidth = actualDrawableWidth * scale;
            actualDrawableHeight = actualDrawableHeight * scale;

            realBounds.right = realBounds.left + Math.round(actualDrawableWidth);
            realBounds.bottom = realBounds.top + Math.round(actualDrawableHeight);

            drawable.setBounds(realBounds);
        } else {
            drawable.setBounds(new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()));
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getCompoundDrawableHeight() {
        return defaultDrawableHeight;
    }

    /**
     * {@inheritDoc}
     */
    public int getCompoundDrawableWidth() {
        return defaultDrawableWidth;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableStartWidth() {
        return mDrawableStartWidth;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableStartHeight() {
        return mDrawableStartHeight;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableEndWidth() {
        return mDrawableEndWidth;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableEndHeight() {
        return mDrawableEndHeight;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableTopWidth() {
        return mDrawableTopWidth;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableTopHeight() {
        return mDrawableTopHeight;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableBottomWidth() {
        return mDrawableBottomWidth;
    }

    /**
     * {@inheritDoc}
     */
    public int getDrawableBottomHeight() {
        return mDrawableBottomHeight;
    }

    private int setValue(int initialValue, int myValue) {
        int finalValue = initialValue;
        if (!allSame && myValue != UNDEFINED) {
            finalValue = myValue;
        }
        return finalValue;
    }

    /**
     * {@inheritDoc}
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAllSame() {
        return allSame;
    }

    public void applyDrawableTint(Drawable drawable, @ColorInt int mDrawableTint) {
        if (mDrawableTint == UNDEFINED)
            return;
        drawable.setColorFilter(new PorterDuffColorFilter(mDrawableTint, PorterDuff.Mode.SRC_IN));
    }
}
