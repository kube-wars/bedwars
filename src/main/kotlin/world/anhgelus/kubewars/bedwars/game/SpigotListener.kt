package world.anhgelus.kubewars.bedwars.game

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import world.anhgelus.kubewars.kubecore.api.game.event.SpigotGameListener
import world.anhgelus.kubewars.kubecore.api.player.KPlayer

object SpigotListener : SpigotGameListener() {
    override fun changeShopSection(player: KPlayer, type: Material, event: InventoryClickEvent) {
        TODO("Not yet implemented")
    }

    override fun closeShop(player: KPlayer, event: InventoryCloseEvent) {
        TODO("Not yet implemented")
    }

    override fun deathEvent(player: KPlayer, cause: EntityDamageEvent.DamageCause, event: EntityDamageEvent) {
        TODO("Not yet implemented")
    }

    override fun finalDeathEvent(player: KPlayer, cause: EntityDamageEvent.DamageCause, event: EntityDamageEvent) {
        TODO("Not yet implemented")
    }

    override fun finalKillEvent(killer: KPlayer, killed: KPlayer, event: PlayerDeathEvent) {
        TODO("Not yet implemented")
    }

    override fun killEvent(killer: KPlayer, killed: KPlayer, event: EntityDamageEvent) {
        TODO("Not yet implemented")
    }

    override fun requestBuy(player: KPlayer, item: ItemStack, price: Long, event: InventoryClickEvent) {
        TODO("Not yet implemented")
    }

    @EventHandler
    fun onRespawn() {

    }

    @EventHandler
    fun onLostRespawn() {

    }

    @EventHandler
    fun onRequestShop() {

    }
}