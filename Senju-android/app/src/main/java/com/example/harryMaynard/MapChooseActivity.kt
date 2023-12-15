package com.example.harryMaynard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf

class MapChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addmarker)

        val submit = findViewById<Button>(R.id.submitButton)

        submit.setOnClickListener(){
            //Gets string data from input and passes it to main activity
            getDetails(findViewById<EditText>(R.id.nameInput).getText().toString(),findViewById<EditText>(R.id.descriptionInput).getText().toString(),findViewById<EditText>(R.id.typeInput).getText().toString(),)
        }


    }

    //Creates bundle with strings needed for the map marker
    private fun getDetails(name:String, description:String, type:String){
        val intent = Intent()
        //Creates bundle with user entered data in it for the marker
        val bundle = bundleOf(
            Pair("Name",name),
            Pair("Description",description),
            Pair("Type",type)
        )
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()
    }
}
