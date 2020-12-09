package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface RequestHandler {


    fun requestDeactive(request: BaseActivity?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}