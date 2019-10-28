package com.example.motivation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.mock.Mock
import com.example.motivation.utils.MotivationConstants
import com.example.motivation.utils.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter: Int = MotivationConstants.PHRASE_FILTER.ALL
    private lateinit var mSecurityPreferences : SecurityPreferences
    private val mMock = Mock()

    val contexto: MainActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        //Eventos
        setListiners()
        // Inicializa
        handleFilter(R.id.imageAll)

        verifyUserName()
    }


    private fun setListiners(){
        imageAll.setOnClickListener(contexto)
        imageSun.setOnClickListener(contexto)
        imageHappy.setOnClickListener(contexto)
        buttonPhrase.setOnClickListener(contexto)
    }

    private fun verifyUserName(){
        textUserName.text = mSecurityPreferences.getSttorageString(MotivationConstants.KEY.PERSON_NAME)
    }

    override fun onClick(view: View?) {
        val id = view?.id

        val listId = listOf(R.id.imageAll,R.id.imageSun,R.id.imageHappy)

        if (id in listId){
            handleFilter(id)

        }else{
            refreshPhrase()
        }
    }

    private fun handleFilter(id: Int?) {
        if (id == R.id.imageAll) {
            mFilter = MotivationConstants.PHRASE_FILTER.ALL
            imageAll.setImageResource(R.drawable.ic_all_selected)
            imageSun.setImageResource(R.drawable.ic_sun_unselected)
            imageHappy.setImageResource(R.drawable.ic_happy_unselected)
        }else if (id == R.id.imageSun){
            mFilter = MotivationConstants.PHRASE_FILTER.SUN
            imageAll.setImageResource(R.drawable.ic_all_unselected)
            imageSun.setImageResource(R.drawable.ic_sun_selected)
            imageHappy.setImageResource(R.drawable.ic_happy_unselected)
        }else{
            mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
            imageAll.setImageResource(R.drawable.ic_all_unselected)
            imageSun.setImageResource(R.drawable.ic_sun_unselected)
            imageHappy.setImageResource(R.drawable.ic_happy_selected)
        }
    }

    private fun refreshPhrase(){
        textPhrase.text = mMock.getPrhase(mFilter)
    }

}
