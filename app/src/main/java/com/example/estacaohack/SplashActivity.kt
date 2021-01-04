package com.example.estacaohack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Abrir a MainActivity após 5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            //inicia uma intent - transição da tela  Splash para a tela Login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 5000)
    }
}