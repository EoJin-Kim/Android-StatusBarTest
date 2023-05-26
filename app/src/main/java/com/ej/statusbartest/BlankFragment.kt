package com.ej.statusbartest

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ej.statusbartest.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    lateinit var binding : FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_blank,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            BlankFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 색상을 변경하려는 RGB 값을 설정합니다.
            val statusBarColor = resources.getColor(R.color.black)
            // 상태 표시 줄 색상을 변경합니다.
            window?.statusBarColor = statusBarColor
        } else {
            // Window의 플래그를 변경하여 상태 표시 줄을 투명하게 만듭니다.
            window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // 상태 표시 줄을 흉내낼 커스텀 뷰를 생성합니다.
            val statusBarView = View(activity)
            statusBarView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
            statusBarView.setBackgroundColor(Color.parseColor("#FF0000")) // 예시로 빨간색을 사용합니다.
            // 상태 표시 줄 위에 커스텀 뷰를 추가합니다.
            val contentFrameLayout = view?.findViewById<ViewGroup>(android.R.id.content)
            contentFrameLayout?.addView(statusBarView)
        }
    }

    // 상태 표시 줄의 높이를 가져오는 함수
    private fun getStatusBarHeight(): Int {
        val resources = resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}