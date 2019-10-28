package com.example.motivation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.utils.MotivationConstants
import com.example.motivation.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private val contexto = this
    private lateinit var mSecurity: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurity = SecurityPreferences(contexto)
        buttonSave.setOnClickListener(contexto)
        verifyUserName()
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == R.id.buttonSave){
            Toast.makeText(contexto,"Click no botão", Toast.LENGTH_SHORT).show()
            handleSave()
        }
    }

    private fun verifyUserName(){
        editName.setText(mSecurity.getSttorageString(MotivationConstants.KEY.PERSON_NAME ))
    }

    private fun handleSave(){
        val name: String = editName.text.toString()

        if(name == ""){
            Toast.makeText(contexto, "por favor, informe o valor correto",Toast.LENGTH_SHORT).show()
        }else{
            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME,name)

            val it: Intent = Intent(this, MainActivity::class.java)
            startActivity(it)

        }
    }

}