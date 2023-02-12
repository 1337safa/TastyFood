package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.RcvItemFoodByCategoryBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.diffutil.FoodByCategoryDiffUtil
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces.ClickOnFoodByCategoryItemAdapter

class RcvAdapterFoodByCategory(

    private val clickOnFoodByCategoryItemAdapter: ClickOnFoodByCategoryItemAdapter

) : RecyclerView.Adapter<RcvAdapterFoodByCategory.ContentHolder>() {

    private var foodByCategoryArrayList = ArrayList<FoodByCategoryItem>()

    class ContentHolder(

        private val binding: RcvItemFoodByCategoryBinding,
        private val clickOnFoodByCategoryItemAdapter: ClickOnFoodByCategoryItemAdapter

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindDataWithView(foodByCategoryItem: FoodByCategoryItem) {

            Glide.with(binding.root)
                .load(foodByCategoryItem.strMealThumb)
                .centerCrop()
                .into(binding.imgFoodByCategoryIcon)

            binding.imgFoodByCategoryIcon.setOnClickListener(View.OnClickListener {
                clickOnFoodByCategoryItemAdapter
                    .clickOnFoodByCategoryItemFromRcvAdapterFoodByCategory(foodByCategoryItem)
            })

            binding.btnFavouriteFood.setOnClickListener(View.OnClickListener {
                clickOnFoodByCategoryItemAdapter.clickOnBtnFavouriteFoodFromRcvAdapterFoodByCategory(
                    foodByCategoryItem
                )
            })

        }

    }

    override fun getItemCount(): Int = this.foodByCategoryArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemFoodByCategoryBinding.inflate(inflater, parent, false)

        return ContentHolder(binding, this.clickOnFoodByCategoryItemAdapter)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindDataWithView(this.foodByCategoryArrayList[position])
    }

    fun submitNewFoodByCategoryList(newFoodByCategoryList: List<FoodByCategoryItem>) {

        val foodByCategoryDiffUtil = FoodByCategoryDiffUtil(
            oldList = this.foodByCategoryArrayList, newFoodByCategoryList
        )
        val calculateDiff = DiffUtil.calculateDiff(foodByCategoryDiffUtil)
        this.foodByCategoryArrayList.removeAll(this.foodByCategoryArrayList.toSet())
        this.foodByCategoryArrayList.addAll(newFoodByCategoryList)
        calculateDiff.dispatchUpdatesTo(this@RcvAdapterFoodByCategory)

    }

}