package com.teltech.employees.connectionbar.ui

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

sealed class ConnectionStatusViewStateModel {
    object EmptyConnectionStatusViewStateModel : ConnectionStatusViewStateModel()
    data class DisconnectedStatusViewStateModel(val connectionStatus: String, @ColorRes val backgroundColor: Int) : ConnectionStatusViewStateModel()
    data class ConnectedStatusViewStateModel(val connectionStatus: String, @ColorRes val backgroundColor: Int, @DrawableRes val drawableStart: Int) : ConnectionStatusViewStateModel()
}
