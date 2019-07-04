package com.github.devit951.borderedimageview

import android.graphics.*
import android.graphics.drawable.Drawable

class BorderDrawable(private val color: Int, private val borderWidth: Float, private val borderRadius: Float): Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val path = Path().apply {
        fillType = Path.FillType.EVEN_ODD
    }

    private val rectF = RectF()

    override fun draw(canvas: Canvas) {
        paint.color = color
        canvas.drawPath(path, paint)
    }

    override fun onBoundsChange(bounds: Rect) {
        val boundsF = RectF(bounds)
        path.addRect(boundsF.left, boundsF.top, boundsF.right, boundsF.bottom, Path.Direction.CW)
        rectF.set(bounds.left + borderWidth, bounds.top + borderWidth, bounds.right - borderWidth, bounds.bottom - borderWidth)
        path.addRoundRect(rectF, borderRadius, borderRadius, Path.Direction.CW)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity() = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

}