
package com.xiaowine.fucknfc.config

import com.xiaowine.fucknfc.tools.Tools


object XposedOwnSP {

    val config: Config by lazy { Config(Tools.getPref("Config")) }
}