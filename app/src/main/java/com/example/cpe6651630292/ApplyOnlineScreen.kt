package com.example.cpe6651630292

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.example.cpe6651630292.applyonline.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyOnlineScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var idCard by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobilephone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    var message by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance("https://web-application-45433-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("users")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("สมัครเรียนออนไลน์", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0D47A1),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text("กรุณากรอกข้อมูลเพื่อลงทะเบียน หรือค้นหาเลขบัตรเพื่อแก้ไข", color = Color.Gray, fontSize = 14.sp)

            // ------------------------------------------------------------------
            // ไฮไลท์: ช่องรหัสบัตรประชาชน พร้อมปุ่มค้นหา (Search)
            // ------------------------------------------------------------------
            OutlinedTextField(
                value = idCard,
                onValueChange = { idCard = it },
                label = { Text("รหัสบัตรประจำตัวประชาชน 13 หลัก") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = { // ใส่ปุ่มแว่นขยายไว้ท้ายช่องกรอกข้อมูล
                    IconButton(onClick = {
                        if (idCard.isNotBlank()) {
                            message = "กำลังค้นหาข้อมูล..."
                            // คำสั่งดึงข้อมูลจาก Firebase ตาม ID Card
                            myRef.child(idCard).get().addOnSuccessListener { snapshot ->
                                if (snapshot.exists()) {
                                    // ถ้าเจอข้อมูล ให้ดึงมาใส่ตัวแปร State เพื่อโชว์บนหน้าจอ
                                    val user = snapshot.getValue(UserData::class.java)
                                    if (user != null) {
                                        title = user.title
                                        firstname = user.firstname
                                        lastname = user.lastname
                                        dateOfBirth = user.dateOfBirth
                                        age = user.age
                                        sex = user.sex
                                        email = user.email
                                        mobilephone = user.mobilephone
                                        address = user.address
                                        subject = user.subject
                                        message = "🔍 พบข้อมูลของคุณแล้ว! สามารถแก้ไขได้เลย"
                                    }
                                } else {
                                    message = "❌ ไม่พบข้อมูลรหัสบัตรประชาชนนี้ในระบบ"
                                }
                            }.addOnFailureListener {
                                message = "❌ ค้นหาล้มเหลว กรุณาลองใหม่"
                            }
                        } else {
                            message = "กรุณากรอกรหัสบัตรประชาชนก่อนกดค้นหา"
                        }
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFF0D47A1))
                    }
                }
            )
            // ------------------------------------------------------------------

            OutlinedTextField(
                value = title, onValueChange = { title = it },
                label = { Text("คำนำหน้านาม (Title)") }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = firstname, onValueChange = { firstname = it },
                label = { Text("ชื่อ (Firstname)") }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastname, onValueChange = { lastname = it },
                label = { Text("นามสกุล (Lastname)") }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = dateOfBirth, onValueChange = { dateOfBirth = it },
                label = { Text("วันเดือนปีเกิด (DD/MM/YYYY)") }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = age, onValueChange = { age = it },
                label = { Text("อายุ (Age)") }, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = sex, onValueChange = { sex = it },
                label = { Text("เพศ (Sex)") }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text("อีเมล (Email)") }, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = mobilephone, onValueChange = { mobilephone = it },
                label = { Text("เบอร์โทรศัพท์ (Mobilephone Number)") }, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            OutlinedTextField(
                value = address, onValueChange = { address = it },
                label = { Text("ที่อยู่ (Address)") }, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = subject, onValueChange = { subject = it },
                label = { Text("สาขาวิชาที่สนใจ (Subject)") }, modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // ปุ่มที่ 1: ลงทะเบียน (สีน้ำเงิน)
                Button(
                    onClick = {
                        if (idCard.isNotBlank() && firstname.isNotBlank() && lastname.isNotBlank()) {
                            message = "กำลังบันทึกข้อมูล..."
                            val newUser = UserData(title, idCard, firstname, lastname, dateOfBirth, age, sex, email, mobilephone, address, subject)

                            myRef.child(idCard).setValue(newUser)
                                .addOnSuccessListener { message = "✅ ลงทะเบียนเข้าศึกษาสำเร็จ!" }
                                .addOnFailureListener { e -> message = "❌ เกิดข้อผิดพลาด: ${e.message}" }
                        } else {
                            message = "กรุณากรอก รหัสบัตรประชาชน, ชื่อ และนามสกุล ให้ครบถ้วน"
                        }
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
                ) {
                    Text("ลงทะเบียนใหม่", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                // ปุ่มที่ 2: บันทึกการแก้ไข (สีส้ม)
                Button(
                    onClick = {
                        if (idCard.isNotBlank() && firstname.isNotBlank() && lastname.isNotBlank()) {
                            message = "กำลังอัปเดตข้อมูล..."
                            val newUser = UserData(title, idCard, firstname, lastname, dateOfBirth, age, sex, email, mobilephone, address, subject)

                            myRef.child(idCard).setValue(newUser)
                                .addOnSuccessListener { message = "✏️ อัปเดตข้อมูลสำเร็จ!" }
                                .addOnFailureListener { e -> message = "❌ เกิดข้อผิดพลาด: ${e.message}" }
                        } else {
                            message = "กรุณากรอกรหัสบัตรประชาชน และกดค้นหาก่อนแก้ไข"
                        }
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00))
                ) {
                    Text("บันทึกการแก้ไข", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            // แสดงข้อความแจ้งเตือนสถานะ
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = if (message.contains("สำเร็จ") || message.contains("พบข้อมูล")) Color(0xFF388E3C) else Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}