package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface RequestHandler {


    fun requestDeactive(request: ActivityController?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}