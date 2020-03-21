package com.example.android_db.Data

data class StudentsEntity(
    var id:Int,
    var name:String,
    var lastName:String,
    var gender:Int,
    var birthDay:String
){
    constructor(): this(0,"","",0,"")
}