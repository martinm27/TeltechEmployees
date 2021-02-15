package com.teltech.employees.connectionbar.resources

interface ConnectionStatusResources {

    fun connectedLabelText() : String

    fun disconnectedLabelText() : String

    fun connectedDrawableRes() : Int

    fun connectedLabelBackgroundColorRes() : Int

    fun disconnectedLabelBackgroundColorRes() : Int
}
