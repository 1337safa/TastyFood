package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_category.AllFoodCategoriesItem

class FoodCategoryDiffUtil(

    private val oldList: List<AllFoodCategoriesItem>,
    private val newList: List<AllFoodCategoriesItem>

): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return this.oldList[oldItemPosition].idCategory == this.newList[newItemPosition].idCategory
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return this.oldList[oldItemPosition].equals(this.newList[newItemPosition])
    }

}