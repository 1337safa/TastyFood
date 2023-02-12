package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.RcvItemFavouriteFoodBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_category.FoodByCategoryItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview.interfaces.ClickOnFavouriteFoodItem

class RcvAdapterFavouriteFood(

    private val clickOnFavouriteFoodItem: ClickOnFavouriteFoodItem

) : RecyclerView.Adapter<RcvAdapterFavouriteFood.ContentHolder>() {

    private var foodByCategoryItemsArrayList = ArrayList<FoodByCategoryItem>()

    class ContentHolder(
        private val binding: RcvItemFavouriteFoodBinding,
        private val clickOnFavouriteFoodItem: ClickOnFavouriteFoodItem
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindDataWithView(foodByCategoryItem: FoodByCategoryItem) {

            Glide.with(binding.root)
                .load(foodByCategoryItem.strMealThumb)
                .centerCrop()
                .into(binding.imgFavouriteFoodIcon)

            binding.imgFavouriteFoodIcon.setOnClickListener(View.OnClickListener {
                clickOnFavouriteFoodItem.clickOnFavouriteFoodItem(foodByCategoryItem)
            })

            binding.btnDeleteFavouriteFood.setOnClickListener(View.OnClickListener {
                clickOnFavouriteFoodItem.clickOnDeleteFavouriteFoodItem(foodByCategoryItem)
            })

        }

    }

    override fun getItemCount(): Int = this.foodByCategoryItemsArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemFavouriteFoodBinding.inflate(inflater, parent, false)

        return ContentHolder(binding, this@RcvAdapterFavouriteFood.clickOnFavouriteFoodItem)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindDataWithView(this.foodByCategoryItemsArrayList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitNewFoodByCategoryItemList(foodByCategoryItem: List<FoodByCategoryItem>) {

        foodByCategoryItemsArrayList.clear()
        foodByCategoryItemsArrayList.addAll(foodByCategoryItem)
        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun favouriteFoodWasDeleted(databaseIdFood: String?) {

        databaseIdFood?.let {
            val indexToDelete = foodByCategoryItemsArrayList.indexOfFirst {
                it.databaseIdFood == databaseIdFood
            }
            foodByCategoryItemsArrayList.removeAt(indexToDelete)
            notifyDataSetChanged()
        }

    }

}