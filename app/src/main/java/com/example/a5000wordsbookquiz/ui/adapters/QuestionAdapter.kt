package com.example.a5000wordsbookquiz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.databinding.ItemQuestionBinding

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(private val binding: ItemQuestionBinding) :
        ViewHolder(binding.root) {
        fun bind(questionData: QuestionData) {
            binding.tvQuestion.text = questionData.question
            binding.btnOptionOne.text = questionData.answer1
            binding.btnOptionTwo.text = questionData.answer2
            binding.btnOptionThree.text = questionData.answer3
            binding.btnOptionFour.text = questionData.answer4
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