package io.github.mangi.eta.data.repository

import android.app.LocaleConfig
import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.Locale

class LanguageSettingsRepository(context: Context) {
    private val applicationContext = context.applicationContext

    private val localeManager: LocaleManager? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            applicationContext.getSystemService(LocaleManager::class.java)
        } else {
            null
        }

    val supportedLocales: List<Locale> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        LocaleConfig(applicationContext).supportedLocales?.toLocaleList().orEmpty()
    } else {
        FALLBACK_SUPPORTED_LOCALES
    }

    fun selectedLocale(): Locale? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        localeManager?.applicationLocales
            ?.takeIf { !it.isEmpty }
            ?.get(0)
    } else {
        applicationContext.resources.configuration.locales
            .takeIf { !it.isEmpty }
            ?.get(0)
            ?.takeIf { selected ->
                supportedLocales.any { supported ->
                    supported.matchesLanguageAndScriptCompat(selected)
                }
            }
    }

    fun selectLocale(locale: Locale?) {
        require(locale == null || locale in supportedLocales)
        val locales = if (locale == null) LocaleList.getEmptyLocaleList() else LocaleList(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val manager = localeManager ?: return
            // Android 13+ 由系统保存应用语言并分发配置变更。
            if (manager.applicationLocales != locales) {
                manager.applicationLocales = locales
            }
        } else {
            // Android 12 没有 LocaleManager，只对当前进程应用所选语言。
            val configuration = Configuration(applicationContext.resources.configuration)
            configuration.setLocales(
                if (locales.isEmpty) LocaleList.getDefault() else locales,
            )
            @Suppress("DEPRECATION")
            applicationContext.resources.updateConfiguration(
                configuration,
                applicationContext.resources.displayMetrics,
            )
        }
    }

    private fun LocaleList.toLocaleList(): List<Locale> =
        List(size()) { index -> get(index) }

    private fun Locale.matchesLanguageAndScriptCompat(other: Locale): Boolean {
        if (language != other.language) return false
        val thisScript = script
        val otherScript = other.script
        return thisScript.isBlank() || otherScript.isBlank() || thisScript == otherScript
    }

    private companion object {
        val FALLBACK_SUPPORTED_LOCALES = listOf(
            Locale.ENGLISH,
            Locale.forLanguageTag("zh-Hans"),
            Locale.forLanguageTag("zh-Hant"),
        )
    }
}
