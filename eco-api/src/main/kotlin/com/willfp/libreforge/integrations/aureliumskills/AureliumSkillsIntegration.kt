package com.willfp.libreforge.integrations.aureliumskills

import com.willfp.eco.core.integrations.Integration
import com.willfp.libreforge.LibReforgePlugin
import com.willfp.libreforge.effects.Effect

object AureliumSkillsIntegration : Integration {
    private lateinit var ADD_STAT: Effect

    fun load() {
        ADD_STAT = EffectAddStat()
        LibReforgePlugin.instance.eventManager.registerListener(UseManaHandler())
    }

    override fun getPluginName(): String {
        return "AureliumSkills"
    }
}