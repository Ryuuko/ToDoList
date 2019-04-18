package com.example.todolist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val REQ_CODE = 123
    private val works = ArrayList<String>()
    private lateinit var myAdapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readFile()
        setupList()
        list_items.setOnItemClickListener { _, _, index, _ ->
            // delete data in the array variable works
            works.removeAt(index)
            // notify the textview that the data has changed
            myAdapter.notifyDataSetChanged()
        }
    }

    private fun readFile(){
        val reader = Scanner(resources.openRawResource(R.raw.todolist))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            works.add(line)
        }
    }

    private fun setupList(){
        myAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, works)

        list_items.adapter = myAdapter
    }

    fun addButtonClick(view: View){
        val myIntent = Intent(this, Add_Word::class.java)
        startActivityForResult(myIntent, REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, myIntent: Intent?) {
        if(requestCode == REQ_CODE){
            if(myIntent != null){
                val line = myIntent.getStringExtra("new")
                works.add(line)
            }
        }
    }

}
