package com.example.a5000wordsbookquiz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.data.model.QuestionData
import com.example.a5000wordsbookquiz.databinding.ItemQuestionBinding

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(private val binding: ItemQuestionBinding) :
        ViewHolder(binding.root) {
        fun bind(questionData: QuestionData) {

        }
    }

    var models = listOf<QuestionData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        val binding = ItemQuestionBinding.bind(v)
        return QuestionViewHolder(binding)
    }


    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(models[position])
    }
}