package com.darklycoder.rangedate.listener

import android.view.View
import com.darklycoder.rangedate.model.DayInfo

interface OnClickDayListener {

    fun onClickDay(view: View, day: DayInfo)

}