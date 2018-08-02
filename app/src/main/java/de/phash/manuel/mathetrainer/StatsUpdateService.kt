/**
 * Copyright (c) 2018 Manuel "Phash" Roedig
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package de.phash.manuel.mathetrainer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class StatsUpdateService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val instance: StatsUpdateService by lazy { StatsUpdateService.Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = StatsUpdateService()
    }

    init {
        println("This ($this) is a singleton")
    }

    fun increaseAllStates() {
        var sharedPref = this.getSharedPreferences("de.phash.manuel.mathetrainer", Context.MODE_PRIVATE)
        Status.instance.richtigAdd()
        Status.instance.versucheAdd()
        Status.instance.aufgabeAdd()
        var richtigPers = sharedPref.getInt(Status.RICHTIGE, 0)
        sharedPref.edit().putInt(Status.RICHTIGE, richtigPers).apply()
        var versuchPers = sharedPref.getInt(Status.VERSUCHE, 0)
        sharedPref.edit().putInt(Status.VERSUCHE, versuchPers).apply()
        var aufgabePers = sharedPref.getInt(Status.AUFGABEN, 0)
        sharedPref.edit().putInt(Status.AUFGABEN, aufgabePers).apply()

        sharedPref.edit().putInt(Status.AUFGABENALLTIME, (sharedPref.getInt(Status.AUFGABENALLTIME, 0) + 1)).apply()
        sharedPref.edit().putInt(Status.VERSUCHEALLTIME, (sharedPref.getInt(Status.VERSUCHEALLTIME, 0) + 1)).apply()
        sharedPref.edit().putInt(Status.RICHTIGEALLTIME, (sharedPref.getInt(Status.RICHTIGEALLTIME, 0) + 1)).apply()
    }
}