package com.github.xenon.customentity.plugin

import com.github.monun.kommand.argument.integer
import com.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CustomEntityPlugin : JavaPlugin() {
    override fun onEnable() {
        server.messenger.registerOutgoingPluginChannel(this, CustomEntityPacket.CHANNEL)
        instance = this
        registerCommands()
    }

    private fun registerCommands() {
        kommand {
            register("ce") {
                then("register") {
                    then("target") {
                        
                    }
                    then("entityId" to integer()) {

                    }
                }
                then("unregister") {
                    then("target") {

                    }
                    then("entityId" to integer()) {

                    }
                }
                then("scale") {
                    then("target") {

                    }
                    then("entityId" to integer()) {

                    }
                }
                then("color") {
                    then("target") {

                    }
                    then("entityId" to integer()) {

                    }
                }
                then("colorandscale") {
                    then("target") {

                    }
                    then("entityId" to integer()) {

                    }
                }
            }
        }
    }

    companion object {
        var instance: CustomEntityPlugin? = null
            private set
    }
}
