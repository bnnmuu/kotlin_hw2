package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // 定義元件變數
        val btnToast = findViewById<Button>(R.id.btnToast)
        val btnSnackBar = findViewById<Button>(R.id.btnSnackBar)
        val btnDialog1 = findViewById<Button>(R.id.btnDialog1)
        val btnDialog2 = findViewById<Button>(R.id.btnDialog2)
        val btnDialog3 = findViewById<Button>(R.id.btnDialog3)

        // [Java 差異]: 陣列宣告
        // Java: String[] item = new String[]{"...", ...}; 寫法較繁瑣。
        // Kotlin: 使用 arrayOf(...) 函式直接建立，編譯器自動推斷型別。
        val item = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

        btnToast.setOnClickListener {
            // [優化程式碼心得]: DRY 原則 (Don't Repeat Yourself)
            // 透過封裝 showToast 方法，避免每次都要寫 Toast.makeText(...).show() 這一長串，讓主邏輯更乾淨。
            showToast("預設 Toast")
        }

        btnSnackBar.setOnClickListener {
            // [Java 差異]: 鏈式呼叫與 Lambda
            // Kotlin 允許將 Lambda 放在括號外，讓 Snackbar 的設定 (make -> setAction -> show) 讀起來像是一個流暢的句子。
            Snackbar.make(it, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("按鈕") {
                    showToast("已回應")
                }.show()
        }

        btnDialog1.setOnClickListener {
            // [優化程式碼心得]: Lambda 參數簡化
            // 原始寫法: { dialogInterface, which -> ... }
            // 優化寫法: 當 Lambda 中的參數(如 dialogInterface)沒有被使用時，Kotlin 慣例是用底線 (_) 取代。
            // 這能讓閱讀者一眼就知道「這個參數不重要」，提升程式碼可讀性。
            AlertDialog.Builder(this)
                .setTitle("按鈕式 AlertDialog")
                .setMessage("AlertDialog 內容")
                .setNeutralButton("左按鈕") { _, _ ->
                    showToast("左按鈕")
                }
                .setNegativeButton("中按鈕") { _, _ ->
                    showToast("中按鈕")
                }
                .setPositiveButton("右按鈕") { _, _ ->
                    showToast("右按鈕")
                }
                .show()
        }

        btnDialog2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("列表式 AlertDialog")
                .setItems(item) { _, i ->
                    // [Java 差異]: 字串模板 (String Template)
                    // Java: "你選的是" + item[i]
                    // Kotlin: "你選的是${item[i]}"，直接嵌入變數或運算式，直觀且不易出錯。
                    showToast("你選的是${item[i]}")
                }.show()
        }

        btnDialog3.setOnClickListener {
            var position = 0
            AlertDialog.Builder(this)
                .setTitle("單選式 AlertDialog")
                .setSingleChoiceItems(item, 0) { _, i ->
                    position = i
                }
                .setPositiveButton("確定") { _, _ ->
                    showToast("你選的是${item[position]}")
                }.show()
        }
    }

    // [優化程式碼心得]: 簡化 Toast 顯示
    // 將重複的邏輯抽離成 Private Function，雖然 Java 也能做，
    // 但配合 Kotlin 的單一表達式語法，未來甚至可以寫成 Extension Function (擴充函式) 來全域使用。
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
