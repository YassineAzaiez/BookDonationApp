package com.example.booksdonationapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <T> LiveData<T>.reObserve(owner: LifecycleOwner , observer: Observer<T>){
    removeObserver(observer)
    observe(owner,observer)
}

fun RecyclerView.loadMore(load : () ->Unit){
  addOnScrollListener(object  : RecyclerView.OnScrollListener(){
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
          super.onScrolled(recyclerView, dx, dy)
          val layout =layoutManager as LinearLayoutManager
          val visibleItemCount = layout.childCount
          val totalItem = layout.itemCount
          val lastVisibleItems = layout.findLastVisibleItemPosition()

          if(((visibleItemCount+lastVisibleItems)>= totalItem && dy>0)){
              load()
          }
      }
  })
}

fun Context.getColor(@ColorRes colorRes : Int) : Int {
    return  ContextCompat.getColor(this,colorRes)
}

fun Context.getDrawable(@DrawableRes drawableRes : Int) : Drawable? {
    return  ContextCompat.getDrawable(this,drawableRes)
}

fun Activity.hideKeyBoard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(currentFocus?.windowToken,0)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

inline fun <reified T : Any> Activity.startActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent,options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

fun Activity.hideSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.let {
            // Default behavior is that if navigation bar is hidden, the system will "steal" touches
            // and show it again upon user's touch. We just want the user to be able to show the
            // navigation bar by swipe, touches are handled by custom code -> change system bar behavior.
            // Alternative to deprecated SYSTEM_UI_FLAG_IMMERSIVE.
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            // make navigation bar translucent (alternative to deprecated
            // WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            // - do this already in hideSystemUI() so that the bar
            // is translucent if user swipes it up
          //  window.navigationBarColor = getColor(R.color.internal_black_semitransparent_light)
            // Finally, hide the system bars, alternative to View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            // and SYSTEM_UI_FLAG_FULLSCREEN.
            it.hide(WindowInsets.Type.systemBars())
        }
    } else {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                // Do not let system steal touches for showing the navigation bar
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        // Keep the app content behind the bars even if user swipes them up
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        // make navbar translucent - do this already in hideSystemUI() so that the bar
        // is translucent if user swipes it up
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }


}

fun String.underlinedBold(
    wordToEdit: String,
    click: ClickableSpan?
): SpannableString {
    val spannableString = SpannableString(this)
    val startPosition = this.indexOf(wordToEdit)
    val endPosition = this.indexOf(wordToEdit) + wordToEdit.length



    spannableString.apply {

        setSpan(
            StyleSpan(Typeface.BOLD),
            startPosition,
            endPosition,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //make the wordToEdit underLined
        setSpan(
            UnderlineSpan(),
            startPosition,
            endPosition,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //make the wordToEdit clickable
        click?.let {
            setSpan(
                it,
                startPosition,
                endPosition,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannableString
    }
}

/**
 * Extension to navigate to a destination, clearing the current back stack and launching the
 * fragment as `Single Top`, from the current navigation graph. This supports both navigating via an
 * {@link NavDestination#getAction(int) action} and directly navigating to a destination.
 *
 * @param resId an [androidx.navigation.NavDestination.getAction] id or a destination id to
 *              navigate to
 * @param args arguments to pass to the destination
 */
fun NavController.navigateSingleTop(@IdRes resId: Int, args: Bundle? = null) {
    val hostDestinationId = graph.startDestination
    val navOptions = NavOptions.Builder()
        .setPopUpTo(hostDestinationId, true)
        .setLaunchSingleTop(true)
        .build()
    navigate(resId, args, navOptions)
}

