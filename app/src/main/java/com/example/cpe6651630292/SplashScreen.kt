package com.example.cpe6651630292

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

// --- 1. หน้า Splash Screen (หมุนรูปภาพ) ---
@Composable
fun SplashScreen(navController: NavController) {
    // ตัวแปรสำหรับเก็บค่าองศาการหมุน (0f -> 360f)
    val rotation = remember { Animatable(0f) }
    // ตัวแปรสำหรับเก็บค่าการขยายขนาด (0f -> 1f) เพื่อความสวยงาม
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        // เริ่ม Animation (หมุนและขยายพร้อมกัน)
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = { OvershootInterpolator(2f).getInterpolation(it) }
            )
        )
        // หมุนต่อเนื่องอีกนิดหน่อย หรือหมุนพร้อมกันก็ได้
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000, // หมุนภายใน 1 วินาที
                easing = LinearEasing
            )
        )
        delay(500) // รออีก 0.5 วินาที ให้คนดูโลโก้แป๊บหนึ่ง

        // ไปหน้า Menu (แก้ไขจาก "splash" เป็น "mainmenu")
        navController.navigate("mainmenu") {
            // ลบหน้า Splash ออกจาก Stack เพื่อไม่ให้กด Back แล้วกลับมาหน้านี้
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ใช้รูปภาพของคุณแทนเฟือง และใส่ scale กับ rotate ให้ครบ
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Animated Logo",
            modifier = Modifier
                .size(400.dp)
                .scale(scale.value)
                .rotate(rotation.value)
        )
    }
}
