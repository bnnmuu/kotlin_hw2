package com.example.lab5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// [優化程式碼心得]: 主建構子 (Primary Constructor)
// Java: 需要寫 class ViewPagerAdapter { public ViewPagerAdapter(Args...) { ... } } 繁瑣的建構子。
// Kotlin: 直接在類別名稱旁 (fm, lifecycle) 宣告參數，自動成為類別屬性，大幅減少 boilerplate code。
class ViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    // [優化程式碼心得]: 單一表達式函式 (Single-Expression Function)
    // 當函式只有一行回傳值，可以用 = 直接連接，不用寫大括號與 return。
    override fun getItemCount(): Int = 3

    // 根據 position 位置回傳對應的 Fragment
    override fun createFragment(position: Int): Fragment {
        // [Java 差異]: When 表達式
        // Java: 使用 switch (position) { case 0: ... break; } 容易漏寫 break 且語法冗長。
        // Kotlin: 使用 when，結構清晰，且它是表達式(Expression)，可以直接 return 結果。
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> ThirdFragment()
        }
    }
}
