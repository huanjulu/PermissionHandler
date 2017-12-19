@file:JvmName("PermissionHandler")

package huanjulu.runtimpermission.com

import android.util.Log

/**
 * Created by huanjulu on 2/11/17.
 */

inline fun android.app.Activity.isLollipopOrBellow(): Boolean = (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP)


inline fun android.app.Activity.isPermissionGranted(permission: AppPermission) = (android.support.v4.content.PermissionChecker.checkSelfPermission(this, permission.permissionName) == android.content.pm.PackageManager.PERMISSION_GRANTED)


inline fun android.app.Activity.isRationaleNeeded(permission: AppPermission) = android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale(this, permission.permissionName)


inline fun android.app.Activity.requestPermission(permission: AppPermission) = android.support.v4.app.ActivityCompat.requestPermissions(this, arrayOf(permission.permissionName), permission.requestCode)


inline fun android.app.Activity.rationaleOccasion(permission: AppPermission): Boolean {


    val sp = getSharedPreferences(permission.permissionName, android.content.Context.MODE_PRIVATE)

    val editor = sp.edit()

    if (sp.getBoolean(permission.permissionName, false)) {

        editor.putBoolean(permission.permissionName, false)

        editor.commit()

    }

    return false

}


inline fun android.app.Activity.checkPermission(permission: AppPermission, onGranted: (AppPermission) -> Unit,
                                                onDenied: (AppPermission) -> Unit, onRationaleNeeded: (AppPermission) -> Unit) {
    when {

        isLollipopOrBellow() || isPermissionGranted(permission) -> onGranted(permission)

        !isRationaleNeeded(permission) && !rationaleOccasion(permission) -> {

            onRationaleNeeded(permission)
        }

        else -> onDenied(permission)
    }
}


fun onRequestPermissionsResultReceived(requestCode: Int, permissions: Array<out String>, grantResults: IntArray, onPermissionGranted: (AppPermission) -> Unit, onPermissionDenied: (AppPermission) -> Unit) {

    AppPermission.permissions.find {

        it.requestCode == requestCode

    }?.let {

        val permissionGrantResult = mapPermissionsAndResults(permissions, grantResults)[it.permissionName]

        if (android.content.pm.PackageManager.PERMISSION_GRANTED == permissionGrantResult) {

            onPermissionGranted(it)

        } else {

            onPermissionDenied(it)
        }
    }
}

inline fun mapPermissionsAndResults(permissions: Array<out String>, grantResults: IntArray): Map<String, Int> = permissions.mapIndexedTo(mutableListOf()) { index, permission -> permission to grantResults[index] }.toMap()

