package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText // 建議將 TextView 改為 EditText 更精確
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step6：定義元件變數
        // [微調]: 這裡建議將 TextView 改為 EditText，因為 XML 中它是 EditText
        val edDrink = findViewById<EditText>(R.id.edDrink)
        val rgSugar = findViewById<RadioGroup>(R.id.rgSugar)
        val rgIce = findViewById<RadioGroup>(R.id.rgIce)
        val btnSend = findViewById<Button>(R.id.btnSend)
        
        // Step7：設定 btnSend 的點擊事件
        btnSend.setOnClickListener {
            // Step8：檢查輸入
            if (edDrink.text.isEmpty()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
            } else {
                // Step9：宣告 Bundle
                // [程式碼優化心得]: 使用 bundleOf 擴充函式 (KTX)
                // Java: 需要 Bundle b = new Bundle(); b.putString("key", val); 分多行撰寫。
                // Kotlin: 使用 bundleOf("key" to value) 一行搞定，語法極度簡潔，接近自然語言。
                val b = bundleOf(
                    "drink" to edDrink.text.toString(),
                    "sugar" to rgSugar.findViewById<RadioButton>(
                        rgSugar.checkedRadioButtonId
                    ).text.toString(),
                    "ice" to rgIce.findViewById<RadioButton>(
                        rgIce.checkedRadioButtonId
                    ).text.toString()
                )
                
                // Step10：宣告 Intent 並放入 Bundle
                val i = Intent().putExtras(b)
                
                // Step11：設定結果並結束
                setResult(RESULT_OK, i)
                finish()
            }
        }
    }
}
