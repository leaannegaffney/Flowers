package org.wit.flowers.helpers

import android.support.v4.view.ViewPager
import android.view.View

//Code taken from https://thesimplycoder.com/137/android-image-gallery-using-kotlin-tutorial/
private const val MIN_SCALE = 0.75f

class ZoomOutPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> {
                    alpha = 1 - position
                    translationX = pageWidth * -position
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}