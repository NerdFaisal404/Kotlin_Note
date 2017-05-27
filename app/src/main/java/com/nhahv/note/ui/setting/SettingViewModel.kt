package com.nhahv.note.ui.setting

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.databinding.Bindable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.ui.reminder.ReminderActivity
import com.nhahv.note.ui.security.SecurityActivity
import com.nhahv.note.ui.security.SecurityViewModel.Companion.TITLE_CANCEL_SECURITY
import com.nhahv.note.ui.security.SecurityViewModel.Companion.TITLE_INPUT_SECURITY
import com.nhahv.note.util.Request.REQUEST_REMINDER
import com.nhahv.note.util.Request.REQUEST_SECURITY
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class SettingViewModel(activity: AppCompatActivity, fragment: SettingFragment) : BaseViewModel(
    activity) {

  val mContext: Context = activity.applicationContext
  val mFragment: Fragment = fragment
  val mPreferences = SharePreference.getInstances(mContext)

  @get : Bindable
  var mChecked: Boolean = mPreferences[PREF_IS_SECURITY, Boolean::class.java]
    set(value) {
      field = value
      notifyPropertyChanged(BR.mChecked)
    }

  @get: Bindable
  var mImageUrl: String = ""
    set(value) {
      field = value
      notifyPropertyChanged(BR.mImageUrl)
    }

  init {
  }

  fun onEditInfo() {

  }

  fun onReminder() {
    mFragment.startActivityForResult(ReminderActivity.newIntent(mContext), REQUEST_REMINDER)
  }

  fun onCheckedReminder(checked: Boolean) {
  }

  fun onClickSecurity() {
    if (mChecked) {
      mFragment.startActivityForResult(SecurityActivity.newIntent(mContext, TITLE_CANCEL_SECURITY),
          REQUEST_SECURITY)
    } else {
      mFragment.startActivityForResult(SecurityActivity.newIntent(mContext, TITLE_INPUT_SECURITY),
          REQUEST_SECURITY)
    }
  }

  fun onChangeLanguage() {
  }

  fun onLogout() {
  }


  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode != RESULT_OK) return

    when (requestCode) {
      REQUEST_SECURITY -> {
        mChecked = mPreferences[PREF_IS_SECURITY, Boolean::class.java]
      }
      REQUEST_REMINDER -> {

      }
    }
  }

}

