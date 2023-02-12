package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.RcvItemFoodByIdBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_id.FoodByIdItem

class RcvAdapterFoodById(): RecyclerView.Adapter<RcvAdapterFoodById.ContentHolder>() {

    private var foodByIdArrayList = ArrayList<FoodByIdItem>()

    class ContentHolder(

        private val binding: RcvItemFoodByIdBinding

    ): RecyclerView.ViewHolder(binding.root) {

        fun bindDataWithView(foodByIdItem: FoodByIdItem) {

            Glide.with(binding.root)
                .load(foodByIdItem.strMealThumb)
                .skipMemoryCache(true)
                .centerCrop()
                .into(binding.imgFoodByCategoryIcon)

            binding.tvNameOfFood.text = foodByIdItem.strMeal
            binding.tvFoodInstructions.text = foodByIdItem.strInstructions

        }

    }

    override fun getItemCount(): Int = this.foodByIdArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RcvItemFoodByIdBinding.inflate(inflater, parent, false)

        return ContentHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindDataWithView(this.foodByIdArrayList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitNewFoodIdList(foodByIdArrayList: List<FoodByIdItem>) {

        this.foodByIdArrayList.clear()
        this.foodByIdArrayList.addAll(foodByIdArrayList)
        notifyDataSetChanged()

    }

}