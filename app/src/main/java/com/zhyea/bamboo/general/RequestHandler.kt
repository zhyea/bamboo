package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface RequestHandler {


    fun requestDeactive(request: ActivatedHandler?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}