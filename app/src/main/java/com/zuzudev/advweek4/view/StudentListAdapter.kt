package com.zuzudev.advweek4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zuzudev.advweek4.databinding.StudentListItemBinding
import com.zuzudev.advweek4.model.Student
import com.zuzudev.advweek4.util.loadImage

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailCLickListener
{
    class StudentViewHolder(var binding: StudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int):StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position] //tambah ini, yg sebelumnya dicommand smua
        holder.binding.listener = this

        //TIDAK PAKAI DATA BINDING
//        holder.binding.txtId.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//
//        holder.binding.btnDetail.setOnClickListener {
//            val studentId = studentList[position].id
//            val action = StudentListFragmentDirections.actionStudentDetailFragment(studentId!!)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//
//        picasso.build().load(studentList[position].photoUrl)
//            .into(holder.binding.imageView2, object:Callback {
//                override fun onSuccess() {
//                    holder.binding.progressBar3.visibility = View.INVISIBLE
//                    holder.binding.imageView2.visibility = View.VISIBLE
//                }
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//            })
//        var imageView = holder.binding.imageView2
//        var progressBar = holder.binding.progressBar3
//        imageView.loadImage(studentList[position].photoUrl, progressBar)

    }
    override fun getItemCount(): Int {
        return studentList.size
    }
    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetailFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

}