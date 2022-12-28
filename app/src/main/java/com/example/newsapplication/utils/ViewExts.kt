package com.example.newsapplication.utils

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import coil.load
import coil.request.Disposable
import coil.request.ImageRequest
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.example.newsapplication.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun View.setHeight(heightInPixel: Int) {
    val tempLayoutParams = layoutParams as ViewGroup.LayoutParams
    tempLayoutParams.height = heightInPixel
    layoutParams = tempLayoutParams
}

fun View.setWidth(widthInPixel: Int) {
    val tempLayoutParams = layoutParams as ViewGroup.LayoutParams
    tempLayoutParams.width = widthInPixel
    layoutParams = tempLayoutParams
}


inline fun <T1 : Any, T2 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    block: (T1, T2) -> R?,
): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    block: (T1, T2, T3) -> R?,
): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}

fun ImageView.loadWithShimmer(
    url: String?,
    builder: ImageRequest.Builder.() -> Unit = {
        val shimmer =
            Shimmer.ColorHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.6f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setBaseColor(ContextCompat.getColor(context, R.color.white))
                .setAutoStart(true)
                .build()
        // This is the placeholder for the imageView
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
        placeholder(shimmerDrawable)
        crossfade(true)
    },
): Disposable {
    return load(url, builder = builder)
}

fun RequestManager.loadWithShimmer(
    url: String?,
    shimmerDrawable: () -> Drawable = {
        val shimmer =
            Shimmer.ColorHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.7f) //the alpha of the underlying children
                .setHighlightAlpha(0.6f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setBaseColor(Color.parseColor("#FFFFFF"))
                .setAutoStart(true)
                .build()
        // This is the placeholder for the imageView
        ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
    }
) : RequestBuilder<Drawable> {
    return load(url).placeholder(shimmerDrawable()).error(shimmerDrawable())
}

fun View.updateMargin(
    top: Int = marginTop,
    start: Int = marginLeft,
    bottom: Int = marginBottom,
    end: Int = marginEnd,
) {
    val tempLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
    tempLayoutParams.bottomMargin = bottom
    tempLayoutParams.topMargin = top
    tempLayoutParams.marginStart = start
    tempLayoutParams.marginEnd = end
    layoutParams = tempLayoutParams
}


/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Show the view if [condition] returns true
 * (visibility = View.VISIBLE)
 */
inline fun View.showIf(condition: () -> Boolean): View {
    if (visibility != View.VISIBLE && condition()) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.invisible(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view if [predicate] returns true
 * (visibility = View.GONE)
 */
inline fun View.hideIf(predicate: () -> Boolean): View {
    if (visibility != View.GONE && predicate()) {
        visibility = View.GONE
    }
    return this
}

fun Int.dpToPx(): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
).toInt()