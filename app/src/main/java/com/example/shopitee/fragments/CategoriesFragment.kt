package com.example.shopitee.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopitee.R
import com.example.shopitee.adapters.CategoryAdapter
import com.example.shopitee.models.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_categories, container, false)

        val items = mutableListOf<CategoryModel>()

        val adapter = CategoryAdapter(items)

        val categoriesList: RecyclerView = view.findViewById(R.id.categoriesList)

        categoriesList.adapter = adapter
        categoriesList.layoutManager = LinearLayoutManager(requireContext())

        val progress: ProgressBar = view.findViewById(R.id.progress)

        progress.visibility = View.VISIBLE
        val query = FirebaseFirestore.getInstance()
                .collection("categories")

        query.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            progress.visibility = View.GONE
            if (firebaseFirestoreException != null) {
                Toast.makeText(requireActivity(), "Failed to fetch categories", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            val categories = querySnapshot?.toObjects(CategoryModel::class.java)

            items.addAll(categories ?: mutableListOf())
            adapter.notifyDataSetChanged()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance() = CategoriesFragment()
    }
}