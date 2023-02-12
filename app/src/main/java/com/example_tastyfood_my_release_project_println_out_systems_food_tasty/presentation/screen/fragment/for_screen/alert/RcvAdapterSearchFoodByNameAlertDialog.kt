package com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.alert

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.databinding.AlertItemSearchByNameFoodBinding
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.domain.model.food.food_by_name.FoodByNameItem
import com.example_tastyfood_my_release_project_println_out_systems_food_tasty.presentation.screen.fragment.for_screen.alert.interfaces.ClickOnSomeFoodByName

class RcvAdapterSearchFoodByNameAlertDialog(

    private val clickOnSomeFoodByName: ClickOnSomeFoodByName

) : RecyclerView.Adapter<RcvAdapterSearchFoodByNameAlertDialog.ContentHolder>() {

    private var foodByNameArrayList = ArrayList<FoodByNameItem>()

    class ContentHolder(

        private val binding: AlertItemSearchByNameFoodBinding,
        private val clickOnSomeFoodByName: ClickOnSomeFoodByName

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindDataWithView(foodByNameItem: FoodByNameItem) {

            Glide.with(binding.root)
                .load(foodByNameItem.strMealThumb)
                .centerCrop()
                .into(binding.foodImg)

            binding.tvNameOfFood.text = foodByNameItem.strMeal

            binding.btnMore.setOnClickListener(View.OnClickListener {
                clickOnSomeFoodByName.clickOnMoreFromRcvAdapterSearchFoodByNameAlertView(
                    foodByNameItem
                )
            })

            binding.btnCancel.setOnClickListener(View.OnClickListener {
                clickOnSomeFoodByName.clickOnCancelFromRcvAdapterSearchFoodByNameAlertView()
            })

        }

    }

    override fun getItemCount(): Int = this.foodByNameArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AlertItemSearchByNameFoodBinding.inflate(
            inflater, parent, false
        )

        return ContentHolder(binding, this.clickOnSomeFoodByName)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bindDataWithView(this.foodByNameArrayList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitNewFoodByNameList(

        foodByNameArrayList: List<FoodByNameItem>
    ) {
        this.foodByNameArrayList.clear()
        this.foodByNameArrayList.addAll(foodByNameArrayList)
        notifyDataSetChanged()

    }

}