package com.example.weatherapp.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.weatherapp.databinding.DialogServerErrorBinding

/**
 * How to use
 * ---------
 * 1) Add widget or component in your xml
 * 2) Initialize the component in the Fragment or Activity calling
 * the methods setListener() and setContentData()
 *
 * Note: the samples are in Java (current use)
 */
class ErrorFullScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var errorScreenListener: ErrorScreenListener? = null
    private lateinit var binding: DialogServerErrorBinding

    init {
        init()
    }

    private fun init() {
        binding = DialogServerErrorBinding.inflate(LayoutInflater.from(context), this, true)

        binding.apply {
            btnRetry.setOnClickListener {
                action(false)
            }
            btnSettings.setOnClickListener {
                action(true)
            }
        }
    }

    private fun setTitle(title: String) {
        if (!TextUtils.isEmpty(title)) binding.tvTitle.text = title
    }

    private fun setDescription(description: String) {
        if (!TextUtils.isEmpty(description)) binding.tvDescription.text = description
    }

    private fun setTextRetryButton(text: String) {
        binding.btnRetry.text = text
    }

    private fun setTextCheckConnectionButton(text: String) {
        binding.btnSettings.text = text
    }

    private fun setImageError(drawable: Drawable?) {
        if (drawable != null) binding.ivErrorType.setImageDrawable(drawable)
    }

    private fun configureButtons(errorType: UIErrorContent) {
        binding.apply {
            btnRetry.visible()
            when (errorType) {
                is UIErrorContent.Connection -> {
                    btnSettings.visible()
                    llButtons.visible()
                }
                is UIErrorContent.Generic -> {
                    btnSettings.gone()
                    llButtons.visible()
                }
            }
        }
    }

    private fun action(isConnectionError: Boolean) {
        errorScreenListener?.actionBTFErrorScreen(isConnectionError)
    }

    /**
     * setListener method listener must be implemented on the UI side
     * (Fragment / Activity) to set the particular action in error screen
     */
    fun setListener(listener: ErrorScreenListener?) {
        errorScreenListener = listener
    }

    /**
     * @param errorType is to change the content in title, description and drawable image.
     *
     * Example in: BettercoinFragment.java
     * @sample
     * UIErrorContent typeError;
     * typeError = new UIErrorContent.Connection("title", "description", R.drawable.ic_error_internet_connection);
     * mBTFErrorFullScreen.setContentData(typeError);
     */
    fun setContentData(errorType: UIErrorContent) {
        configureButtons(errorType)
        errorType.drawable?.let { setImageError(it) }
        if (!TextUtils.isEmpty(errorType.title)) setTitle(errorType.title)
        if (!TextUtils.isEmpty(errorType.description)) setDescription(errorType.description)
        if (!errorType.textRetry.isNullOrEmpty()) setTextRetryButton(errorType.textRetry)
        if (!errorType.textCheckConnection.isNullOrEmpty())
            setTextCheckConnectionButton(errorType.textCheckConnection)
    }

    interface ErrorScreenListener {
        fun actionBTFErrorScreen(isConnectionError: Boolean)
    }
}

sealed class UIErrorContent(
    val title: String,
    val description: String,
    val textRetry: String?,
    val textCheckConnection: String?,
    val drawable: Drawable?
) {
    class Connection(
        title: String,
        description: String,
        textRetry: String,
        textCheckConnection: String,
        drawable: Drawable?
    ) :
        UIErrorContent(title, description, textRetry, textCheckConnection, drawable)

    class Generic(
        title: String,
        description: String,
        textRetry: String,
        textCheckConnection: String,
        drawable: Drawable?
    ) :
        UIErrorContent(title, description, textRetry, textCheckConnection, drawable)
}