package com.willfp.libreforge.filters.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.util.containsIgnoreCase
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.filters.Filter
import com.willfp.libreforge.triggers.TriggerData

object FilterVictimName : Filter<NoCompileData, Collection<String>>("victim_name") {
    override fun getValue(config: Config, data: TriggerData?, key: String): Collection<String> {
        return config.getStrings(key)
    }

    override fun filter(data: TriggerData, value: Collection<String>, compileData: NoCompileData): Boolean {
        val victim = data.victim ?: return true

        return value.containsIgnoreCase(victim.name)
    }
}
