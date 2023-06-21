package world.anhgelus.kubewars.bedwars.game.listeners

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemStack
import world.anhgelus.kubewars.kubecore.api.game.event.SpigotGameListener
import world.anhgelus.kubewars.kubecore.api.game.event.player.life.LostRespawnEvent
import world.anhgelus.kubewars.kubecore.api.game.event.player.life.RespawnEvent
import world.anhgelus.kubewars.kubecore.api.game.event.player.shop.RequestShopEvent
import world.anhgelus.kubewars.kubecore.api.player.KPlayer

object SpigotListener : SpigotGameListener() {
    override fun changeShopSection(player: KPlayer, type: Material, event: InventoryClickEvent) {

    }

    override fun closeShop(player: KPlayer, event: InventoryCloseEvent) {

    }

    override fun deathEvent(player: KPlayer, cause: EntityDamageEvent.DamageCause, event: EntityDamageEvent) {

    }

    override fun finalDeathEvent(player: KPlayer, cause: EntityDamageEvent.DamageCause, event: EntityDamageEvent) {

    }

    override fun finalKillEvent(killer: KPlayer, killed: KPlayer, event: EntityDamageEvent) {

    }

    override fun killEvent(killer: KPlayer, killed: KPlayer, event: EntityDamageEvent) {

    }

    override fun requestBuy(player: KPlayer, item: ItemStack, price: Long, event: InventoryClickEvent) {

    }

    fun onRespawn(players: List<KPlayer>, player: KPlayer) {
        listeners.forEach { it.onRespawn(RespawnEvent(players, player)) }
    }

    @EventHandler
    fun onLostRespawn(event: BlockBreakEvent) {
        if (event.block.type != Material.BED) return
        val player = KPlayer(event.player)
        val losts = listOf(player) //TODO: edit this when team will be implemented
        listeners.forEach { it.onLostRespawn(LostRespawnEvent(players, player, losts)) }
    }

    @EventHandler
    fun onRequestShop(event: PlayerInteractEntityEvent) {
        if (event.rightClicked.type != EntityType.VILLAGER) return
        val player = KPlayer(event.player)
//        listeners.forEach { it.onRequestShop(RequestShopEvent(players, shop, player)) }
    }
}