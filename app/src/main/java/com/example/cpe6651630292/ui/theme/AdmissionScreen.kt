package com.example.cpe6651630292.ui.theme

import com.example.cpe6651630292.R
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// 1. กำหนดลิสต์ของรูปภาพ (คุณต้องมี Resource ID เหล่านี้ในโฟลเดอร์ res/drawable)
val imageList = listOf(
    R.drawable.poster_admission2569,   // รูปภาพที่ 1
    R.drawable.poster_admission2569_2  // รูปภาพที่ 2
)

@Composable
fun AdmissionScreen(navController: NavController) {
    // State สำหรับเก็บ Index ของรูปภาพปัจจุบัน
    var currentImageIndex by remember { mutableStateOf(0) }

    // ดึง Resource ID ของรูปภาพปัจจุบัน
    val currentImageId = imageList[currentImageIndex]

    // เมื่อคลิก ให้เปลี่ยนไปที่รูปภาพถัดไป (วนกลับไปที่ 0 เมื่อถึงรูปสุดท้าย)
    val onImageClick = {
        currentImageIndex = (currentImageIndex + 1) % imageList.size
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onImageClick() }, // คลิกที่ Box เพื่อเปลี่ยนรูปภาพ
        contentAlignment = Alignment.Center
    ) {
        // 2. ใช้ Crossfade Composable
        Crossfade(
            targetState = currentImageId, // State ที่ต้องการให้ Crossfade แอนิเมชันตาม
            animationSpec = tween(durationMillis = 1000), // แอนิเมชันใช้เวลา 1 วินาที
            label = "ImageCrossfade"
        ) { imageId ->
            // 3. เนื้อหา Composable ที่ถูกแอนิเมชัน
            // 'imageId' ในที่นี้คือค่า targetState (Resource ID) ที่ Crossfade ส่งมาให้
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Image $imageId",
                modifier = Modifier
                    .size(600.dp)
                    .padding(16.dp)
                // contentScale = ContentScale.Crop // อาจเพิ่มเพื่อจัดการการปรับขนาด
            )
        }

        // แสดงข้อความบอกวิธีใช้งาน
        Text(
            text = "คลิกที่รูปภาพ เพื่อเปลี่ยนรูป",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}
