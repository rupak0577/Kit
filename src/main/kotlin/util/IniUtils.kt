package org.example.util

import org.ini4j.Wini

fun writeDefaultConfig(config: Wini) {
    config.put("core", "repositoryformatversion", 0)
    config.put("core", "filemode", false)
    config.put("core", "bare", false)
    config.store()
}

inline fun <reified T> Wini.getOption(section: String, option: String): T {
    return get(section, option, T::class.java)
}

inline fun <reified T> Wini.putOption(section: String, option: String, value: T) {
    put(section, option, value)
}