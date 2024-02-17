package com.example.a5000wordsbookquiz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.data.TopicData
import com.example.a5000wordsbookquiz.databinding.ItemQuestionBinding
import com.example.a5000wordsbookquiz.databinding.ItemTopicBinding

class TopicsAdapter : RecyclerView.Adapter<TopicsAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(private val binding: ItemTopicBinding) :
        ViewHolder(binding.root) {
        fun bind(topicData: TopicData) {
            binding.tvName.text = topicData.name
            binding.card.setOnClickListener {
                onItemClick.invoke(topicData.id, adapterPosition)
            }
        }
    }

    var models = listOf<TopicData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        val binding = ItemTopicBinding.bind(v)
        return QuestionViewHolder(binding)
    }


    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onItemClick: (id: Int, position: Int) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (id: Int, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }
}