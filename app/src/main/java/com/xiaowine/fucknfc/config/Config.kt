package com.xiaowine.fucknfc.config

import android.content.SharedPreferences
import com.xiaowine.fucknfc.BuildConfig
import com.xiaowine.fucknfc.tools.ConfigTools
import de.robv.android.xposed.XSharedPreferences

class Config {
    private var config: ConfigTools

    constructor(xSharedPreferences: XSharedPreferences?) {
        config = ConfigTools(xSharedPreferences)
    }

    constructor(sharedPreferences: SharedPreferences) {
        config = ConfigTools(sharedPreferences)
    }

    fun update() {
        config.update()
    }


    fun getString(key: String, def: String = ""): String {
        return config.opt(key, def)
    }

    fun getBoolean(key: String, def: Boolean = false): Boolean {
        return config.opt(key, def)
    }

    fun getInt(key: String, def: Int = 0): Int {
        return config.opt(key, def)
    }


    fun setValue(key: String, value: Any) {
        config.put(key, value)
    }

    var printXpLog: Boolean
        get() {
            if (BuildConfig.DEBUG) {
                return true
            }
            return config.opt("printXpLog", false)
        }
        set(value) {
            config.put("printXpLog", value)
        }

    fun clear() {
        config.clearConfig()
    }


}
