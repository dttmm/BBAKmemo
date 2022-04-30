package com.dttmm.bbakmemo.dto

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dttmm.bbakmemo.BR
import com.dttmm.bbakmemo.database.MemoDatabase.Companion.TABLE

@Entity(tableName = "$TABLE")
class MemoDto() : BaseObservable() {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @get:Bindable
    var content: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.content)
        }

    @get:Bindable
    var password = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    constructor(content: String) : this() {
        this.content = content
    }

    constructor(content: String, password: String) : this(content) {
        this.password = password
    }

    constructor(id: Int, content: String, password: String) : this(content, password) {
        this.id = id
    }
}