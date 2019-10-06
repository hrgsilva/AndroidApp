package com.example.synergiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class TelaInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        supportActionBar?.title = getString(R.string.curriculos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar){
            Toast.makeText(this, "clicou em buscar",
                Toast.LENGTH_LONG).show()
        }
        else if (id == R.id.action_atualizar){
            Toast.makeText(this,"clicou em atualizar",
                Toast.LENGTH_LONG).show()
        }
        else if(id == R.id.action_add){
            Toast.makeText(this,"clicou em adicionar",
                Toast.LENGTH_LONG).show()
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
        else if(id == R.id.action_config){
            Toast.makeText(this, "clicou em configuração",
                Toast.LENGTH_LONG).show()
            val intent = Intent(this, ConfiguracaoActivity::class.java)
            startActivity(intent)
        }
        else if (id == R.id.action_sair){
            finish()
        }
        else if(id == android.R.id.home){
            finish()
        }
            return super.onOptionsItemSelected(item)
    }
}
