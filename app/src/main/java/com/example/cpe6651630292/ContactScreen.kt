package com.cpe4231.cpe6651630292

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.IOException

// ... (ใช้ฟังก์ชัน readAssetFile ที่สร้างไว้ด้านบน)

@Composable
fun ContactScreen(navController: NavController) {
    // 1. รับ Context จาก Composable
    val context = LocalContext.current

    // 2. ใช้ remember เพื่ออ่านไฟล์เพียงครั้งเดียวเมื่อ Composable ถูกเรียกใช้ครั้งแรก
    // (ถ้าไฟล์มีขนาดใหญ่ ควรใช้ ViewModel และ Coroutines เพื่อจัดการ)
    val fileContent = remember {
        // กำหนดชื่อไฟล์ .txt ของคุณ
        readAssetFile(context, "contact.txt")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                // ทำให้สามารถเลื่อนดูได้ถ้าข้อความยาว
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "File Content:",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // 3. แสดงข้อมูลที่อ่านได้จากไฟล์
            Text(
                text = fileContent,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun readAssetFile(context: Context, fileName: String): String {
    return try {
        // ใช้ bufferedReader().use { it.readText() } เพื่ออ่านเนื้อหาทั้งหมดอย่างมีประสิทธิภาพ
        context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        "Error reading file: $fileName"
    }
}