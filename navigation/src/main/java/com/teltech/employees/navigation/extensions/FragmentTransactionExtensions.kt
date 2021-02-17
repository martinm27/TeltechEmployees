package com.teltech.employees.navigation.extensions

import androidx.fragment.app.FragmentTransaction
import com.teltech.employees.navigation.R

fun FragmentTransaction.applyFadeInEnterAndFadeOutExitAnimation() {
    setCustomAnimations(
        R.anim.fade_in_animation,
        R.anim.fade_out_animation,
        R.anim.fade_in_animation,
        R.anim.fade_out_animation
    )
}
