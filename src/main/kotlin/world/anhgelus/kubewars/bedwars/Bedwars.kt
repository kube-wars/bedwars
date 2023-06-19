package world.anhgelus.kubewars.bedwars

import org.bukkit.Bukkit
import org.bukkit.Material
import world.anhgelus.kubewars.bedwars.game.BedwarsGame
import world.anhgelus.kubewars.bedwars.game.BedwarsPrice
import world.anhgelus.kubewars.bedwars.game.listeners.GameListener
import world.anhgelus.kubewars.bedwars.game.listeners.SpigotListener
import world.anhgelus.kubewars.kubecore.PluginBase
import world.anhgelus.kubewars.kubecore.api.game.Game
import world.anhgelus.kubewars.kubecore.api.player.KPlayer
import world.anhgelus.kubewars.kubecore.api.player.KPlayerManager
import world.anhgelus.kubewars.kubecore.utils.config.ConfigHelper

class Bedwars : PluginBase() {

    object ConfigAPI : ConfigHelper()

    override val configHelper = ConfigAPI
    override val pluginName = "Bedwars"

    override fun disable() {

    }

    override fun enable() {
        LOGGER = logger
        INSTANCE = this

        events.add(SpigotListener)

        BedwarsPrice.addCurrency(Material.IRON_INGOT)
            .addCurrency(Material.GOLD_INGOT)
            .addCurrency(Material.DIAMOND)
            .addCurrency(Material.EMERALD)

        val gameType = BedwarsGame()
        gameType.addListener(GameListener)
        val kplayers = mutableListOf<KPlayer>()
        Bukkit.getOnlinePlayers().forEach {
            kplayers.add(KPlayerManager.getPlayer(it))
        }
        game = Game.new(kplayers, gameType, SpigotListener)
    }

    companion object : CompanionBase() {
        lateinit var game: Game
    }
}