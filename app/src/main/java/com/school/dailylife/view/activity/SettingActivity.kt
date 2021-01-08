package com.school.dailylife.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.school.dailylife.R
import com.school.dailylife.config.CURRNT_USER_EXIST
import com.school.dailylife.util.defaultSharedPrefrence
import com.school.dailylife.util.editor
import com.school.dailylife.view.fragment.dialog.EditMyContactDialog
import com.school.dailylife.viewmodel.SettingViewModel
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {
    override val contentViewId: Int
        get() = R.layout.activity_setting

    private val viewmodel by viewModels<SettingViewModel>()
    private var dialog: DialogFragment? = null


    override fun initView(savedInstanceState: Bundle?) {

        cl_setting_upload_contact_way.setOnClickListener {
            dialog = EditMyContactDialog(viewmodel)
            dialog?.show(
                supportFragmentManager,
                "EditMyContactDialog"
            )
        }

        cl_setting_logout.setOnClickListener{
            defaultSharedPrefrence.editor {
                putBoolean(CURRNT_USER_EXIST, false)
                startActivity(Intent(this@SettingActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }


}