package br.com.doggerz.firebaseauthteste.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import br.com.doggerz.firebaseauthteste.R;
import br.com.doggerz.firebaseauthteste.Util.CircleTransform;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        } else {
            showProfile();
        }

        Button signOut = (Button) findViewById(R.id.signOut);
        assert signOut != null;
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                finish();
            }
        });
    }

    private void showProfile() {
        if (mFirebaseUser.getPhotoUrl() != null) {
            ImageView userPhoto = (ImageView) findViewById(R.id.userPhoto);
            Picasso.with(getApplicationContext()).load(mFirebaseUser.getPhotoUrl()).transform(new CircleTransform()).into(userPhoto);
        }

        TextView userName = (TextView) findViewById(R.id.userName);
        assert userName != null;
        userName.setText(mFirebaseUser.getDisplayName());

        TextView userEmail = (TextView) findViewById(R.id.userEmail);
        assert userEmail != null;
        userEmail.setText(mFirebaseUser.getEmail());
    }
}
