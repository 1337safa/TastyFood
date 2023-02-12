package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.RcvItemFoodCategoryBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategoriesItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.diffutil.FoodCategoryDiffUtil
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces.ClickOnFoodCategory

class RcvAdapterAllFoodCategories(

    private val clickOnFoodCategory: ClickOnFoodCategory

) : RecyclerView.Adapter<RcvAdapterAllFoodCategories.ContentHolder>() {

    private var foodCategoryArrayList = ArrayList<AllFoodCategoriesItem>()

    class ContentHolder(private val binding: RcvItemFoodCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindDataWithView(
            category: AllFoodCategoriesItem, clickOnFoodCategory: ClickOnFoodCategory
        ) {

            Glide.with(binding.root)
                .load(category.strCategoryThumb)
                .centerCrop()
                .into(binding.imgFoodImg)

            binding.imgFoodImg.setOnClickListener(View.OnClickListener {
                clickOnFoodCategory.clickOnFoodCategoryFromRcvAdapterFoodCategory(category)
            })

        }

    }

    override fun getItemCount(): Int = foodCategoryArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemFoodCategoryBinding.inflate(inflater, parent, false)
        return ContentHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindDataWithView(
            foodCategoryArrayList[position],
            clickOnFoodCategory
        )
    }

    fun submitNewFoodCategoryList(newFoodCategoryList: List<AllFoodCategoriesItem>) {

        val foodCategoryDiffUtil = FoodCategoryDiffUtil(
            oldList = this.foodCategoryArrayList, newList = newFoodCategoryList
        )
        val calculateDiff = DiffUtil.calculateDiff(foodCategoryDiffUtil)
        this.foodCategoryArrayList.clear()
        this.foodCategoryArrayList.addAll(newFoodCategoryList)
        calculateDiff.dispatchUpdatesTo(this@RcvAdapterAllFoodCategories)

    }

}