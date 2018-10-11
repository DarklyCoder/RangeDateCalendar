package com.darklycoder.rangedate.model

data class MonthInfo(

        var year: Int = 0, //该月份 属于哪一年
        var month: Int = 0, // 该月 是哪一个月份
        var dayList: ArrayList<DayInfo> = arrayListOf() //天

)