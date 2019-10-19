package com.example.mytrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View?) {
        val id = view?.id
        if ( id == R.id.btn_calculate){
            handleCalculate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calculate.setOnClickListener(this)
    }

    private fun handleCalculate(){
        if(IsValid()){

            try{

                val distance = distance.text.toString().toFloat()
                val price = price.text.toString().toFloat()
                val autonomy = autonomy.text.toString().toFloat()

                val result = ((distance * price) / autonomy)
                textResult.setText("Total é ${result}")

            }catch (nfe: NumberFormatException){
                Toast.makeText(this,"Por Favor Informe Valores Válidos", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Por Favor Informe Valores Válidos", Toast.LENGTH_LONG).show()
        }
    }

    private fun IsValid(): Boolean{
        return distance.text.toString() != ""
                && autonomy.text.toString() != ""
                && price.text.toString() != "0"
    }
}
