package world.anhgelus.kubewars.bedwars.game.listeners

import org.bukkit.GameMode
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.scheduler.BukkitRunnable
import world.anhgelus.kubewars.bedwars.Bedwars
import world.anhgelus.kubewars.kubecore.api.game.event.GameListener
import world.anhgelus.kubewars.kubecore.api.game.event.player.life.*
import world.anhgelus.kubewars.kubecore.api.game.event.player.shop.*
import world.anhgelus.kubewars.kubecore.api.game.event.state.*
import world.anhgelus.kubewars.kubecore.utils.ChatHelper

object GameListener : GameListener() {
    override fun onBuy(event: BuyEvent) {

    }

    override fun onCloseShop(event: CloseShopEvent) {
    }

    override fun onDeath(event: DeathEvent) {
        // reset player
        val bplayer = event.player.player
        event.player.toDefaultCondition()
        bplayer.gameMode = GameMode.SPECTATOR
        var t = 5
        object : BukkitRunnable() {
            override fun run() {
                if (t == 0) {
                    SpigotListener.onRespawn(event.kplayers, event.player)
                    cancel()
                    return
                }
                // fuck NMS
                @Suppress("DEPRECATION")
                bplayer.sendTitle("${ChatHelper.error} Respawning in ${ChatHelper.success} $t", "")
                t--
            }
        }.runTaskTimer(Bedwars.INSTANCE, 0, 20L)
        event.player.stats.deaths++
        event.player.stats.save()
        if (event.cause != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            return
        }
        ChatHelper.sendInfo("${bplayer.displayName} died!")
    }

    override fun onFinalDeath(event: FinalDeathEvent) {
        event.player.stats.finalDeaths++
        event.player.stats.save()
        event.player.toDefaultCondition()
        event.player.player.gameMode = GameMode.SPECTATOR
    }

    override fun onFinalKill(event: FinalKillEvent) {
        event.player.stats.finalKills++
        event.player.stats.save()
        ChatHelper.sendInfo("${event.killed.player.displayName} was destroyed by ${event.player.player.displayName}! (final kill)")
    }

    override fun onGamePause(event: GamePauseEvent) {
    }

    override fun onGamePrepare(event: GamePrepareEvent) {
    }

    override fun onGameResume(event: GameResumeEvent) {
    }

    override fun onGameStart(event: GameStartEvent) {
    }

    override fun onGameStop(event: GameStopEvent) {
    }

    override fun onKill(event: KillEvent) {
        event.player.stats.kills++
        event.player.stats.save()
        ChatHelper.sendInfo("${event.killed.player.displayName} was killed by ${event.player.player.displayName}!")
    }

    override fun onLostRespawn(event: LostRespawnEvent) {
        ChatHelper.sendWarning("The team ${event.losts.first()} lost their respawn!")
        event.losts.forEach {
            // fuck NMS
            @Suppress("DEPRECATION")
            it.player.sendTitle("${ChatHelper.error} You lost your respawn!", "")
            // remove respawn
        }
    }

    override fun onRequestBuy(event: RequestBuyEvent) {
        onBuy(event.toBuyEvent())
    }

    override fun onRequestChangeShopSection(event: RequestChangeShopSectionEvent) {
    }

    override fun onRequestShop(event: RequestShopEvent) {
//        val shop = event.shop
//        shop.open(event.player)
    }

    override fun onRespawn(event: RespawnEvent) {
        event.player.player.gameMode = GameMode.SURVIVAL
//        event.player.player.teleport(event.player.team.spawn)
        event.player.toDefaultCondition()
        ChatHelper.sendInfoToPlayer(event.player.player, "You have been respawned!")
    }
}