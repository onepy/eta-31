package io.github.mangi.eta.ui.app

import android.os.Build
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported

/** Miuix blur requires API 33; older containers use the normal opaque surfaces. */
internal fun isBlurSupportedOnDevice(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && isRuntimeShaderSupported()
