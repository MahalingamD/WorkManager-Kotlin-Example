package com.example.simplework.model

class User() {

    var mUserName = ""
    var mPassword = ""
    var mStatus = true

    constructor(s: String, s1: String, b: Boolean) : this() {
        mUserName = s
        mPassword = s1
        mStatus = b
    }

}