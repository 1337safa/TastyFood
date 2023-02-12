package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem

class FoodByCategoryDiffUtil(

    private val oldList: List<FoodByCategoryItem>,
    private val newList: List<FoodByCategoryItem>

): DiffUtil.Callback() {

    override fun getOldListSize(): Int = this.oldList.size
    override fun getNewListSize(): Int = this.newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return this.oldList[oldItemPosition].idMeal == this.newList[newItemPosition].idMeal
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return this.oldList[oldItemPosition].equals(this.newList[newItemPosition])
    }


}