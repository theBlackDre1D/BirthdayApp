package com.g3.base.extensions

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.os.SystemClock
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.g3.base.R


private const val CLICK_DELAY: Long = 750

fun View.setDefaultShadow() {
    this.setShadow(
        R.color.black_59333333,
        R.dimen.shadow__view_corner_radius,
        R.dimen.shadow__elevation,
        Gravity.BOTTOM,
        R.color.white_FFFFFF
    )
}

fun View.setShadow(
    @ColorRes shadowColor: Int,
    @DimenRes cornerRadius: Int,
    @DimenRes elevation: Int,
    shadowGravity: Int = Gravity.BOTTOM,
    @ColorRes backgroundColorResource: Int = 0
) {
    val resource = context.resources
    val firstLayer = 0
    val ratioTopBottom = 3
    val defaultRatio = 2

    if (background == null && backgroundColorResource == 0) {
        throw RuntimeException("Pass backgroundColorResource or use setBackground")
    }

    if (background != null && background !is ColorDrawable) {
        throw RuntimeException(
            "${background::class.java.name} " +
                    "is not supported, set background as " +
                    "ColorDrawable or pass background as a resource"
        )
    }

    val cornerRadiusValue = resource.getDimension(cornerRadius)
    val elevationValue = resource.getDimension(elevation).toInt()
    val shadowColorValue = ContextCompat.getColor(context, shadowColor)

    val backgroundColor = if (backgroundColorResource != 0) {
        ContextCompat.getColor(context, backgroundColorResource)
    } else {
        (background as ColorDrawable).color
    }

    val outerRadius = FloatArray(8) { cornerRadiusValue }

    val directionOfY = when (shadowGravity) {
        Gravity.CENTER -> 0
        Gravity.TOP -> -1 * elevationValue / ratioTopBottom
        Gravity.BOTTOM -> elevationValue / ratioTopBottom
        else -> elevationValue / defaultRatio // Gravity.LEFT & Gravity.RIGHT
    }

    val directionOfX = when (shadowGravity) {
        Gravity.LEFT -> -1 * elevationValue / ratioTopBottom
        Gravity.RIGHT -> elevationValue / ratioTopBottom
        else -> 0
    }

    val shapeDrawable = ShapeDrawable()
    shapeDrawable.paint.color = backgroundColor
    shapeDrawable.paint.setShadowLayer(
        cornerRadiusValue / ratioTopBottom,
        directionOfX.toFloat(),
        directionOfY.toFloat(),
        shadowColorValue
    )
    shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

    when (Build.VERSION.SDK_INT) {
        in Build.VERSION_CODES.BASE..Build.VERSION_CODES.O_MR1 -> setLayerType(
            View.LAYER_TYPE_SOFTWARE,
            shapeDrawable.paint
        )
    }

    val drawable = LayerDrawable(arrayOf(shapeDrawable))
    drawable.setLayerInset(
        firstLayer,
        elevationValue,
        elevationValue * defaultRatio,
        elevationValue,
        elevationValue * defaultRatio
    )

    background = drawable
}

fun View.onClick(onClick: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - this.lastClickTime < CLICK_DELAY) {
                return
            } else {
                onClick(v)
            }

            this.lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}