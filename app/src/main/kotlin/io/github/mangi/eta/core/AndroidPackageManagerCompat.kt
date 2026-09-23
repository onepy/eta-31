package io.github.mangi.eta.core

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build

/** Uses the PackageManager query overload available on the running Android API. */
fun PackageManager.queryIntentActivitiesCompat(
    intent: Intent,
    flags: Int,
): List<ResolveInfo> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(flags.toLong()))
} else {
    @Suppress("DEPRECATION")
    queryIntentActivities(intent, flags)
}

/** Uses the PackageManager service-query overload available on the running Android API. */
fun PackageManager.queryIntentServicesCompat(
    intent: Intent,
    flags: Int,
): List<ResolveInfo> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    queryIntentServices(intent, PackageManager.ResolveInfoFlags.of(flags.toLong()))
} else {
    @Suppress("DEPRECATION")
    queryIntentServices(intent, flags)
}
