package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginpageActivity extends AppCompatActivity {

    EditText user ;
    EditText pass ;
    Button lbtn ;

    TextView signup,forgetpass,google;
    private FirebaseAuth mAuth;
    private Dialog progressdialog;
    private TextView dialogtxt;
   // GoogleSignInClient googleSignInClient;

    //int RC_SIGN_IN;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        user = (EditText) findViewById(R.id.user);
        lbtn = (Button) findViewById(R.id.loginbtn);
        pass = (EditText) findViewById(R.id.pass) ;
        signup = (TextView) findViewById(R.id.sign);
        forgetpass = (TextView) findViewById(R.id.forgetpass);
        google = (TextView) findViewById(R.id.google);

        progressdialog = new Dialog(LoginpageActivity.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Signing In...");

        mAuth = FirebaseAuth.getInstance();



      /*  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);*/

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                opensign();
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openforgetpass();
            }
        });

       /* google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSign();
            }
        });*/

        lbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

               if (validate())
               {
                   login();
               }

            }
        });

    }

    private void openforgetpass() {

        Intent intent = new Intent(this, ForgotpassActivity.class);
        startActivity(intent);

    }

    private boolean validate()
    {

        if (user.getText().toString().isEmpty())
        {
            user.setError("Enter you emailaddress");
            return false;
        }

        if (pass.getText().toString().isEmpty())
        {
            pass.setError("Enter you password");
            return false;
        }
        return true;
    }

    private void login()
    {
        progressdialog.show();
        mAuth.signInWithEmailAndPassword(user.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginpageActivity.this, "login successful",
                                    Toast.LENGTH_SHORT).show();
                            progressdialog.dismiss();
                            Intent intent = new Intent(LoginpageActivity.this, HomepageActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginpageActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            progressdialog.dismiss();
                        }
                    }
                });
    }

   /* public void GoogleSign()
    {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try
            {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());

            }
            catch (Exception e)
            {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void firebaseAuth(String idToken)
    {

        progressdialog.show();

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {

                if (task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(loginpage.this, "Google SignIn successful",
                            Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();
                    Intent intent = new Intent(loginpage.this, OTP.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(loginpage.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    progressdialog.dismiss();
                }
            }
        });
    }*/

    public void opensign()
    {
        Intent intent = new Intent(this, SmartWatch.class);
        startActivity(intent);
    }


}