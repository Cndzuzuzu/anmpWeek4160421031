package com.zuzudev.advweek4.model

data class Fruit (
    var id:Int?,
    var name:String?,
    var color:String?,
    var origin: Origin,
    var varieties:List<String>?,
    var images:String?,
)
