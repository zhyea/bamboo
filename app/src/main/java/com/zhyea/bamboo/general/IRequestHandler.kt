package com.zhyea.bamboo.general

/**
 * jc
 * @author robin
 */
interface IRequestHandler {


    fun requestDeactive(request: ContentController?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}