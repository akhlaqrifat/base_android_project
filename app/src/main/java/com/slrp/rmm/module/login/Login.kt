package com.slrp.rmm.module.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.slrp.rmm.databinding.ActivityLoginBinding
import com.slrp.rmm.module.login.data.LoginRequest
import com.slrp.rmm.module.login.data.LoginResponse
import com.slrp.rmm.network.ErrorModel
import com.slrp.rmm.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val userName = binding.userNameBox.text.toString()
            val password = binding.passwordBox.text.toString()
            if (userName.isNotEmpty() && password.isNotEmpty()){
                callLogin(LoginRequest(userName,password))
            }
        }
    }

    private fun callLogin(loginRequest: LoginRequest) {
        viewModel.login(loginRequest).observe(this, Observer {
//            Log.e(this::class.simpleName,it.toString())
            if (it.status == Resource.Status.ERROR){
                val error = it.data as ErrorModel
                Log.i(this::class.simpleName,error.toString())
                Toast.makeText(this,error.apierror.message,Toast.LENGTH_SHORT).show()
            }else if (it.status == Resource.Status.LOADING){
                Toast.makeText(this,"fetching data",Toast.LENGTH_SHORT).show()
            }else{
                val response = it.data as LoginResponse
                Log.i(this::class.simpleName,response.toString())
                Toast.makeText(this,response.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}