package com.example.cpe6651630292

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpe4231.cpe6651630292.ContactScreen
import com.example.cpe6651630292.ui.theme.AdmissionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

// --- ส่วนจัดการ Navigation ---
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // เปลี่ยน startDestination เป็น "splash"
    NavHost(navController = navController, startDestination = "splash") {
        // 1. หน้าจอ splash
        composable("splash") { SplashScreen(navController) }
        // 2. หน้าจอ mainmenu
        composable("mainmenu") { MainMenuScreen(navController) }

        // --- ส่วนที่เพิ่มสำหรับเมนูต่างๆ (Placeholder) ---
        // 3. หน้าจอ admission (ประกาศรับสมัคร)
        composable("admission") { AdmissionScreen(navController) }
        // 4. หน้าจอ curriculum (แนะนำหลักสูตร)
        composable("curriculum") { CurriculumScreen(navController) }
        // 5. หน้าจอ guidance (แนะนำการเรียน)
        composable("guidance") { GuidanceScreen(navController) }
        // 6. หน้าจอ apply_online (สมัครเรียนออนไลน์)
        composable("apply_online") { ApplyOnlineScreen(navController) }
        // 7. หน้าจอ map (แผนที่ตั้งคณะ)
        composable("map") { MapScreen(navController) }
        // 8. หน้าจอ contact (ติดต่อคณะ)
        composable("contact") { ContactScreen(navController) }

        // 9. หน้าจอ career_path (แนะนำวิชาชีพด้านวิศวกรรม) - สำหรับส่งงาน
        composable("career_path") { CareerPathScreen(navController) }
    }
}
