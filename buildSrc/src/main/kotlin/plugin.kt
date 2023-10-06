@file:Suppress("NOTHING_TO_INLINE", "ObjectPropertyName")

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

inline fun PluginDependenciesSpec.psychat(pluginId: String): PluginDependencySpec =
    id("psychat.plugin.$pluginId")

inline fun PluginDependenciesSpec.android(pluginId: String): PluginDependencySpec =
    id("com.android.$pluginId")

val PluginDependenciesSpec.`kotlin-parcelize`: PluginDependencySpec
    inline get() = id("kotlin-parcelize")