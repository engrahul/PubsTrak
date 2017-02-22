package pubstrak.ahanaprods.com.pubstrak;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{
    //declare
    private FirebaseAuth firebaseAuth;

    private EditText etNewPwd;
    private Button btChgPwd;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //initialize
        firebaseAuth = FirebaseAuth.getInstance();

        //initialize Progress Dialog
        progressDialog = new ProgressDialog(this);


        etNewPwd = (EditText) findViewById(R.id.etNewPwd);
        btChgPwd = (Button) findViewById(R.id.btChangePwd);

        //get current user
        FirebaseUser fbUser = firebaseAuth.getCurrentUser();
        if(fbUser != null){
            Toast.makeText(getApplicationContext(), "You are logged in.", Toast.LENGTH_SHORT).show();
        }
        else{
            //not logged
            Toast.makeText(getApplicationContext(), "You are not logged in.", Toast.LENGTH_SHORT).show();
            finish();
        }

        //function for responding to clicks
        btChgPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //respond to Change Password button click
        if(v == btChgPwd){
            ChangePass();
        }
    }

    private void ChangePass() {
        String strNewPwd = etNewPwd.getText().toString().trim();
        //check if password are not blank
        if(TextUtils.isEmpty(strNewPwd)){
            Toast.makeText(this, "Password cannot be blank.", Toast.LENGTH_SHORT).show();
            return;
        }
        //check if password is atleast 6 characters long
        if(strNewPwd.length() < 6){
            Toast.makeText(this, "Password should be atleast 6 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        //if email and pwd are valid, show progress dialog
        progressDialog.setMessage("Password Change in progress...");
        progressDialog.show();

        //change password
        //get current user
        final FirebaseUser fbUser = firebaseAuth.getCurrentUser();
        fbUser.updatePassword(strNewPwd).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.v("TAG", "exception: " + task.getException());
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Password change successful.", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else {
                    progressDialog.dismiss();
                    if (task.getException() != null){
                        //alert dialog
                        AlertDialog.Builder myAlertDialog;
                        myAlertDialog = new AlertDialog.Builder(getApplicationContext());

                        myAlertDialog.setMessage(task.getException().toString());
                        myAlertDialog
                                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        myAlertDialog.show();
                    }
                    Toast.makeText(getApplicationContext(), "Password change Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //logoff and send to login screen
    }
}
