package com.zhyea.bamboo.ui.general

/**
 *
 */
class DeactiveRequestHandler(val request: DeactiveRequest) : RequestHandler {


    override fun requestDeactive(request: DeactiveRequest?): Boolean {

        return true;
    }

    override fun requestHideMenu() {
        TODO("Not yet implemented")
    }

    override fun requestShowMenu() {
        TODO("Not yet implemented")
    }
}