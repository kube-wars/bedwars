package world.anhgelus.kubewars.bedwars.game

import world.anhgelus.kubewars.kubecore.api.game.event.GameListener
import world.anhgelus.kubewars.kubecore.api.game.event.player.life.*
import world.anhgelus.kubewars.kubecore.api.game.event.player.shop.*
import world.anhgelus.kubewars.kubecore.api.game.event.state.*

object GameListener : GameListener() {
    override fun onBuy(event: BuyEvent) {

    }

    override fun onCloseShop(event: CloseShopEvent) {
    }

    override fun onDeath(event: DeathEvent) {
    }

    override fun onFinalDeath(event: FinalDeathEvent) {
    }

    override fun onFinalKill(event: FinalKillEvent) {
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
    }

    override fun onLostRespawn(event: LostRespawnEvent) {
    }

    override fun onRequestBuy(event: RequestBuyEvent) {
    }

    override fun onRequestChangeShopSection(event: RequestChangeShopSectionEvent) {
    }

    override fun onRequestShop(event: RequestShopEvent) {
    }

    override fun onRespawn(event: RespawnEvent) {
    }
}