package com.xiaowine.fucknfc.hook


import com.github.kyuubiran.ezxhelper.EzXHelper
import com.xiaowine.fucknfc.hook.app.NFC
import com.xiaowine.fucknfc.tools.LogTools
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage, IXposedHookZygoteInit {


    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        EzXHelper.initHandleLoadPackage(lpparam)
        if (lpparam.packageName == "com.android.nfc") {
            initHooks(NFC)
        }
    }


    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        EzXHelper.initZygote(startupParam)
    }

    private fun initHooks(vararg hook: BaseHook) {
        hook.forEach {
            runCatching {
                if (it.isInit) return@forEach
                it.init()
                it.isInit = true
                LogTools.xp("Inited hook: ${it.javaClass.simpleName}")
            }.exceptionOrNull()?.let {
                LogTools.xp("Failed init hook: ${it.javaClass.simpleName}")
            }
        }
    }
}