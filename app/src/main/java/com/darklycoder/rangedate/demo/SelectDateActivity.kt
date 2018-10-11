package com.darklycoder.rangedate.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.darklycoder.rangedate.listener.OnFinishSelectListener
import com.darklycoder.rangedate.model.SelectDateInfo
import com.darklycoder.rangedate.model.SelectDateType
import kotlinx.android.synthetic.main.activity_select_date.*

class SelectDateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)

        val selectType = SelectDateType.findType(intent?.getIntExtra("type", 0))
        val selectInfo = intent?.getParcelableExtra<SelectDateInfo>("info")

        view_range_date.initDate(selectType, selectInfo)

        initEvents()
    }

    private fun initEvents() {
        view_range_date.setOnFinishSelectListener(object : OnFinishSelectListener {
            override fun onFinishSelect(info: SelectDateInfo?) {
                val intent = Intent()
                intent.putExtra("data", info)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }

    override fun onDestroy() {
        view_range_date?.onDestroy()
        super.onDestroy()
    }

}
