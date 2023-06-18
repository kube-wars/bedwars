package world.anhgelus.kubewars.bedwars

import world.anhgelus.kubewars.kubecore.PluginBase
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
    }

    companion object : CompanionBase()
}