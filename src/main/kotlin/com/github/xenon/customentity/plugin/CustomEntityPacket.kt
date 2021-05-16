/*
 * Copyright (c) 2019 Noonmaru
 *
 * Licensed under the General Public License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/gpl-2.0.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.xenon.customentity.plugin

import com.comphenix.protocol.ProtocolLibrary
import com.github.xenon.customentity.plugin.util.PacketBuilder
import com.sun.tools.jdi.Packet
import org.bukkit.Bukkit

object CustomEntityPacket {
    const val CHANNEL = "CustomEntity"
    private const val REGISTER = 0
    private const val UNREGISTER = 1
    private const val COLOR = 2
    private const val SCALE = 3
    private const val COLOR_AND_SCALE = 4
    private const val FAKE_ENTITY = 5
    fun register(entityId: Int) {
        val builder: PacketBuilder = PacketBuilder.instance
        builder.write(REGISTER)
        builder.writeInt(entityId)
        for(player in Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, builder.build())
        }
    }

    fun unregister(vararg entityIds: Int){
        val builder: PacketBuilder = PacketBuilder.instance
        builder.write(UNREGISTER)
        val length = entityIds.size
        builder.writeShort(length)
        for (i in 0 until length) builder.writeInt(entityIds[i])
        for(player in Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, builder.build())
        }
    }

    fun scale(entityId: Int, scaleX: Float, scaleY: Float, scaleZ: Float, duration: Int) {
        val builder: PacketBuilder = PacketBuilder.instance
        builder.write(SCALE)
        builder.writeInt(entityId)
        builder.writeFloat(scaleX)
        builder.writeFloat(scaleY)
        builder.writeFloat(scaleZ)
        builder.writeInt(duration)
        for(player in Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, builder.build())
        }
    }

    fun color(entityId: Int, colorR: Int, colorG: Int, colorB: Int, duration: Int) {
        val builder: PacketBuilder = PacketBuilder.instance
        builder.write(COLOR)
        builder.writeInt(entityId)
        builder.writeByte(colorR)
        builder.writeByte(colorG)
        builder.writeByte(colorB)
        builder.writeInt(duration)
        for(player in Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, builder.build())
        }
    }

    fun colorAndScale(
        entityId: Int,
        colorR: Int,
        colorG: Int,
        colorB: Int,
        scaleX: Float,
        scaleY: Float,
        scaleZ: Float,
        duration: Int
    ) {
        val builder: PacketBuilder = PacketBuilder.instance
        builder.write(COLOR_AND_SCALE)
        builder.writeInt(entityId)
        builder.writeByte(colorR)
        builder.writeByte(colorG)
        builder.writeByte(colorB)
        builder.writeFloat(scaleX)
        builder.writeFloat(scaleY)
        builder.writeFloat(scaleZ)
        builder.writeInt(duration)
        for(player in Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, builder.build())
        }
    }
}