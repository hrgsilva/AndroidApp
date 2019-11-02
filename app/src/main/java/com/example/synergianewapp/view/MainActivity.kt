package com.example.synergianewapp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import br.com.fernandosousa.lmsapp.Prefs
import com.example.synergianewapp.R
import com.example.synergianewapp.domain.DisciplinaService
import com.example.synergianewapp.domain.User
import com.example.synergianewapp.domain.UsuarioService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_vaga_cadastro.*

class MainActivity : AppCompatActivity() {

    val contexto = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Criando evento de click e instânciando as funções


        buttonStart.setOnClickListener { loginCondition() }

//        buttonStart.setOnClickListener { saveApi() }

        textViewAccount.setOnClickListener { createAcount() }

        textViewRecovery.setOnClickListener { recoveryAccount() }

        inputUser.setText(Prefs.getString("lembrarLogin"))
        inputPassword.setText(Prefs.getString("lembrarPassword"))
        checkLogin.isChecked = Prefs.getBoolean("Lembrar")
    }

    private fun loginCondition() {
        val mLogin: String = inputUser.text.toString()
        val mPassword: String = inputPassword.text.toString()
        val guardaLogin = checkLogin.isChecked
        Prefs.setBoolean("Lembrar", guardaLogin)

        if (guardaLogin) {
            Prefs.setString("lembrarLogin", mLogin)
            Prefs.setString("lembrarPassword", mPassword)
        } else {
            Prefs.setString("lembrarLogin", "")
            Prefs.setString("lembrarPassword", "")
        }

        if (mLogin == "hercules" && mPassword == "1") {
//                Toast.makeText(this, "Bem vindo, $Slogin!", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(contexto, TelaInicialActivity::class.java)
            startActivity(intent)
        } else if (mLogin == "" && mPassword == "") {
            Toast.makeText(
                this, "Campos em branco, Informe usuário e senha!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this, "Usuário ou senha incorretos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun createAcount() {
        var intent: Intent = Intent(contexto, AccountActivity::class.java)
        startActivity(intent)
    }

    private fun recoveryAccount() {
        val editAlert = AlertDialog.Builder(contexto).create()

        val editView = layoutInflater.inflate(R.layout.alert_recovery, null)

        editAlert.setTitle("Recupere Sua Conta!")
        editAlert.setIcon(R.drawable.ic_email)
        editAlert.setView(editView)
        editAlert.show()
    }

//    private fun taskAtualizar(user: User) {
//        // Thread para salvar a disciplina
//        Thread {
//            UsuarioService.login(contexto, user)
//            runOnUiThread {
//
//                // após cadastrar, voltar para activity anterior
//                finish()
//            }
//        }.start()
//    }
//
//    private fun saveApi() {
//        val usuario = User()
//        usuario.login = inputUser.text.toString()
//        usuario.senha = inputPassword.text.toString()
//        taskAtualizar(usuario)
//    }
}

