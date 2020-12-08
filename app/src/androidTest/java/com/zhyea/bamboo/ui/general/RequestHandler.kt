package com.zhyea.bamboo.ui.general

/**
 * jc
 * @author robin
 */
interface RequestHandler {


    fun requestDeactive(request: BaseActivityManager?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}