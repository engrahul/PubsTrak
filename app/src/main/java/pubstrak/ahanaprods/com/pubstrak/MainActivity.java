package pubstrak.ahanaprods.com.pubstrak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //declare
    private FirebaseAuth firebaseAuth;

    private TextView tvUser;
    private Button btLogoff;
    private Button btChangePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        firebaseAuth = FirebaseAuth.getInstance();

        tvUser = (TextView) findViewById(R.id.tvUser);
        btLogoff = (Button) findViewById(R.id.btLogOff);
        btChangePwd = (Button) findViewById(R.id.btChangePwd);

        //check if user is not logged

        //get current user
        FirebaseUser fbUser = firebaseAuth.getCurrentUser();
        //set label to email of user logged in
        tvUser.setText(fbUser.getEmail() + " logged");
        Log.v("TAG", "In onClick(View v)...");

        //attach the listner to LogOff button
        btLogoff.setOnClickListener(this);

        //attach the listner to Change Passwordbutton
        btChangePwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.v("TAG", "In onClick(View v)...");
        //check id Logoff button is clicked
        if(v == btLogoff){
            //logoff the user
            finish();   //finish the current activity
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        //Change Password button OnClick
        if(v == btChangePwd){
            //logoff the user
            //finish();   //finish the current activity
            //open change password screen
            Log.v("TAG", "In btChangePwd...");
            startActivity(new Intent(getApplicationContext(), ChangePassword.class));
        }
    }

}
