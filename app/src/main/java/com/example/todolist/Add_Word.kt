package com.example.todolist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add__word.*
import java.io.PrintStream

class Add_Word : AppCompatActivity() {

    private val activityFile = "extraactivity.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__word)
    }

    fun add_new_activity(view: View){
        val new_activity = new_activity.text.toString()
        val outStream = PrintStream(openFileOutput(activityFile, Context.MODE_PRIVATE))
        outStream.println(new_activity)
        outStream.close()

        val myIntent = Intent()
        myIntent.putExtra("new", new_activity)
        setResult(Activity.RESULT_OK, myIntent)
        finish()

    }
}
