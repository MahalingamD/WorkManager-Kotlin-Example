package com.example.simplework.utils

import android.util.Log
import com.example.simplework.BuildConfig

class Logger {

    private val TAG: String = BuildConfig.APPLICATION_ID

    fun e(Msg: String) {
        LogIt(Log.ERROR, TAG, Msg)
    }

    fun e(Tag: String?, Msg: String) {
        LogIt(Log.ERROR, Tag, Msg)
    }

    fun i(Msg: String) {
        LogIt(Log.INFO, TAG, Msg)
    }

    fun i(Tag: String?, Msg: String) {
        LogIt(Log.INFO, Tag, Msg)
    }

    fun d(Msg: String) {
        LogIt(Log.DEBUG, TAG, Msg)
    }

    fun d(Tag: String?, Msg: String) {
        LogIt(Log.DEBUG, Tag, Msg)
    }

    fun v(Msg: String) {
        LogIt(Log.VERBOSE, TAG, Msg)
    }

    fun v(Tag: String?, Msg: String) {
        LogIt(Log.VERBOSE, Tag, Msg)
    }

    fun w(Msg: String) {
        LogIt(Log.WARN, TAG, Msg)
    }

    fun w(Tag: String?, Msg: String) {
        LogIt(Log.WARN, Tag, Msg)
    }

    private fun LogIt(LEVEL: Int, Tag: String?, Message: String) {
        if (BuildConfig.DEBUG) Log.println(LEVEL, Tag ?: TAG, Message)
    }
}