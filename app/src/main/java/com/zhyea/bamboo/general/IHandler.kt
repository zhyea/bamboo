package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface IHandler {


    fun requestDeactive(request: ActivatedHandler?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}