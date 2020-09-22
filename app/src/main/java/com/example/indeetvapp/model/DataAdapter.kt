package com.example.indeetvapp.model

import android.graphics.Bitmap
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.indeetvapp.R


class DataAdapter(private val posterList: List<PosterData>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {



    class DataViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        var nameTxt: TextView = view.findViewById(R.id.posterName_txt)
        var planTxt: TextView = view.findViewById(R.id.planTxt)
        var createdValueTxt: TextView = view.findViewById(R.id.createdValueTxt)
        var updatedValueTxt: TextView = view.findViewById(R.id.updateValueTxt)
        var durationTxt: TextView = view.findViewById(R.id.durationTxt)
        var releaseYear: TextView = view.findViewById(R.id.releaseYearTxt)
        var typeTxt: TextView = view.findViewById(R.id.typeTxt)
        var shortDesc: TextView = view.findViewById(R.id.shortDescTxt)
        var description: TextView = view.findViewById(R.id.descTxt)
        var imageView : ImageView = view.findViewById(R.id.posterImage)


    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataAdapter.DataViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)


        return DataViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
       val item =  posterList[position]
        if(!TextUtils.isEmpty(item.name)){
            holder.nameTxt?.text = item.name
        }

        holder.planTxt.checkVisibility(item.payment_plan)
        holder.createdValueTxt.checkVisibility(item.created_on)
        holder.updatedValueTxt.checkVisibility(item.updated_on)
        holder.description.checkVisibility(item.description)
        holder.shortDesc.checkVisibility(item.shortDescription)
        holder.releaseYear.checkVisibility(item.release_year)
        holder.typeTxt.checkVisibility(item.type)
        holder.durationTxt.checkVisibility(item.video_duration)


    }

    fun setVisibilityOfText(str: String, view : TextView){
        if(!TextUtils.isEmpty(str)){
            view.text = str
        }else{
            view.visibility = View.GONE
        }
    }

    fun TextView.checkVisibility(str: String){
        if(!TextUtils.isEmpty(str)){
            text = str
        }else{
            visibility = View.GONE
        }

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = posterList.size
}