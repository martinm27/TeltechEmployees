package com.teltech.employees.coreui.comparator

import java.text.Collator
import java.util.*

class NaturalStringComparator<T>(private val stringSelector: (T) -> String) : Comparator<T> {

    private val collator = Collator.getInstance(Locale.getDefault()).apply {
        strength = Collator.PRIMARY
    }

    override fun compare(left: T, right: T) =
            collator.compare(stringSelector(left), stringSelector(right))
}
