package com.zuzudev.advweek4.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zuzudev.advweek4.databinding.FruitListItemBinding
import com.zuzudev.advweek4.databinding.StudentListItemBinding
import com.zuzudev.advweek4.model.Fruit
import com.zuzudev.advweek4.model.Student
import com.zuzudev.advweek4.util.loadImage

class FruitListAdapter (val fruitList:ArrayList<Fruit>): RecyclerView.Adapter<FruitListAdapter.FruitViewHolder>()
{
    class FruitViewHolder(var binding: FruitListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FruitViewHolder {
        val binding = FruitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return FruitViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.binding.txtName.text = "Name: " + fruitList[position].name
        holder.binding.txtColor.text = "Color: " +fruitList[position].color
        holder.binding.txtCountry.text = "Country: " +fruitList[position].origin.country
        holder.binding.txtRegion.text = "Region: " +fruitList[position].origin.region
        var listVaries = "Varieties: \n"

        fruitList[position].varieties?.forEach {
            listVaries += "--. " + it + "\n"
        }
        holder.binding.txtVarieties.text = listVaries
        var imageView = holder.binding.imgView
        var progressBar = holder.binding.progressBar2
        imageView.loadImage(fruitList[position].images, progressBar)




    }
    override fun getItemCount(): Int {
        return fruitList.size
    }
    fun updateFruitList(newFruitList: ArrayList<Fruit>) {
        fruitList.clear()
        fruitList.addAll(newFruitList)
        notifyDataSetChanged()
    }

}