package com.example.cpe6651630292 // เปลี่ยนให้ตรงกับโปรเจกต์ของคุณ

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController) {
    // ตัวแปรสำหรับเก็บค่าการซูม (scale) และการเลื่อน (offset)
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("แผนที่ตั้งคณะ") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "กลับ")
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE0E0E0))
                // เพิ่ม Pointer Input เพื่อรับจับการแตะนิ้วและถ่างนิ้ว
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        // จำกัดการซูมไม่ให้เล็กกว่า 1 เท่า (ปกติ) และไม่ให้ใหญ่เกิน 5 เท่า
                        scale = (scale * zoom).coerceIn(1f, 5f)

                        // ถ้าระดับการซูมเป็น 1 เท่า (ไม่ได้ซูม) ให้รูปกลับมาอยู่ตรงกลาง
                        val newOffset = if (scale == 1f) Offset.Zero else offset + pan
                        offset = newOffset
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.map_engineer), // เปลี่ยนชื่อไฟล์ให้ตรงกับของคุณ
                contentDescription = "Map of Faculty of Engineering",
                modifier = Modifier
                    .fillMaxSize()
                    // นำค่าซูมและการเลื่อนมาแสดงผลกราฟิกที่นี่
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    ),
                contentScale = ContentScale.Fit // ใช้ Fit เพื่อให้เห็นครบทั้งรูปในตอนเริ่มต้น
            )
        }
    }
}