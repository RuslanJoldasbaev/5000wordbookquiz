package com.example.a5000wordsbookquiz.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.a5000wordsbookquiz.R
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.data.SectionData
import com.example.a5000wordsbookquiz.data.model.data.TopicData
import com.example.a5000wordsbookquiz.databinding.ItemQuestionBinding
import com.example.a5000wordsbookquiz.databinding.ItemSectionBinding
import com.example.a5000wordsbookquiz.databinding.ItemTopicBinding

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    inner class SectionViewHolder(private val binding: ItemSectionBinding) :
        ViewHolder(binding.root) {
        fun bind(sectionData: SectionData) {
            binding.tvName.text = sectionData.name
            binding.card.setOnClickListener {
                onItemClick.invoke(sectionData.id, adapterPosition)
            }
        }
    }

    var models = listOf<SectionData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_section, parent, false)
        val binding = ItemSectionBinding.bind(v)
        return SectionViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onItemClick: (id: Int, position: Int) -> Unit = { _, _ -> }
    fun setOnItemClickListener(onItemClick: (id: Int, position: Int) -> Unit) {
        this.onItemClick = onItemClick
    }
}