/**
 * @author Caleb Parten
 * @author David Schmith
 * @author Mario Soto
 * @date 12/10/2024
 *
 * This file is the activity that is displayed when the application is opened. It consists of a
 * login screen.
 */
package com.example.secureshelledmessenger;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secureshelledmessenger.ui.home.AppTheme;
import com.example.secureshelledmessenger.ui.home.MainController;

public class LoginActivity extends AppCompatActivity {

    MainController mainController;
    private String userID;

    EditText usernameField;
    EditText passwordField;
    Button submitButton;
    TextView createAccountTextView;

    private Handler handler;
    private Runnable runnable;

    private AppTheme currentTheme;

    private String[] permissions = {Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!hasNotifPermission()){
            requestNotificationPermssion();
        }

        mainController = MainController.getInstance(this);

        // Load saved theme
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        currentTheme = AppTheme.fromString(prefs.getString("theme", AppTheme.LIGHT.getName())); // Default to LIGHT if not set

        // Apply theme
        applyTheme(currentTheme);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameField = findViewById(R.id.username_field);
        passwordField = findViewById(R.id.password_field);
        submitButton = findViewById(R.id.submit_button);

        // Initialize the createAccountTextView here
        createAccountTextView = findViewById(R.id.createAccountTextView);

        submitButton.setOnClickListener(view -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            //set alerts if fields are empty
            if(username.isEmpty()){
                usernameField.setError("Username is Required");
                usernameField.requestFocus();
            }
            else if(password.isEmpty()){
                passwordField.setError("Password is Required");
                passwordField.requestFocus();
            }

            //new thread so that application can request the API
            new Thread(() -> {
                String loginCheckResult = mainController.checkLogin(username, password);

                //update ui
                runOnUiThread(() -> {
                    userID = loginCheckResult;
                    System.out.println("Login Check Result (User ID): " + loginCheckResult);

                    //save the user info if login success
                    if(userID != null && !userID.isEmpty()){
                        mainController.updatePassword(password);
                        mainController.updateUsername(username);
                        mainController.updateUserID(Long.parseLong(userID));
                        goToMainActivity();
                    } else {

                        //set an error if login failure
                        usernameField.setError("Incorrect Username or Password");
                        usernameField.setText("");
                        passwordField.setText("");
                        usernameField.requestFocus();
                    }
                });
            }).start();
        });

        // Add OnClickListener for createAccountTextView
        createAccountTextView.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);  // Navigate to CreateAccountActivity
            startActivity(intent);
        });
    }

    //goes to the main activity
    public void goToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //apply the them to the application
    private void applyTheme(AppTheme theme) {
        setTheme(theme.getStyleResId()); // Directly apply the theme
    }

    //check for notification permission
    private boolean hasNotifPermission(){
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED);
    }

    //check for internet permission
    private boolean hasInternetPermission(){
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED);
    }

    //request the notification permissions
    private void requestNotificationPermssion(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
    }

    //set the application permission granted if user allowed notifications
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            for(int i = 0; i < permissions.length; i++){
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                    requestNotificationPermssion();
                }
            }
        }
    }
}
