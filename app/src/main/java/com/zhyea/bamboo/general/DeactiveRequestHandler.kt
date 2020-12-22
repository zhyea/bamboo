package com.zhyea.bamboo.general

/**
 * ix
 * @author robin
 */
class DeactiveRequestHandler(val request: ActivatedHandler) : IHandler {


    override fun requestDeactive(request: ActivatedHandler?): Boolean {
        return true;
    }

    override fun requestHideMenu() {
        TODO("Not yet implemented")
    }

    override fun requestShowMenu() {
        TODO("Not yet implemented")
    }
}