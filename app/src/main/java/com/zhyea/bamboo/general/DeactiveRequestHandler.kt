package com.zhyea.bamboo.general

/**
 * ix
 * @author robin
 */
class DeactiveRequestHandler(val request: BaseActivity) : RequestHandler {


    override fun requestDeactive(request: BaseActivity?): Boolean {
        return true;
    }

    override fun requestHideMenu() {
        TODO("Not yet implemented")
    }

    override fun requestShowMenu() {
        TODO("Not yet implemented")
    }
}