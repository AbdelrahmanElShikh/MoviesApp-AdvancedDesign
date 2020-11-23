package com.noha.moviesadvanced.presentation.util.snackbars

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler
import com.google.android.material.snackbar.Snackbar
import com.noha.moviesadvanced.R
import kotlinx.android.synthetic.main.activity_main.*


/**
 *@author: Abdel-Rahman El-Shikh on 25-Apr-20.
 */
class RetrySnackBar {
    companion object {
        fun show(retryHandler: RetryHandler, fragment: Fragment, msg: String) {
            DynamicSnackBar.make(
                fragment.view,
                msg,
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(fragment.requireContext().getString(R.string.retry_button_title)) {
                    if (fragment.view != null)
                        retryHandler.onRetry()
                }
                .show()
        }

        fun show(retryHandler: RetryHandler?, fragment: Context, msgRes: Int) {
            DynamicSnackBar.make(
                //TODO : need to change this backgroundView image to view which related to a fragment after refactoring to navigation comoinent
                //TODO : ya abdel-rahmaaaaaan DON'T FORGET
                (fragment as Activity).backgroundImageView,
                fragment.getString(msgRes),
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(fragment.getString(R.string.retry_button_title)) {
                    if (fragment.backgroundImageView != null)
                        retryHandler?.onRetry()
                }
                .show()
        }

    }
}