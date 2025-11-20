package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // [程式碼優化心得]:
    // 使用 ActivityResultLauncher 取代舊式 Java 的 onActivityResult。
    // 優點：將「啟動」與「結果處理」邏輯綁定在一起，程式碼更內聚、易讀，且不需要檢查 RequestCode。
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        // Step12：判斷回傳結果是否為 RESULT_OK
        if (result.resultCode == Activity.RESULT_OK) {
            // Step13：取得回傳的 Intent
            val intent = result.data
            
            // [Java 語法差異]: Null Safety (空值安全)
            // Java: 需要層層判斷 if (intent != null) 以避免 NullPointerException。
            // Kotlin: 使用 ? (安全呼叫運算子)，若 intent 為 null 則後面程式碼不會執行，避免閃退。
            val drink = intent?.getStringExtra("drink")
            val sugar = intent?.getStringExtra("sugar")
            val ice = intent?.getStringExtra("ice")
            
            // Step14：設定 tvMeal 的文字
            val tvMeal = findViewById<TextView>(R.id.tvMeal)
            
            // [Java 語法差異]: 字串模板 (String Templates)
            // Java: tvMeal.setText("飲料：" + drink + "\n\n甜度：" + sugar ...); 寫法冗長且容易出錯。
            // Kotlin: 使用 $變數名稱 直接嵌入字串中，大幅提升程式碼可讀性與撰寫效率。
            tvMeal.text = "飲料：$drink\n\n甜度：$sugar\n\n冰塊：$ice"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step2：定義元件變數
        // [Java 語法差異]: 型別推斷
        // Java: Button btnChoice = findViewById(R.id.btnChoice); 需重複宣告型別。
        // Kotlin: 使用 val 宣告唯讀變數，編譯器會自動推斷型別。
        val btnChoice = findViewById<Button>(R.id.btnChoice)
        
        // Step3：設定 btnChoice 的點擊事件
        // [Java 語法差異]: Lambda 表達式
        // Java: 需 new View.OnClickListener() { @Override onClick... } 寫一大串匿名類別。
        // Kotlin: 直接使用 { ... } 區塊，簡潔明瞭。
        btnChoice.setOnClickListener {
            // Step4：宣告 Intent
            // [Java 語法差異]: 類別引用
            // Java: SecActivity.class
            // Kotlin: SecActivity::class.java
            val intent = Intent(this, SecActivity::class.java)
            
            // Step5：啟動 Intent
            startForResult.launch(intent)
        }
    }
}
