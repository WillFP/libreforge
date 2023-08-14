package com.willfp.libreforge.effects.impl.aoe.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.arguments
import com.willfp.libreforge.effects.impl.aoe.AOEShape
import com.willfp.libreforge.getDoubleFromExpression
import com.willfp.libreforge.toLocation
import com.willfp.libreforge.triggers.TriggerData
import dev.romainguy.kotlin.math.Float3
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.LivingEntity
import kotlin.math.roundToInt

object AOEShapeCircle: AOEShape<NoCompileData>("circle") {
    override val arguments = arguments {
        require("radius", "You must specify the circle radius!")
    }

    override fun getEntities(
        location: Float3,
        direction: Float3,
        world: World,
        config: Config,
        data: TriggerData,
        compileData: NoCompileData
    ): Collection<LivingEntity> {
        val radius = config.getDoubleFromExpression("radius", data)

        return location.toLocation(world).getNearbyEntities(radius, radius, radius)
            .filterIsInstance<LivingEntity>()
    }

    override fun getBlocks(
        location: Float3,
        direction: Float3,
        world: World,
        config: Config,
        data: TriggerData,
        compileData: NoCompileData
    ): Collection<Block> {
        val radius = config.getDoubleFromExpression("radius", data)
        val blocks = arrayListOf<Block>()
        val radiusInt = radius.roundToInt()

        for (x in (-radiusInt..radiusInt)) {
            for (y in (-radiusInt..radiusInt)) {
                for (z in (-radiusInt..radiusInt)) {
                    val block = world.getBlockAt(
                        location.toLocation(world).clone().add(x.toDouble(), y.toDouble(), z.toDouble())
                    )
                    blocks.add(block)
                }
            }
        }
        return blocks.filter { !it.isEmpty }
    }
}
