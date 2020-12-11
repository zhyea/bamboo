package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface IController {


    fun requestDeactive(request: ActivatedController?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}