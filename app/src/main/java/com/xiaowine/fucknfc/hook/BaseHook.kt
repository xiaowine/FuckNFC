package com.xiaowine.fucknfc.hook

abstract class BaseHook {
    var isInit: Boolean = false
    abstract fun init()
}