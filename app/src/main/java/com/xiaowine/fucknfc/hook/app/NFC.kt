package com.xiaowine.fucknfc.hook.app

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.xiaowine.fucknfc.hook.BaseHook


object NFC : BaseHook() {
    override fun init() {
        loadClass("com.android.nfc.NfcService").methodFinder().filterByName("initSoundPool").first().createHook {
            before {
                it.result = null
            }
        }
    }
}