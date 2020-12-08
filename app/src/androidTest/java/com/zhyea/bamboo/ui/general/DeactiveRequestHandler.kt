package com.zhyea.bamboo.ui.general

/**
 * ix
 * @author robin
 */
class DeactiveRequestHandler(val request: BaseActivityManager) : RequestHandler {


    override fun requestDeactive(request: BaseActivityManager?): Boolean {

        return true;
    }

    override fun requestHideMenu() {
        TODO("Not yet implemented")
    }

    override fun requestShowMenu() {
        TODO("Not yet implemented")
    }
}