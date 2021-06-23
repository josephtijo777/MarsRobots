package com.example.marsrobots.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.marsrobots.network.response.Item
import com.example.marsrobots.room.ImageEntity
import kotlin.reflect.full.memberProperties

/**
 * To return height of device in pixels
 * @return Integer that specify absolute height of the available display size in pixels.
 */
fun Context.getDeviceHeight(): Int {
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        val display = display
        display?.getRealMetrics(dm)
    } else {
        @Suppress("DEPRECATION")
        val display = wm.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(dm)
    }

    return dm.heightPixels
}

/**
 * To return width of device in pixels
 * @return Integer that specify absolute width of the available display size in pixels.
 */
fun Context.getDeviceWidth(): Int {
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        val display = display
        display?.getRealMetrics(dm)
    } else {
        @Suppress("DEPRECATION")
        val display = wm.defaultDisplay
        @Suppress("DEPRECATION")
        display.getMetrics(dm)
    }

    return dm.widthPixels
}

/**
 * To convert dp to **pixel**.
 * @param dp to convert.
 * @return Dimension in pixel.
 */
fun Context.dpTOpx(dp: Float): Float {
    return dp * resources.displayMetrics.density
}

/**
 * To convert pixel to **dp**.
 * @param px to convert.
 * @return Dimension in dp.
 */
fun Context.pxTOdp(px: Float): Float {
    return px / resources.displayMetrics.density
}

/**
 * To perform fragment transaction and commit the transaction
 * @param func FragmentTransaction to be performed
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

/**
 * To add a fragment into a view
 * @param fragment Fragment to be added
 * @param frameId Id of the view where fragment is to be added
 * @param tag String tag of the fragment to be added
 */
fun AppCompatActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String = fragment.javaClass.simpleName
) {
    supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
}

/**
 * To replace a fragment from the view
 * @param fragment Fragment to be replaced
 * @param frameId Id of the view where fragment should be replaced
 * @param tag String tag of the fragment to be replaced
 */
fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String = fragment.javaClass.simpleName
) {
    supportFragmentManager.inTransaction {
        replace(
            frameId,
            fragment,
            tag
        )
    }
}

/**
 * To replace a fragment from the view and add back stack entry
 * @param fragment Fragment to be replaced
 * @param frameId Id of the view where fragment should be replaced
 * @param tag String tag of the fragment to be replaced
 * @param addToStack Whether addToBackStack is needed
 */
fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String = fragment.javaClass.simpleName,
    addToStack: Boolean
) {
    supportFragmentManager.inTransaction {
        if (addToStack) replace(frameId, fragment, tag)
            .addToBackStack(tag)
        else
            replace(frameId, fragment, tag)
    }
}

/**
 * To replace a fragment from the view , add back stack entry and clear back stack
 * @param fragment Fragment to be replaced
 * @param frameId Id of the view where fragment should be replaced
 * @param tag String tag of the fragment to be replaced
 * @param addToStack Boolean to specify the addToBackStack is needed
 * @param clearBackStack Boolean to specify  clear the fragment from backStack is needed
 */
fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String = fragment.javaClass.simpleName,
    addToStack: Boolean,
    clearBackStack: Boolean
) {
    supportFragmentManager.inTransaction {

        if (clearBackStack && supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (addToStack) replace(frameId, fragment, tag)
            .addToBackStack(tag)
        else
            replace(frameId, fragment, tag)
    }
}

/**
 * To add a fragment into a view and add to back stack
 * @param fragment Fragment to be added
 * @param frameId Id of the view where fragment is to be added
 * @param tag String tag of the fragment to be added
 * @param addToStack Boolean to specify the addToBackStack is needed
 */
fun AppCompatActivity.addFragment(
    fragment: Fragment,
    frameId: Int,
    tag: String = fragment.javaClass.simpleName,
    addToStack: Boolean
) {
    supportFragmentManager.inTransaction {
        if (addToStack) add(frameId, fragment, tag)
            .addToBackStack(tag)
        else add(frameId, fragment)
    }
}

/**
 * To get the current fragment
 * @return Returns the current fragment
 */
fun AppCompatActivity.getCurrentFragment(): Fragment? {
    val fragmentManager = supportFragmentManager
    var fragmentTag: String? = ""

    if (fragmentManager.backStackEntryCount > 0)
        fragmentTag =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name

    return fragmentManager.findFragmentByTag(fragmentTag)
}

/**
 * To get the current visible fragment
 * @return The current visible fragment
 */
fun AppCompatActivity.getVisibleFragment(): Fragment? {
    val fragmentManager: FragmentManager = this.supportFragmentManager
    val fragments = fragmentManager.fragments
    for (fragment in fragments) {
        if (fragment != null && fragment.isVisible) return fragment
    }
    return null
}

/**
 * To check is the fragment of specified tag is added or not
 * @return Returns true if it is added else null
 */
fun AppCompatActivity.isFragmentAdded(tag: String): Boolean? {
    return supportFragmentManager.findFragmentByTag(tag)?.isAdded
}

/**
 * Show the soft keyboard.
 */
fun AppCompatActivity.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/**
 * Hide the soft keyboard.
 */
fun AppCompatActivity.hideKeyboard() {
    val view = currentFocus
    if (view != null) {
        hideKeyboard(this, view.windowToken)
    }
}

/**
 * Hide the soft keyboard using activity and window token
 * @param activity Activity of the keyboard to be hidden
 * @param windowToken An instance of [IBinder]
 */
fun hideKeyboard(activity: Activity?, windowToken: IBinder?) {
    if (activity == null) return
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Item.toImageEntityRecord() = with(::ImageEntity) {
    val propertiesByName = Item::class.memberProperties.associateBy { it.name }

    callBy(args = parameters.associate { parameter ->
        parameter to when (parameter.name) {
            "url" -> links[0].href
            "description" -> data[0].description
            "date" -> data[0].dateCreated
            else -> propertiesByName[parameter.name]?.get(this@toImageEntityRecord)
        }
    })
}