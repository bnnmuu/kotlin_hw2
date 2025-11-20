package com.example.lab5

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

// [Java 差異]: 繼承語法
// Java: public class MainActivity extends AppCompatActivity
// Kotlin: 使用 : AppCompatActivity()，簡潔明瞭。
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
        
        Log.e("MainActivity", "onCreate")
        
        // [Java 差異]: 泛型推斷
        // Java: ViewPager2 vp = (ViewPager2) findViewById(...); 需要強制轉型。
        // Kotlin: findViewById<ViewPager2>(...) 使用泛型，更安全且不需要轉型。
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        
        val adapter = ViewPagerAdapter(supportFragmentManager, this.lifecycle)
        
        // [優化程式碼心得]: 屬性存取 (Property Access Syntax)
        // Java: viewPager2.setAdapter(adapter); 必須呼叫 Setter 方法。
        // Kotlin: viewPager2.adapter = adapter 直接賦值，語法像存取變數一樣自然，背後自動呼叫 setter。
        viewPager2.adapter = adapter
        
        // 同理，取代 setOffscreenPageLimit(1)
        viewPager2.offscreenPageLimit = 1
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "onDestroy")
    }
}
