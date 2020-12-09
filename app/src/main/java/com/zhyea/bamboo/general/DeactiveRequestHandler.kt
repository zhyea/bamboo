package com.zhyea.bamboo.general

/**
 * ix
 * @author robin
 */
class DeactiveRequestHandler(val request: ActivityController) : RequestHandler {


    override fun requestDeactive(request: ActivityController?): Boolean {
        return true;
    }

    override fun requestHideMenu() {
        TODO("Not yet implemented")
    }

    override fun requestShowMenu() {
        TODO("Not yet implemented")
    }
}