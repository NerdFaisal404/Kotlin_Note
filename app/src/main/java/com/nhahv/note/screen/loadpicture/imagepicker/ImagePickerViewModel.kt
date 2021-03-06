package com.nhahv.note.screen.loadpicture.imagepicker

import android.content.Context
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.loadpicture.model.Folder
import com.nhahv.note.screen.previewpicture.PreviewPictureActivity

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class ImagePickerViewModel(activity: ImagePickerActivity,
        folder: Folder?) : ImagePickerContract.ViewModel(
        activity) {

    private val mContext: Context = activity.applicationContext
    private val mFolder: Folder? = folder
    private var mPresenter: ImagePickerContract.Presenter? = null

    @get: Bindable
    var mTitle: String? = mContext.getString(R.string.title_albums)
        set(value) {
            field = value
            notifyPropertyChanged(BR.mTitle)
        }

    @get: Bindable
    var mAdapter: ImagePickerAdapter? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mAdapter)
        }

    @get: Bindable
    var mNumberImage: Int? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mNumberImage)
        }

    init {
        mTitle = mFolder?.name
        mAdapter = ImagePickerAdapter(this, mFolder?.images)
    }

    override fun onStart() {
        mPresenter?.onStart()
    }

    override fun onStop() {
        mPresenter?.onStop()
    }

    override fun setPresenter(presenter: ImagePickerContract.Presenter) {
        mPresenter = presenter
    }

    fun onImagePicker(position: Int) {
        mActivity.startActivity(
                mFolder?.images?.let { PreviewPictureActivity.newIntent(mContext, it, position) })
    }

    fun onDonePickImage() {}
}
