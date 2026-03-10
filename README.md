# ⚙️ Engineering Faculty PR App 
*(แอปพลิเคชันประชาสัมพันธ์คณะวิศวกรรมศาสตร์ ม.รามคำแหง)*

<p align="left">
  <a href="https://kotlinlang.org/" target="_blank" title="Kotlin">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/kotlin/kotlin-original.svg" alt="Kotlin" width="45" height="45" style="vertical-align: middle;" />
  </a>&nbsp;&nbsp;&nbsp;&nbsp;
  
  <a href="https://developer.android.com/compose" target="_blank" title="Jetpack Compose" style="vertical-align: middle; font-size: 22px; font-weight: bold; text-decoration: none; color: #4CAF50;">
    Jetpack Compose
  </a>&nbsp;&nbsp;&nbsp;&nbsp;

  <a href="https://firebase.google.com/" target="_blank" title="Firebase">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/firebase/firebase-original.svg" alt="Firebase" width="45" height="45" style="vertical-align: middle;" />
  </a>&nbsp;&nbsp;&nbsp;&nbsp;
  
  <a href="https://developer.android.com/studio" target="_blank" title="Android Studio">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/androidstudio/androidstudio-original.svg" alt="Android Studio" width="45" height="45" style="vertical-align: middle;" />
  </a>
</p>

แอปพลิเคชัน Android ที่ออกแบบและพัฒนาขึ้นเพื่อประชาสัมพันธ์คณะวิศวกรรมศาสตร์ มหาวิทยาลัยรามคำแหง โปรเจกต์นี้แสดงให้เห็นถึงทักษะการพัฒนาแอปพลิเคชัน Android สมัยใหม่ โดยใช้สถาปัตยกรรมการเขียน UI แบบ Declarative และการเชื่อมต่อระบบฐานข้อมูลแบบ Real-time

---

## 🚀 ภาพรวมโปรเจกต์ (Project Overview)
แอปพลิเคชันนี้ทำหน้าที่เป็นคู่มือฉบับสมบูรณ์สำหรับนักศึกษาใหม่และผู้ที่สนใจ โดยรวบรวมข้อมูลหลักสูตรที่เปิดสอน, รายละเอียดการรับสมัคร, เส้นทางสายอาชีพวิศวกรรม และมีระบบสมัครเรียนออนไลน์ที่สามารถใช้งานได้จริง

---

## ✨ ฟีเจอร์เด่น (Key Features)

| หมวดหมู่ (Category) | ฟีเจอร์ (Feature) | รายละเอียด (Description) |
| :--- | :--- | :--- |
| **🎨 UI/UX Design** | **Modern UI/UX** | พัฒนาหน้าจอทั้งหมดด้วย Jetpack Compose ยึดหลัก Material Design 3 โดดเด่นด้วยการ์ดข้อมูลแบบยืดขยายได้ (Expandable Cards) |
| **📊 Data & Analytics** | **Interactive Stats Dashboard 🔥** | แดชบอร์ดแสดงสถิติด้วย `Canvas` แบบ Custom UI พร้อม Staggered Animation เลื่อนกราฟแท่งแบบหน่วงเวลา และ Gradient Brush |
| **💾 Backend System** | **Advanced Online Registration 🔥** | ฟอร์มสมัครเรียนออนไลน์เชื่อมต่อ Firebase พร้อมระบบ "ค้นหาด้วยเลขบัตรประชาชน 13 หลัก" เพื่อดึงข้อมูลเดิมกลับมาแก้ไขอัตโนมัติ |
| **🖼️ Media & Visuals** | **Dynamic Posters & Video** | แอนิเมชัน `Crossfade` สำหรับสไลด์โชว์โปสเตอร์ และฝังวิดีโอโปรโมทด้วยไลบรารี AndroidX Media3 (ExoPlayer) |
| **👆 Interaction** | **Interactive Map & Filtering** | ระบบแผนที่ซูมได้ด้วยสองนิ้ว (Pinch-to-zoom) และระบบคัดกรองสายอาชีพด้วย `LazyRow` และ Filter Chips |

---

## 🛠️ เทคโนโลยีและโครงสร้างระบบ (Tech Stack & Architecture)

| หมวดหมู่ | เทคโนโลยีที่ใช้ | หน้าที่การทำงาน |
| :--- | :--- | :--- |
| **Language** | Kotlin | ภาษาหลักในการพัฒนาแอปพลิเคชัน |
| **UI Toolkit** | Jetpack Compose | สร้างหน้าจอและแอนิเมชันด้วยสถาปัตยกรรม Declarative UI |
| **Backend** | Firebase Realtime Database | จัดการฐานข้อมูลผู้สมัครเรียนแบบ Real-time |
| **Media Player** | AndroidX Media3 (ExoPlayer) | จัดการระบบเล่นสื่อวิดีโอภายในแอปพลิเคชัน |
| **Navigation** | Jetpack Navigation Compose | ควบคุมการเปลี่ยนหน้าจอและส่งผ่านข้อมูล |
| **Architecture** | Single-Activity Architecture | โครงสร้างแอปพลิเคชันแบบ Activity เดียว พร้อมจัดการ State ด้วย `remember` และ `StateFlow` |

---

## 📸 Screenshots
<img width="500" height="1065" alt="Screenshot 2026-03-10 180659" src="https://github.com/user-attachments/assets/a8b3a3ee-e0b5-4cdb-9383-7c1ed7725677" />
<img width="500" height="1065" alt="Screenshot 2026-03-10 180803" src="https://github.com/user-attachments/assets/e8184cf2-f368-4716-ab16-5e01ac5e1d55" />
<img width="500" height="1065" alt="Screenshot 2026-03-06 230115" src="https://github.com/user-attachments/assets/1ffbd3f5-4ec0-456e-b20d-c9d18188aadf" />
<img width="500" height="1065" alt="Screenshot 2026-03-06 230241" src="https://github.com/user-attachments/assets/52b9665c-c9ff-4406-bd6e-1541c63ef880" />
<img width="500" height="1065" alt="Screenshot 2026-03-06 230303" src="https://github.com/user-attachments/assets/571ad95a-2e0b-479b-8435-1982d06810a6" />
<img width="500" height="1065" alt="Screenshot 2026-03-10 180732" src="https://github.com/user-attachments/assets/9e02b16a-5011-406f-a442-02e0e1b67724" />


---

## 💻 การติดตั้งและทดลองใช้งาน (Getting Started)

### วิธีที่ 1: ติดตั้งแอปพลิเคชัน (สำหรับผู้ใช้งานทั่วไป)
สามารถดาวน์โหลดไฟล์ `.apk` เวอร์ชันล่าสุดได้ที่หัวข้อ **[Releases](https://github.com/Disorn1998/Cpe6651630292/releases)** ด้านขวามือของ Repository และติดตั้งลงบนสมาร์ทโฟน Android ได้ทันที

### วิธีที่ 2: รันโค้ดโปรเจกต์ (สำหรับนักพัฒนา)
1. โคลน (Clone) Repository นี้ลงในเครื่องของคุณ:
   ```bash
   git clone [https://github.com/Disorn1998/Cpe6651630292.git](https://github.com/Disorn1998/Cpe6651630292.git)
