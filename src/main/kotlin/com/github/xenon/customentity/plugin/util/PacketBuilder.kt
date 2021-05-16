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
package com.github.xenon.customentity.plugin.util

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.PacketContainer
import com.google.common.base.Charsets
import com.sun.tools.jdi.Packet
import java.io.ByteArrayOutputStream
import java.io.DataOutput
import java.io.DataOutputStream
import java.io.IOException

class PacketBuilder private constructor() : DataOutput {
    private val bOut: ByteArrayOutputStream
    private val dOut: DataOutputStream
    override fun write(b: Int) {
        bOut.write(b)
    }

    override fun write(b: ByteArray) {
        try {
            bOut.write(b)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        bOut.write(b, off, len)
    }

    override fun writeBoolean(v: Boolean) {
        try {
            dOut.writeBoolean(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeByte(v: Int) {
        try {
            dOut.writeByte(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeShort(v: Int) {
        try {
            dOut.writeShort(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeChar(v: Int) {
        try {
            dOut.writeChar(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeInt(v: Int) {
        try {
            dOut.writeInt(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeLong(v: Long) {
        try {
            dOut.writeLong(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeFloat(v: Float) {
        try {
            dOut.writeFloat(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeDouble(v: Double) {
        try {
            dOut.writeDouble(v)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeBytes(s: String) {
        try {
            dOut.writeBytes(s)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeChars(s: String) {
        try {
            dOut.writeChars(s)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    override fun writeUTF(s: String) {
        try {
            dOut.writeUTF(s)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    fun writeString(s: String?) {
        if (s == null) {
            try {
                dOut.writeShort(0)
            } catch (e: IOException) {
                throw AssertionError(e)
            }
            return
        }
        val bytes = s.toByteArray(Charsets.UTF_8)
        try {
            dOut.writeShort(bytes.size)
            dOut.write(bytes)
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    fun reset() {
        bOut.reset()
    }

    fun toByteArray(): ByteArray {
        return bOut.toByteArray()
    }

    fun build(): PacketContainer {
        return PacketContainer(PacketType.Play.Server.CUSTOM_PAYLOAD).apply {
            modifier
                .write(0, "CustomEntity")
            byteArrays
                .write(0, toByteArray())
        }
    }

    companion object {
        private val INSTANCE = PacketBuilder()
        val instance: PacketBuilder
            get() {
                INSTANCE.reset()
                return INSTANCE
            }
    }

    init {
        val bOut = ByteArrayOutputStream()
        this.bOut = bOut
        dOut = DataOutputStream(bOut)
    }
}