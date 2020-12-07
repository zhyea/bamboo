package com.zhyea.bamboo.ui.general

interface RequestHandler {


    fun requestDeactive(request: DeactiveRequest?): Boolean

    fun requestHideMenu()

    fun requestShowMenu()


}