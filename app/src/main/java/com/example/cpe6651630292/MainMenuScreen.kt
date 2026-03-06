package com.example.cpe6651630292

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star // เพิ่มไอคอนรูปดาวสำหรับเมนูใหม่
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- 2. หน้า Main Menu Screen ---
@Composable
fun MainMenuScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // 1. ภาพพื้นหลัง
        Image(
            painter = painterResource(id = R.drawable.bg_engineer),
            contentDescription = "Engineer Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Overlay สีเข้มแบบ Gradient เพื่อให้ปุ่มเด่นขึ้น
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.2f),
                            Color.Black.copy(alpha = 0.7f),
                            Color.Black.copy(alpha = 0.9f)
                        )
                    )
                )
        )

        // 3. เนื้อหาเมนู
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 40.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // จัดกึ่งกลางแนวตั้ง
        ) {
            // ส่วนหัวข้อ (Header)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Text(
                    text = "คณะวิศวกรรมศาสตร์",
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Faculty of Engineering",
                    color = Color(0xFFBBDEFB), // สีฟ้าอ่อนให้ดู Tech
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 2.sp
                )
            }

            // ปุ่มเมนูต่างๆ (ส่งไอคอนเข้าไปด้วย)
            ModernMenuButton(
                text = "ประกาศรับสมัคร",
                icon = Icons.Filled.Menu,
                onClick = { navController.navigate("admission") }
            )
            ModernMenuButton(
                text = "สาขาวิชาที่เปิดสอน",
                icon = Icons.Filled.DateRange,
                onClick = { navController.navigate("curriculum") }
            )
            ModernMenuButton(
                text = "แนะนำการเรียน",
                icon = Icons.Filled.Face,
                onClick = { navController.navigate("guidance") }
            )
            ModernMenuButton(
                text = "สมัครเรียนออนไลน์",
                icon = Icons.Filled.Edit,
                onClick = { navController.navigate("apply_online") }
            )
            ModernMenuButton(
                text = "แผนที่ตั้งคณะ",
                icon = Icons.Filled.LocationOn,
                onClick = { navController.navigate("map") }
            )
            ModernMenuButton(
                text = "ติดต่อคณะ",
                icon = Icons.Filled.Phone,
                onClick = { navController.navigate("contact") }
            )

            // --- ส่วนที่เพิ่มเข้ามาสำหรับการส่งงานที่ 15.2 (ข้อ 4) ---
            ModernMenuButton(
                text = "แนะนำวิชาชีพด้านวิศวกรรม",
                icon = Icons.Filled.Star, // ใช้รูปดาว
                onClick = { navController.navigate("career_path") }
            )
        }
    }
}

// --- ส่วนประกอบปุ่มแบบ Modern (ดีไซน์ใหม่) ---
@Composable
fun ModernMenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(60.dp), // เพิ่มความสูงให้ดูโปร่ง
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.75f), // ขาวเกือบทึบ
            contentColor = Color(0xFF1E3A8A) // สีน้ำเงินเข้ม (Engineering Blue)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        shape = RoundedCornerShape(16.dp) // มุมโค้งมนทันสมัย
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // ส่วนซ้าย: ไอคอน + ข้อความ
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF0D47A1) // สีไอคอนเข้มกว่าตัวหนังสือเล็กน้อย
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333)
                )
            }
            // ส่วนขวา: ลูกศรชี้ไป
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}