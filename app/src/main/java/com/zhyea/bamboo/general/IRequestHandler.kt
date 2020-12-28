package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface IRequestHandler {


    fun requestDeactive(request: ActivatedHandler?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}