package com.dxp.shiro.web.vo

import com.dxp.shiro.common.ErrorEnum
import java.io.Serializable

/**
 * jaxa返回的JSON
 *
 * @author carzy
 * @date 2020/8/6
 */
class R<T> : Serializable {
    var code = 0
    var msg: String? = null
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }

    companion object {

        fun <T> suc(data: T): R<T> {
            return of(data, ErrorEnum.S_200)
        }

        fun err(status: ErrorEnum): R<String> {
            return err("", status)
        }

        @JvmStatic
        fun err(data: String, status: ErrorEnum): R<String> {
            return of(data, status)
        }

        fun <T> of(data: T, status: ErrorEnum): R<T> {
            val r = R<T>()
            r.code = status.errorCode
            // todo(国际化. 英文时则用 status.name())
            r.msg = status.errorMsg
            r.setData(data)
            return r
        }
    }
}