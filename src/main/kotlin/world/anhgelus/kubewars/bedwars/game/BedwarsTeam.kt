package world.anhgelus.kubewars.bedwars.game

import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.scheduler.BukkitRunnable
import world.anhgelus.kubewars.bedwars.Bedwars
import world.anhgelus.kubewars.bedwars.game.listeners.SpigotListener
import world.anhgelus.kubewars.kubecore.api.game.event.player.GamePlayerEvent
import world.anhgelus.kubewars.kubecore.api.game.event.player.life.DeathEvent
import world.anhgelus.kubewars.kubecore.api.game.event.player.life.LostRespawnEvent
import world.anhgelus.kubewars.kubecore.api.player.KPlayer
import world.anhgelus.kubewars.kubecore.api.team.RespawnBase
import world.anhgelus.kubewars.kubecore.api.team.TeamBase
import world.anhgelus.kubewars.kubecore.api.team.TeamManager
import world.anhgelus.kubewars.kubecore.utils.ChatHelper

class BedwarsTeam(name: String, color: ChatColor, players: MutableList<KPlayer>) : TeamBase(name, color, players) {

    class BedwarsRespawn(override val respawn: Location, override val respawnTime: Int) : RespawnBase {
        override var isPresent: Boolean = true
        override fun canRespawn(): Boolean {
            return isPresent
        }
    }

    override fun addPoint(event: GamePlayerEvent, point: Int) {

    }

    override fun gotNewRespawn(respawn: RespawnBase) {
        this.respawn = respawn
    }

    override fun removePoint(event: GamePlayerEvent, point: Int) {

    }

    override fun respawnAll() {
        players.forEach {
            it.teleportToRespawn()
        }
    }

    override fun respawnLost(event: LostRespawnEvent) {
        respawn?.isPresent = false
    }

    override fun respawnPlayer(player: KPlayer, event: DeathEvent) {
        if (!isInTeam(player)) throw IllegalArgumentException("Player ${player.player.displayName} is not in team $name")
        // respawn the player after respawnTime
        var t = respawn!!.respawnTime
        object : BukkitRunnable() {
            override fun run() {
                if (t == 0) {
                    player.toDefaultCondition(false)
                    player.teleportToRespawn()
                    SpigotListener.onRespawn(event.kplayers, player)
                    cancel()
                    return
                }
                // fuck NMS
                @Suppress("DEPRECATION")
                player.player.sendTitle("${ChatHelper.error} Respawning in ${ChatHelper.success} $t", "")
                t--
            }
        }.runTaskTimer(Bedwars.INSTANCE, 0, 20L)
    }

    override fun teleportToRespawn(player: KPlayer) {
        if (!isInTeam(player)) throw IllegalArgumentException("Player ${player.player.displayName} is not in team $name")
        if (respawn == null) return
        player.player.teleport(respawn!!.respawn)
    }

    companion object {
        /**
         * Create a new team
         *
         * @param name the name of the team
         * @param color the color of the team
         * @param respawn the respawn location of the team
         * @param respawnTime the respawn time of the team
         */
        fun new(name: String, color: ChatColor, respawn: Location, respawnTime: Int): BedwarsTeam {
            val res = BedwarsRespawn(respawn, respawnTime)
            val team = BedwarsTeam(name, color, mutableListOf())
            team.respawn = res
            TeamManager.addTeam(team)
            return team
        }
    }
}