package com.example.cpe6651630292

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

// 1. นำ Resource ID ของรูปโปสเตอร์ทั้ง 5 สาขาของคุณมาใส่ที่นี่
val curriculumImages = listOf(
    R.drawable.poster_civil,       // 1. โยธา
    R.drawable.poster_industrial,  // 2. อุตสาหการ
    R.drawable.poster_environ,     // 3. สิ่งแวดล้อม
    R.drawable.poster_computer,    // 4. คอมพิวเตอร์
    R.drawable.poster_energy       // 5. พลังงาน
)

@Composable
fun CurriculumScreen(navController: NavController) {
    // State สำหรับเก็บ Index ของรูปภาพปัจจุบัน
    var currentImageIndex by remember { mutableStateOf(0) }
    val currentImageId = curriculumImages[currentImageIndex]

    // คำสั่งเปลี่ยนรูปเมื่อถูกคลิก
    val onImageClick = {
        currentImageIndex = (currentImageIndex + 1) % curriculumImages.size
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onImageClick() }, // กดที่หน้าจอเพื่อเปลี่ยนรูป
        contentAlignment = Alignment.Center
    ) {
        // แอนิเมชันตอนเปลี่ยนรูปภาพ
        Crossfade(
            targetState = currentImageId,
            animationSpec = tween(durationMillis = 1000), // เฟด 1 วินาที
            label = "CurriculumCrossfade"
        ) { imageId ->
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Curriculum Poster",
                modifier = Modifier
                    .size(600.dp)
                    .padding(16.dp)
            )
        }

        // ข้อความแนะแนวการใช้งาน
        Text(
            text = "คลิกที่รูปภาพ เพื่อเปลี่ยนสาขา",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}