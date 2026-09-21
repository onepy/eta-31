package io.github.mangi.eta.core

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable

fun <T : Parcelable> Bundle.getParcelableCompat(key: String, parcelableClass: Class<T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, parcelableClass)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key) as? T
    }

fun <T : Parcelable> Bundle.getParcelableArrayListCompat(
    key: String,
    parcelableClass: Class<T>,
): ArrayList<T>? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayList(key, parcelableClass)
    } else {
        @Suppress("DEPRECATION", "UNCHECKED_CAST")
        getParcelableArrayList<Parcelable>(key) as? ArrayList<T>
    }

fun <T : Parcelable> Intent.getParcelableExtraCompat(
    key: String,
    parcelableClass: Class<T>,
): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, parcelableClass)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T
    }
