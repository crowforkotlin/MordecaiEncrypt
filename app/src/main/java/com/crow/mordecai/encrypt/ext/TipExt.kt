@file:Suppress("unused")

package com.crow.mordecai.encrypt.ext

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.crow.mordecai.encrypt.app

/*************************
 * @Machine: RedmiBook Pro 15
 * @RelativePath: cn\barry\base\extensions\TipExt.kt
 * @Path: D:\Barry\B_study\Android\Android_Project\JetpackApp\lib_base\src\main\java\cn\barry\base\extensions\TipExt.kt
 * @Author: CrowForKotlin
 * @Time: 2022/5/1 20:58 周日 下午
 * @Description: Tips Ext
 * @formatter:off
 *************************/

const val TIPS_TAG = "MordecaiFileEncrypt"

fun Any?.log(tag: String = TIPS_TAG) {
    Log.i(tag, this.toString())
}

private var mToast: Toast? = null
private var mToastHide: Boolean = false

/* String Toast */
fun toast(msg: String, isShort: Boolean = true, context: Context = app.applicationContext) {
    if (mToastHide) return
    mToast?.cancel()
    mToast = Toast.makeText(context, msg, if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
    mToast?.show()
}

/* CharSequence Toast */
fun toast(charSequence: CharSequence, isShort: Boolean = true) {
    if (mToastHide) return
    mToast?.cancel()
    mToast = Toast.makeText(app.applicationContext, charSequence, if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
    mToast?.show()
}

fun error(message: Any): Nothing = throw IllegalStateException(message.toString())