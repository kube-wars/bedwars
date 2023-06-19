package world.anhgelus.kubewars.bedwars.game

import org.bukkit.Material
import world.anhgelus.kubewars.kubecore.api.player.KPlayer
import world.anhgelus.kubewars.kubecore.api.shop.Price

object BedwarsPrice : Price<Material>() {
    override fun hasEnoughMoney(player: KPlayer, cost: Long): Boolean {
        val costMap = getCostMap(cost)
        val playerMap = generatePlayerMap(player)
        costMap.forEach {
            if (playerMap[it.key] == null || playerMap[it.key]!! < it.value) return false
        }
        return true
    }

    override fun removeMoney(player: KPlayer, cost: Long): Boolean {
        if (!hasEnoughMoney(player,cost)) return false
        val costMap = getCostMap(cost)
        player.player.inventory.forEach {
            if (!currencies.contains(it.type)) return@forEach
            val amount = it.amount - costMap[it.type]!!
            if (amount == 0) {
                it.type = Material.AIR
            } else {
                it.amount = amount
            }
        }
        return true
    }

    private fun generatePlayerMap(player: KPlayer): Map<Material, Int> {
        val playerMap = mutableMapOf<Material, Int>()
        player.player.inventory.forEach {
            if (!currencies.contains(it.type)) return@forEach
            playerMap[it.type] = it.amount
        }
        return playerMap
    }
}