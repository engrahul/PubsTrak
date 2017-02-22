package pubstrak.ahanaprods.com.pubstrak;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener{
    //declarations
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    //TODO: remember password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize the value in controls
        etEmail = (EditText) findViewById(R.id.editEmail);
        etPassword = (EditText) findViewById(R.id.editPwd);
        btLogin = (Button) findViewById(R.id.btLogin);

        //initialize Progress Dialog
        progressDialog = new ProgressDialog(this);
        //initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //check if user already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //user is already logged in, go to main activity
            finish();   //finish the current activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        //function for responding to clicks
        btLogin.setOnClickListener(this);
    }

    // [START] function userLogin
    private void userLogin(){
        String strEmail = etEmail.getText().toString().trim();
        String strPassword = etPassword.getText().toString().trim();

        //check if email and password are not blank
        if(TextUtils.isEmpty(strEmail)){
            Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(strPassword)){
            Toast.makeText(this, "Please enter a valid password.", Toast.LENGTH_SHORT).show();
            return;
        }

        //if email and pwd are valid, show progress dialog
        progressDialog.setMessage("Login in progress...");
        progressDialog.show();

        //call firbase to validate credentials
        Log.d("TAG", "Before auth");
        firebaseAuth.signInWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //on successful login, call the main page
                            finish();   //finish the current activity
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{
                            //login not successful
                            Toast.makeText(getApplicationContext(), "Login not successful. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    // [END] function userLogin

    @Override
    public void onClick(View v) {
        //check if button is clicked
        if(v == btLogin){
            //call custom method
            userLogin();
            //Toast.makeText(this, "Login not successful. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }


    //TODO: version label at the bottom
    //TODO: implement 'Forgot Password'
}
