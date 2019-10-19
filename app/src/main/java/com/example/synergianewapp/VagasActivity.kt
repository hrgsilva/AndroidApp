package com.example.synergianewapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.synergianewapp.adapter.DisciplinaAdapter
import com.example.synergianewapp.domain.Disciplina
import com.example.synergianewapp.domain.DisciplinaService
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class VagasActivity : AppCompatActivity() {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)

        setSupportActionBar(toolbar)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar?.title = "Oportunidades Synergia"
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
// task para recuperar as disciplinas
        taskDisciplinas()
    }

    fun taskDisciplinas() {
        disciplinas = DisciplinaService.getDisciplinas(context)
// atualizar lista
        recyclerDisciplinas?.adapter = DisciplinaAdapter(disciplinas) { onClickDisciplina(it) }
    }

    // tratamento do evento de clicar em uma disciplina
// tratamento do evento de clicar em uma disciplina
    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT).show()
//        val intent = Intent(context, DisciplinaActivity::class.java)
////        intent.putExtra("disciplina", disciplina)
////        startActivity(intent)
    }
}
