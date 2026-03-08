package com.example.cpe6651630292.applyonline // เช็ค package ให้ตรงกับของคุณนะครับ

data class UserData(
    val title: String = "",
    val idCard: String = "", // เพิ่มรหัสบัตรประชาชน
    val firstname: String = "",
    val lastname: String = "",
    val dateOfBirth: String = "",
    val age: String = "",
    val sex: String = "",
    val email: String = "",
    val mobilephone: String = "",
    val address: String = "",
    val subject: String = ""
)