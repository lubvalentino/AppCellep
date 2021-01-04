package com.example.estacaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obter o dado passado pela intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        //Abrindo o arquivo de Shared Preferences
        val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

        //Recuperar os dados do Shared Preferences
        val nome = sharedPrefs.getString("NOME", "Anônimo")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val genero = sharedPrefs.getString("GENERO", "Outros")

        //Exibindo as informações obitdas para o usuário
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvNomeCompleto = findViewById<TextView>(R.id.tvNome)
        val tvGenero = findViewById<TextView>(R.id.tvGenero)

        tvNomeCompleto.text = "$nome $sobrenome"
        tvEmail.text = email
        tvGenero.text = genero

        //Botão iniciar WebActivity
        val btSite = findViewById<AppCompatButton>(R.id.btSite)
        btSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }

        //Botão sair aplicação
        val btSair = findViewById<AppCompatButton>(R.id.btSair)

        btSair.setOnClickListener {
            //Criando um alerta
            AlertDialog.Builder(this).setTitle("Atenção:").setMessage("Você realmente deseja sair?").setPositiveButton("Sim"){
                _,_ ->
                //Executanto o clique do botão SIM
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }.setNeutralButton("Cancelar"){
                _,_->}.setCancelable(false) //se você clicar fora do alerta ele não vai sumir
                .create() //vai permitir que isso seja realmente criado
                .show()
        }
    }
}