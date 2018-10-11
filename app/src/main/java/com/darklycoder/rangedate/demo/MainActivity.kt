package com.darklycoder.rangedate.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.darklycoder.rangedate.RangeDateUtils
import com.darklycoder.rangedate.model.RoomType
import com.darklycoder.rangedate.model.SelectDateInfo
import com.darklycoder.rangedate.model.SelectDateType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val codeRequestDate = 666
    private var mSelectDateType: SelectDateType = SelectDateType.TYPE_NORMAL
    private var mSelectDateInfo: SelectDateInfo = RangeDateUtils.getDefaultSelectDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateUI()
        initEvents()
    }

    private fun initEvents() {
        btn_select.setOnClickListener {

            val intent = Intent(this, SelectDateActivity::class.java)
            intent.putExtra("type", mSelectDateType.type)
            intent.putExtra("info", mSelectDateInfo)
            startActivityForResult(intent, codeRequestDate)
        }

        rg_mode.setOnCheckedChangeListener { _, _ ->
            updateUI()
        }

        sw_delay.setOnCheckedChangeListener { _, _ ->
            updateUI()
        }
    }

    private fun updateUI() {
        val isNormal = rg_mode.checkedRadioButtonId == R.id.rb_normal
        sw_delay.isEnabled = isNormal
        val isDelay = isNormal && sw_delay.isChecked

        if (isNormal) {
            mSelectDateInfo.type = RoomType.TYPE_ROOM_NORMAL.type

            mSelectDateType = if (isDelay) {
                SelectDateType.TYPE_DELAY

            } else {
                SelectDateType.TYPE_NORMAL
            }

        } else {
            mSelectDateType = SelectDateType.TYPE_HOUR
            mSelectDateInfo.type = RoomType.TYPE_ROOM_HOUR.type
        }

        if (mSelectDateInfo.type == RoomType.TYPE_ROOM_HOUR.type) {
            tv_content?.text = "钟点房\n${RangeDateUtils.getMD(mSelectDateInfo.hourDate)}"

        } else {
            tv_content?.text = "普通房\n${RangeDateUtils.getMD(mSelectDateInfo.startDate)}-${RangeDateUtils.getMD(mSelectDateInfo.endDate)}-${mSelectDateInfo.count}天"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == codeRequestDate && resultCode == Activity.RESULT_OK) {
            val info = data?.getParcelableExtra<SelectDateInfo>("data")

            if (null != info) {
                mSelectDateInfo = info
                updateUI()
            }
        }
    }

}
