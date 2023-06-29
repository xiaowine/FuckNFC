package com.xiaowine.fucknfc.tools

import com.xiaowine.fucknfc.BuildConfig
import android.util.Log
import com.xiaowine.fucknfc.config.XposedOwnSP
import de.robv.android.xposed.XposedBridge
import statusbar.lyric.config.ActivityOwnSP

object LogTools {
    private const val maxLength = 4000
    const val TAG = "Example"


    private fun log(obj: Any?, toXposed: Boolean = false, toLogd: Boolean = false) {
        if (!BuildConfig.DEBUG) return
        if (toXposed) {
            if (!XposedOwnSP.config.printXpLog) return
        } else {
            if (!ActivityOwnSP.config.printXpLog) return
        }
        val content = if (obj is Throwable) Log.getStackTraceString(obj) else obj.toString()
        if (content.length > maxLength) {
            val chunkCount = content.length / maxLength
            for (i in 0..chunkCount) {
                val max = 4000 * (i + 1)
                if (max >= content.length) {
                    if (toXposed) XposedBridge.log("$TAG: ${content.substring(maxLength * i)}")
                    if (toLogd) Log.d(TAG, content.substring(maxLength * i))
                } else {
                    if (toXposed) XposedBridge.log("$TAG: ${content.substring(maxLength * i, max)}")
                    if (toLogd) Log.d(TAG, content.substring(maxLength * i, max))
                }
            }
        } else {
            if (toXposed) XposedBridge.log("$TAG: $content")
            if (toLogd) Log.d(TAG, content)
        }
    }

    fun xp(obj: Any?) {
        log(obj, toXposed = true)
    }

    fun app(obj: Any?) {
        log(obj, toLogd = true)
    }

}
