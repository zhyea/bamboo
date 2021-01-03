package com.zhyea.bamboo.general

/**
 * ix
 * @author robin
 */
class DeactiveRequestHandler(val request: ContentController) : IRequestHandler {


    override fun requestDeactive(request: ContentController?): Boolean {
        return true
    }

    override fun requestHideMenu() {
        TODO("Not yet implemented")
    }

    override fun requestShowMenu() {
        TODO("Not yet implemented")
    }
}