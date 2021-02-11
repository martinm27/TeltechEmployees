package com.teltech.employees.navigation

import androidx.fragment.app.FragmentTransaction

fun FragmentTransaction.applyFadeInEnterAndFadeOutExitAnimation() {
    setCustomAnimations(
        R.anim.fade_in_animation,
        R.anim.fade_out_animation,
        R.anim.fade_in_animation,
        R.anim.fade_out_animation
    )
}
