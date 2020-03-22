package com.example.shopitee.adapters

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.shopitee.HorizontalProductScrollModel
import com.example.shopitee.R
import com.example.shopitee.models.HomePageModel
import com.example.shopitee.models.SliderModel
import java.util.*

class HomePageAdapter(private val homePageModelList: List<HomePageModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (homePageModelList[position].type) {
            0 -> HomePageModel.BANNER_SLIDER
            1 -> HomePageModel.STRIP_ADD_BANNER
            2 -> HomePageModel.HORIZONTAL_PRODUCT_VIEW
            3 -> HomePageModel.GRID_PRODUCT_VIEW
            else -> -1
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HomePageModel.BANNER_SLIDER -> {
                val bannerSliderView = LayoutInflater.from(viewGroup.context).inflate(R.layout.sliding_ad_layout, viewGroup, false)
                BannerSliderViewholder(bannerSliderView)
            }
            HomePageModel.STRIP_ADD_BANNER -> {
                val stripAddView = LayoutInflater.from(viewGroup.context).inflate(R.layout.strip_ad_layout, viewGroup, false)
                StripAdBannerViewholder(stripAddView)
            }
            HomePageModel.GRID_PRODUCT_VIEW -> {
                val gridProductView = LayoutInflater.from(viewGroup.context).inflate(R.layout.grid_product_layout, viewGroup, false)
                GridProductViewHolder(gridProductView)
            }
            else -> GridProductViewHolder(viewGroup)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (homePageModelList[position].type) {
            HomePageModel.BANNER_SLIDER -> {
                val sliderModelList = homePageModelList[position].sliderModelsList
                (viewHolder as BannerSliderViewholder).setBannersSliderViewpager(sliderModelList)
            }
            HomePageModel.STRIP_ADD_BANNER -> {
                val resource = homePageModelList[position].resource
                val color = homePageModelList[position].backgroundColor
                (viewHolder as StripAdBannerViewholder).setStripAd(resource, color)
            }
            HomePageModel.HORIZONTAL_PRODUCT_VIEW -> {
                val horizontalLayoutTitle = homePageModelList[position].title
                val horizontalProductScrollModelList = homePageModelList[position].horizontalProductScrollModelList
                (viewHolder as HorizonatlProductViewHolder).setHorizontalProductLayout(horizontalProductScrollModelList, horizontalLayoutTitle)
            }
            HomePageModel.GRID_PRODUCT_VIEW -> {
                val gridLayoutTitle = homePageModelList[position].title
                val gridProductScrollModelList = homePageModelList[position].horizontalProductScrollModelList
                (viewHolder as GridProductViewHolder).setGridProductLayout(gridProductScrollModelList, gridLayoutTitle)
            }
            else -> return
        }
    }

    override fun getItemCount(): Int {
        return homePageModelList.size
    }

    inner class BannerSliderViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bannersSliderViewpager: ViewPager
        private var currentPage = 2
        private var timer: Timer? = null
        private val DELAY_TIME: Long = 3000
        private val PERIOD_TIME: Long = 3000
         fun setBannersSliderViewpager(sliderModelsList: List<SliderModel>) {
            val sliderAdapter = SliderAdapter(sliderModelsList)
            bannersSliderViewpager.adapter = sliderAdapter
            bannersSliderViewpager.clipToPadding = false
            bannersSliderViewpager.pageMargin = 20
            bannersSliderViewpager.currentItem = currentPage
            val onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
                override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
                override fun onPageSelected(i: Int) {
                    currentPage = i
                }

                override fun onPageScrollStateChanged(i: Int) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(sliderModelsList)
                    }
                }
            }
            bannersSliderViewpager.addOnPageChangeListener(onPageChangeListener)
            startBannerSlideShow(sliderModelsList)
            bannersSliderViewpager.setOnTouchListener { v, event ->
                pageLooper(sliderModelsList)
                stopBannerSlideShow()
                if (event.action == MotionEvent.ACTION_UP) {
                    startBannerSlideShow(sliderModelsList)
                }
                false
            }
        }

         fun pageLooper(sliderModelsList: List<SliderModel>) {
            if (currentPage == sliderModelsList.size - 2) {
                currentPage = 2
                bannersSliderViewpager.setCurrentItem(currentPage, false)
            }
            if (currentPage == 1) {
                currentPage = sliderModelsList.size - 3
                bannersSliderViewpager.setCurrentItem(currentPage, false)
            }
        }

         fun startBannerSlideShow(sliderModelsList: List<SliderModel>) {
            val handler = Handler()
            val update = Runnable {
                if (currentPage >= sliderModelsList.size) {
                    currentPage = 1
                }
                bannersSliderViewpager.setCurrentItem(currentPage++, true)
            }
            timer = Timer()
            timer!!.schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, DELAY_TIME, PERIOD_TIME)
        }

         fun stopBannerSlideShow() {
            timer!!.cancel()
        }

        init {
            bannersSliderViewpager = itemView.findViewById(R.id.banner_slider_view_pagger)
        }
    }

    inner class StripAdBannerViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stripAdImage: ImageView
        private val stripAdContainer: ConstraintLayout
         fun setStripAd(resource: Int, color: String) {
            stripAdImage.setImageResource(resource)
            stripAdContainer.setBackgroundColor(Color.parseColor(color))
        }

        init {
            stripAdImage = itemView.findViewById(R.id.strip_ad_image)
            stripAdContainer = itemView.findViewById(R.id.strip_ad_container)
        }
    }

    inner class HorizonatlProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val horizonatlLayoutTitle: TextView
        private val horizonatlLayoutViewAllBtn: Button
        private val horizontalRecyclerView: RecyclerView
         fun setHorizontalProductLayout(horizontalProductScrollModelList: List<HorizontalProductScrollModel>, title: String) {
            horizonatlLayoutTitle.text = title
            if (horizontalProductScrollModelList.size > 8) {
                horizonatlLayoutViewAllBtn.visibility = View.VISIBLE
            } else {
                horizonatlLayoutViewAllBtn.visibility = View.INVISIBLE
            }
            val horizontalProductScrollAdapter = HorizontalProductScrollAdapter(horizontalProductScrollModelList)
            val linearLayoutManager = LinearLayoutManager(itemView.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            horizontalRecyclerView.layoutManager = linearLayoutManager
            horizontalRecyclerView.adapter = horizontalProductScrollAdapter
            horizontalProductScrollAdapter.notifyDataSetChanged()
        }

        init {
            horizonatlLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title)
            horizonatlLayoutViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_viewall_btn)
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recylerview)
        }
    }

    inner class GridProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gridLayoutTitle: TextView
        private val gridLayoutViewAllbtn: Button
        private val gridView: GridView
         fun setGridProductLayout(horizontalProductScrollModelList: List<HorizontalProductScrollModel>, title: String) {
            gridLayoutTitle.text = title
            gridView.adapter = GridProductVLayoutAdapter(horizontalProductScrollModelList)
        }

        init {
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title)
            gridLayoutViewAllbtn = itemView.findViewById(R.id.grid_layout_product_viewall_btn)
            gridView = itemView.findViewById(R.id.grid_product_layout_gridview)
        }
    }

}