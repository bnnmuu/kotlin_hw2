package com.example.lab5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// [優化程式碼心得]: 主要建構子 (Primary Constructor)
// Java: 需要在類別內部另外寫 public ViewPagerAdapter(...) { ... } 建構子來接收參數。
// Kotlin: 直接在類別名稱後面定義參數 (fm, lifecycle)，省略了繁瑣的建構子程式碼。
class ViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    // [優化程式碼心得]: 單一表達式函式 (Single-Expression Function)
    // Java: return 3; 需寫完整的方法區塊。
    // Kotlin: 當函式只有一行回傳值時，可以用 = 直接賦值，極度簡潔。
    override fun getItemCount(): Int = 3

    // 根據 position 位置回傳對應的 Fragment
    override fun createFragment(position: Int): Fragment {
        // [Java 差異]: when 表達式
        // Java: 使用 switch (position) { case 0: ... break; } 語法較為冗長且容易漏寫 break。
        // Kotlin: 使用 when 語法，結構清晰，且可以直接作為回傳值 (return when...)。
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> ThirdFragment()
        }
    }
}
