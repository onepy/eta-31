package io.github.mangi.eta.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Handler

/** Registers runtime receivers without calling the Android 13-only overload on older systems. */
object AndroidReceiverCompat {
    fun registerNotExported(
        context: Context,
        receiver: BroadcastReceiver,
        filter: IntentFilter,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            context.registerReceiver(receiver, filter)
        }
    }

    fun registerNotExported(
        context: Context,
        receiver: BroadcastReceiver,
        filter: IntentFilter,
        handler: Handler?,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, filter, null, handler, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            context.registerReceiver(receiver, filter, null, handler)
        }
    }

    fun registerExported(
        context: Context,
        receiver: BroadcastReceiver,
        filter: IntentFilter,
        permission: String?,
        handler: Handler?,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, filter, permission, handler, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            context.registerReceiver(receiver, filter, permission, handler)
        }
    }
}
