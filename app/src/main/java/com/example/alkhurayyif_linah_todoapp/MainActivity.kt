package com.example.alkhurayyif_linah_todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var taskItem:ArrayList<ToDo>
    private lateinit var toDoAdapter: toDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = "To Do App"
        taskItem = ArrayList()
        toDoAdapter = toDoAdapter(taskItem)
        var MainActivity = findViewById<ConstraintLayout>(R.id.MainActivity)
        floatingActionButton.setOnClickListener {
            customAlert()
            RecyclerView.adapter =toDoAdapter
            RecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var countNumber =0
                if(taskItem.size==0){
                    Snackbar.make(MainActivity, "The To Do list is empty", Snackbar.LENGTH_SHORT).show()
                }else{
                    for(task in taskItem){
                        if(task.checked){
                            countNumber++
                        }
                    }
                    if(countNumber>0){

                        Toast.makeText(this, "$countNumber items deleted", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(this, "Theres no items selected to delete", Toast.LENGTH_LONG).show()
                    }
                }

        toDoAdapter.deleteTask()
        return super.onOptionsItemSelected(item)
    }
    private fun customAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        input.hint = "Enter to-do item"
        // here we set the message of our alert dialog
        dialogBuilder.setTitle("New Item")
            // positive button text and action
            .setPositiveButton("ADD", DialogInterface.OnClickListener {
                    dialog, id ->
                if(input.text.toString()==""){

                    Snackbar.make(MainActivity,"You should enter a text",Snackbar.LENGTH_LONG).show()
                }else{  taskItem.add(ToDo(input.text.toString()))}

            })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }
}