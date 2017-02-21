package pubstrak.ahanaprods.com.pubstrak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //call ValidateCrentials function on click of submit button
    public void ValidateCredentials(View view){
        Log.v("TAG", "In ValidateCrentials...");
        //TODO: Authenticate using username & password
        //TODO: Check if user is Active
        //TODO: Check if user has admin rights
        Intent intent = new Intent(this, MainActivity.class);
        Log.v("TAG", "Starting Main Screen...");
        startActivity(intent);
        //prevent to come back to this screen by killing it
        Log.v("TAG", "Calling finish()...");
        finish();
    }

    //TODO: version label at the bottom
    //TODO: implement 'Forgot Password'
}
