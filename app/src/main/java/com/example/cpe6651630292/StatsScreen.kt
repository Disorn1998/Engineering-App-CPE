package com.example.cpe6651630292 // เช็คชื่อแพ็กเกจด้วยนะครับ

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(navController: NavController) {
    var animationPlayed by remember { mutableStateOf(false) }

    // หน่วงเวลาเล็กน้อยก่อนเล่นแอนิเมชันให้ดูมีมิติ
    LaunchedEffect(key1 = true) {
        delay(150)
        animationPlayed = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard สถิติบัณฑิต", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F172A), // เปลี่ยนเป็นสีกรมท่าเข้ม ดู Pro
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F5F9)) // พื้นหลังสไตล์ Dashboard
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- ส่วนที่ 1: Quick Summary Cards (เพิ่มใหม่ ดูเป็นมืออาชีพสุดๆ) ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryCard(Modifier.weight(1f), "บริษัทพาร์ทเนอร์", "150+", Icons.Filled.Business, Color(0xFF3B82F6))
                SummaryCard(Modifier.weight(1f), "ระยะเวลาได้งาน", "1-3 ด.", Icons.Filled.Timer, Color(0xFF10B981))
                SummaryCard(Modifier.weight(1f), "เรียนต่อ ป.โท", "12%", Icons.Filled.School, Color(0xFFF59E0B))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- ส่วนที่ 2: กราฟโดนัทไล่สี (Gradient Donut Chart) ---
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("อัตราการมีงานทำตรงสาย", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E293B))
                    Text("เทียบจากจำนวนผู้สำเร็จการศึกษาทั้งหมด", fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 24.dp))

                    AnimatedGradientDonutChart(percentage = 0.95f, isPlayed = animationPlayed)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- ส่วนที่ 3: กราฟแท่งมีเส้น Grid หลังกราฟ และแอนิเมชันหน่วงเวลา ---
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                ) {
                    Text("ฐานเงินเดือนเฉลี่ย (THB)", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E293B))
                    Text("แยกตามสาขาวิชาชีพวิศวกรรม", fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 24.dp))

                    AnimatedProBarChart(isPlayed = animationPlayed)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// -------------------------------------------------------------
// Component ใหม่: การ์ดสรุปข้อมูลด้านบน
// -------------------------------------------------------------
@Composable
fun SummaryCard(modifier: Modifier, title: String, value: String, icon: ImageVector, color: Color) {
    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            Column {
                Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color(0xFF1E293B))
                Text(text = title, fontSize = 11.sp, color = Color.Gray, maxLines = 1)
            }
        }
    }
}

// -------------------------------------------------------------
// Component: กราฟโดนัทไล่สีขั้นเทพ (Sweep Gradient)
// -------------------------------------------------------------
@Composable
fun AnimatedGradientDonutChart(percentage: Float, isPlayed: Boolean) {
    val animateFloat by animateFloatAsState(
        targetValue = if (isPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
        label = "DonutChart"
    )

    // สร้างสีไล่ระดับสำหรับกราฟวงกลม
    val gradientColors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(180.dp)) {
        Canvas(modifier = Modifier.size(180.dp)) {
            // วงแหวนพื้นหลัง
            drawArc(
                color = Color(0xFFF1F5F9),
                startAngle = 0f, sweepAngle = 360f, useCenter = false,
                style = Stroke(width = 45f, cap = StrokeCap.Round)
            )
            // วงแหวนไล่สี
            drawArc(
                brush = Brush.sweepGradient(gradientColors),
                startAngle = -90f, sweepAngle = 360 * animateFloat, useCenter = false,
                style = Stroke(width = 45f, cap = StrokeCap.Round)
            )
        }

        // ตัวเลข % วิ่งตามแอนิเมชัน
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${(animateFloat * 100).toInt()}%",
                fontSize = 40.sp, fontWeight = FontWeight.Black, color = Color(0xFF0072FF)
            )
            Text("Employed", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
        }
    }
}

// -------------------------------------------------------------
// Component: กราฟแท่งขั้นเทพ มี Grid ประ + แอนิเมชันเด้งทีละแท่ง
// -------------------------------------------------------------
@Composable
fun AnimatedProBarChart(isPlayed: Boolean) {
    val barData = listOf(
        Pair("คอม", 28000f),
        Pair("โยธา", 24000f),
        Pair("อุตสาหกรรม", 22000f),
        Pair("สิ่งแวดล้อม", 20000f),
        Pair("พลังงาน", 26000f)
    )
    val maxSalary = 30000f

    // สร้างสีไล่ระดับสำหรับกราฟแท่ง
    val barGradient = Brush.verticalGradient(listOf(Color(0xFF60A5FA), Color(0xFF2563EB)))

    Box(modifier = Modifier.fillMaxWidth().height(220.dp)) {
        // วาดเส้นตาราง (Grid) แบบจุดไข่ปลาเป็นพื้นหลัง
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            val lines = 4
            for (i in 0..lines) {
                val y = canvasHeight * (i.toFloat() / lines)
                drawLine(
                    color = Color(0xFFE2E8F0),
                    start = Offset(0f, y),
                    end = Offset(canvasWidth, y),
                    strokeWidth = 2f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f) // สร้างเส้นประ
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            barData.forEachIndexed { index, (branch, salary) ->
                val barHeightPercentage = salary / maxSalary

                // ความลับระดับโปร: ให้กราฟแต่ละแท่งหน่วงเวลาขึ้นไม่พร้อมกัน (index * 200)
                val animateProgress by animateFloatAsState(
                    targetValue = if (isPlayed) 1f else 0f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        delayMillis = index * 200, // หน่วงเวลาตามลำดับแท่ง
                        easing = FastOutSlowInEasing
                    ),
                    label = "BarChart_$index"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    // ตัวเลขเงินเดือนโผล่ตามกราฟ
                    Text(
                        text = "${(salary * animateProgress / 1000).toInt()}k",
                        fontSize = 12.sp, color = Color(0xFF1E293B), fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    // วาดแท่งกราฟไล่สี
                    Canvas(
                        modifier = Modifier
                            .width(36.dp)
                            .height(160.dp * barHeightPercentage * animateProgress)
                    ) {
                        drawRoundRect(
                            brush = barGradient, // ใส่ Gradient
                            size = Size(size.width, size.height),
                            cornerRadius = CornerRadius(16f, 16f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = branch, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }
        }
    }
}