package com.example.pppbapi

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.pppbapi.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(name : String?, type : String?, role : String?, description : String?, image : String?, twitter : String?, github : String?, website : String?) : BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val args = Bundle()
            args.putString("name", name)
            args.putString("type", type)
            args.putString("role", role)
            args.putString("description", description)
            args.putString("image", image)
            args.putString("twitter", twitter)
            args.putString("github", github)
            args.putString("website", website)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        with(binding){
            val name = arguments?.getString("name")
            val type = arguments?.getString("type")
            val role = arguments?.getString("role")
            val imageURL = arguments?.getString("image")
            val description = arguments?.getString("description")
            val github = arguments?.getString("github")
            val website = arguments?.getString("website")
            val twitter = arguments?.getString("twitter")

            Glide.with(this@BottomSheetFragment).load(imageURL).into(imgLogo)

            txtName.text = name
//            txtType.text = "$type - $role"
            txtType.text = "$type"

            if (description.isNullOrEmpty()) {
                cardDescription.visibility = View.INVISIBLE
            } else {
                txtDescription.text = description
            }

            imgLogoGithub.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(github))
                startActivity(browserIntent)
            }
            imgLogoTwitter.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(twitter))
                startActivity(browserIntent)
            }
            imgLogoWeb.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
                startActivity(browserIntent)
            }

        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            setOnShowListener {
                val dimLayout = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                dimLayout?.background = ColorDrawable(Color.TRANSPARENT)
            }
        }
    }
}