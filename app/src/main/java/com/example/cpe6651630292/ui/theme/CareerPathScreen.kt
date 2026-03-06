package com.example.cpe6651630292 // อย่าลืมแก้ให้ตรงกับโปรเจกต์ของคุณนะครับ

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// เพิ่มตัวแปร marketDemand (ความต้องการตลาด 0.0 - 1.0) เพื่อทำหลอดแอนิเมชัน
data class CareerInfo(
    val id: Int,
    val category: String,
    val branch: String,
    val englishName: String,
    val careers: String,
    val icon: ImageVector,
    val iconTint: Color,
    val marketDemand: Float, // ระดับความต้องการ (เช่น 0.95 คือ 95%)
    val demandText: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareerPathScreen(navController: NavController) {
    // ข้อมูลสาขาพร้อมตัวเลขความต้องการของตลาด
    val careerList = listOf(
        CareerInfo(
            1, "เทคโนโลยี", "วิศวกรรมคอมพิวเตอร์", "(Computer Engineering)",
            "• Software Engineer / Full-Stack Developer\n• DevOps Engineer / Cloud Engineer\n• Network Engineer / System Analyst",
            Icons.Filled.Computer, Color(0xFF1976D2), 0.95f, "สูงมาก (95%)"
        ),
        CareerInfo(
            2, "ก่อสร้าง", "วิศวกรรมโยธา", "(Civil Engineering)",
            "• วิศวกรโครงสร้าง / วิศวกรควบคุมงานก่อสร้าง\n• วิศวกรประเมินราคา / วิศวกรสำรวจ\n• ผู้จัดการโครงการ",
            Icons.Filled.Home, Color(0xFFF57C00), 0.85f, "สูง (85%)"
        ),
        CareerInfo(
            3, "อุตสาหกรรม", "วิศวกรรมอุตสาหการ", "(Industrial Engineering)",
            "• วิศวกรวางแผนการผลิต / วิศวกรควบคุมคุณภาพ\n• วิศวกรปรับปรุงกระบวนการ\n• ผู้จัดการโรงงาน",
            Icons.Filled.Settings, Color(0xFF689F38), 0.80f, "สูง (80%)"
        ),
        CareerInfo(
            4, "สิ่งแวดล้อม", "วิศวกรรมสิ่งแวดล้อม", "(Environmental Engineering)",
            "• วิศวกรสิ่งแวดล้อม / วิศวกรควบคุมระบบบำบัดมลพิษ\n• ผู้ตรวจประเมินด้านสิ่งแวดล้อม\n• ที่ปรึกษาด้านสิ่งแวดล้อม",
            Icons.Filled.Eco, Color(0xFF388E3C), 0.75f, "ปานกลาง-สูง (75%)"
        ),
        CareerInfo(
            5, "พลังงาน", "วิศวกรรมพลังงาน", "(Energy Engineering)",
            "• วิศวกรพลังงาน / วิศวกรออกแบบระบบพลังงานทดแทน\n• ผู้ตรวจสอบการจัดการพลังงาน\n• ที่ปรึกษาด้านการอนุรักษ์พลังงาน",
            Icons.Filled.Bolt, Color(0xFFFBC02D), 0.90f, "เติบโตเร็ว (90%)"
        )
    )

    var selectedCategory by remember { mutableStateOf("ทั้งหมด") }
    val categories = listOf("ทั้งหมด") + careerList.map { it.category }.distinct()

    val filteredCareers = if (selectedCategory == "ทั้งหมด") {
        careerList
    } else {
        careerList.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("แนะนำวิชาชีพด้านวิศวกรรม", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
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
                .background(Color(0xFFF0F4F8))
                .padding(paddingValues)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = category },
                        label = { Text(category, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF0D47A1),
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "สายงานที่รองรับตามสาขาวิชา",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A8A),
                        modifier = Modifier.padding(bottom = 4.dp, top = 4.dp)
                    )
                }

                items(filteredCareers) { career ->
                    ExpandableCareerCard(career, navController)
                }

                item { Spacer(modifier = Modifier.height(32.dp)) }
            }
        }
    }
}

// การ์ดเวอร์ชันใหม่ล่าสุด (ลบแชร์ออก ใส่แอนิเมชันหลอดและปุ่มกดหัวใจแทน)
@Composable
fun ExpandableCareerCard(career: CareerInfo, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    // State สำหรับบันทึกความชอบ
    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(career.iconTint.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = career.icon,
                        contentDescription = career.branch,
                        tint = career.iconTint,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "หมวด: ${career.category}", fontSize = 12.sp, color = career.iconTint, fontWeight = FontWeight.Bold)
                    Text(text = career.branch, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF333333))
                    Text(text = career.englishName, fontSize = 14.sp, color = Color.Gray)
                }

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = Color.Gray
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "สายอาชีพและตำแหน่งงาน:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = career.careers,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // --- ลูกเล่นที่ 1: แถบแอนิเมชันความต้องการของตลาด ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "แนวโน้มตลาดงาน:", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
                    Text(text = career.demandText, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = career.iconTint)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // แอนิเมชันหลอดวิ่ง
                val animatedProgress by animateFloatAsState(
                    targetValue = if (expanded) career.marketDemand else 0f,
                    animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
                    label = "DemandProgress"
                )

                LinearProgressIndicator(
                    progress = animatedProgress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = career.iconTint,
                    trackColor = Color(0xFFE0E0E0)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // --- ลูกเล่นที่ 2: ปุ่มกดถูกใจ (หัวใจ) และ สมัครเรียน ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // จัดให้อยู่ซ้ายสุด-ขวาสุด
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // ปุ่มหัวใจ พร้อมแอนิเมชันเปลี่ยนสี
                    IconToggleButton(
                        checked = isFavorite,
                        onCheckedChange = { isFavorite = it }
                    ) {
                        val tint by animateColorAsState(if (isFavorite) Color(0xFFE91E63) else Color.Gray, label = "FavoriteColor")
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = tint,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Button(
                        onClick = { navController.navigate("apply_online") },
                        modifier = Modifier.height(38.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = "Apply", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("สมัครเรียน", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}