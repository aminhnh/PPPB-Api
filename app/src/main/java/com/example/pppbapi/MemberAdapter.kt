package com.example.pppbapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pppbapi.databinding.ItemMemberBinding
import com.example.pppbapi.model.Data

typealias OnClickMember = (Data) -> Unit
class MemberAdapter(private val listMember : List<Data>, private val onClickMember: OnClickMember) : RecyclerView.Adapter<MemberAdapter.ItemMemberViewHolder>() {
    inner class ItemMemberViewHolder(private val binding : ItemMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Data) {
            with(binding) {
                // edit stuff here
                txtName.text = data.name
                Glide.with(binding.root.context).load(data.image).into(imgProfile)

                itemView.setOnClickListener {
                    onClickMember(data)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMemberViewHolder {
        val binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMember.size
    }

    override fun onBindViewHolder(holder: ItemMemberViewHolder, position: Int) {
        holder.bind(listMember[position])
    }
}