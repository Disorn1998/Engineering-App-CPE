package com.example.cpe6651630292

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.example.cpe6651630292.applyonline.UserData

@Composable
fun ApplyOnlineScreen(navController: NavController) {
    // 1. ประกาศตัวแปร State สำหรับรับค่าทั้ง 10 ฟิลด์
    var title by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobilephone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    // ตัวแปรสำหรับแสดงข้อความแจ้งเตือนบนหน้าจอ
    var message by remember { mutableStateOf("") }

    // เชื่อมต่อฐานข้อมูล Firebase ของคุณ
    val database = FirebaseDatabase.getInstance("https://web-application-45433-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("users")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // เพิ่ม scroll เพื่อให้เลื่อนหน้าจอได้เวลาคีย์บอร์ดบัง
        verticalArrangement = Arrangement.Center
    ) {
        Text("สมัครเรียนออนไลน์", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // 1) คำนำหน้านาม (Title)
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("คำนำหน้านาม (Title)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 2) ชื่อ (Firstname)
        OutlinedTextField(
            value = firstname,
            onValueChange = { firstname = it },
            label = { Text("ชื่อ (Firstname)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 3) นามสกุล (Lastname)
        OutlinedTextField(
            value = lastname,
            onValueChange = { lastname = it },
            label = { Text("นามสกุล (Lastname)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 4) วันเดือนปีเกิด (Date of Birth)
        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("วันเดือนปีเกิด (DD/MM/YYYY)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 5) อายุ (Age)
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("อายุ (Age)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 6) เพศ (Sex)
        OutlinedTextField(
            value = sex,
            onValueChange = { sex = it },
            label = { Text("เพศ (Sex)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 7) อีเมล (Email)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("อีเมล (Email)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 8) เบอร์โทรศัพท์เคลื่อนที่ (Mobilephone Number)
        OutlinedTextField(
            value = mobilephone,
            onValueChange = { mobilephone = it },
            label = { Text("เบอร์โทรศัพท์ (Mobilephone Number)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 9) ที่อยู่ (Address)
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("ที่อยู่ (Address)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 10) สาขาวิชา (Subject) ที่สนใจเข้าศึกษา
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("สาขาวิชาที่สนใจ (Subject)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // ตรวจสอบข้อมูลเบื้องต้นก่อนบันทึก (ต้องกรอกชื่อ, นามสกุล และอีเมล)
                if (firstname.isNotBlank() && lastname.isNotBlank() && email.contains("@")) {
                    message = "กำลังบันทึกข้อมูล..."

                    // นำข้อมูลทั้ง 10 ฟิลด์ใส่เข้า Data Class
                    val newUser = UserData(
                        title, firstname, lastname, dateOfBirth, age,
                        sex, email, mobilephone, address, subject
                    )

                    // บันทึกลง Firebase
                    myRef.push().setValue(newUser)
                        .addOnSuccessListener {
                            message = "บันทึกข้อมูลสำเร็จ (Registration successful!)"
                            // หากต้องการเคลียร์ข้อมูลฟอร์มหลังจากส่งสำเร็จ สามารถเซ็ตค่าตัวแปรเป็น "" ได้ที่นี่
                        }
                        .addOnFailureListener { e ->
                            message = "เกิดข้อผิดพลาด: ${e.message}"
                        }
                } else {
                    message = "กรุณากรอกชื่อ นามสกุล และอีเมลให้ครบถ้วนและถูกต้อง"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ลงทะเบียน")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(message, color = MaterialTheme.colorScheme.primary)

        // เพิ่มระยะด้านล่างสุดกันการเลื่อนลงไปไม่สุด
        Spacer(modifier = Modifier.height(32.dp))
    }
}