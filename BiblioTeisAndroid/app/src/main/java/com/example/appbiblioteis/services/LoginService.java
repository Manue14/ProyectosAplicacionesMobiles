package com.example.appbiblioteis.services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;

import com.example.appbiblioteis.API.models.LoginFormObject;
import com.example.appbiblioteis.API.models.User;
import com.example.appbiblioteis.API.repository.BookRepository;
import com.example.appbiblioteis.API.repository.UserRepository;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import okhttp3.internal.Internal;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public void logIn(String email, String password) {
        boolean success = false;
        LoginFormObject formObject = new LoginFormObject(email, password);
        userRepository.loginUser(formObject, new BookRepository.ApiCallback<User>() {
            @Override
            public void onSuccess(User result) {
                if (result == null) {
                    Log.d("LoginService", "Usuario no existe o contraseña incorrecta");
                }
                Session session = Session.getInstance();
                session.setUser(result);
                User currentLoggedUser = session.getUser();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("LoginService", "Error al logearse");
            }
        });
    }

    public void logOut(String username) {

    }

}
