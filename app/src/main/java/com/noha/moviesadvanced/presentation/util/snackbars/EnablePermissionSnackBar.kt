package com.noha.moviesadvanced.presentation.util.snackbars

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.noha.moviesadvanced.R


/**
 *@author: Abdel-Rahman El-Shikh on 16-May-20.
 */
class EnablePermissionSnackBar {
    companion object {
        fun make(view: View?, fragment: Fragment, text: String, length: Int): Snackbar {
            val snackbar = DynamicSnackBar.make(view, text, length)
            return snackbar.setAction(view!!.context.getString(R.string.ok_snackbar)) {
                if(fragment.view != null)
                    openPermissionsScreen(fragment)
            }
        }

        private fun openPermissionsScreen(fragment: Fragment) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", fragment.requireActivity().packageName, null)
            intent.data = uri
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            fragment.startActivity(intent)
        }
    }

}