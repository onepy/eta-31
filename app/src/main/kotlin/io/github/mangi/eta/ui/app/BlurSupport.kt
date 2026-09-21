package io.github.mangi.eta.ui.app

import android.os.Build
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported

/** Miuix blur requires API 33; older containers use the normal opaque surfaces. */
internal fun isBlurSupportedOnDevice(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && isRuntimeShaderSupported()
import io.github.mangi.eta.ui.app.LocalBlurEnabled
import io.github.mangi.eta.ui.app.isBlurSupportedOnDevice
    if (!LocalBlurEnabled.current || !isBlurSupportedOnDevice()) return null
import io.github.mangi.eta.ui.app.isBlurSupportedOnDevice
    val frostEnabled = hasMessages && LocalBlurEnabled.current && isBlurSupportedOnDevice()
import io.github.mangi.eta.ui.app.isBlurSupportedOnDevice
    val blurSupported = isBlurSupportedOnDevice()
