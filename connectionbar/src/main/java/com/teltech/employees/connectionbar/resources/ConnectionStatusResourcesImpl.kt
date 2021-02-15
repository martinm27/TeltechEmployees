package com.teltech.employees.connectionbar.resources

import android.content.res.Resources
import com.teltech.employees.connectionbar.R

class ConnectionStatusResourcesImpl(private val resources: Resources) :
    ConnectionStatusResources {

    override fun connectedLabelText(): String =
        resources.getString(R.string.connectionbar_connectedStatus)

    override fun disconnectedLabelText(): String =
        resources.getString(R.string.connectionbar_disconnectedStatus)

    override fun connectedDrawableRes() = R.drawable.ic_check_white

    override fun connectedLabelBackgroundColorRes(): Int = R.color.green

    override fun disconnectedLabelBackgroundColorRes(): Int = R.color.red
}
