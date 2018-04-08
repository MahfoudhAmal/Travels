package com.example.ramzi.test;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {


    Button heber, rest, vol, act, home, trips, alert, me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        TextView dest = (TextView)findViewById(R.id.dest);
        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int0 = new Intent(ProfileActivity.this, DestActivity.class);
                startActivity(int0);
            }
        });
        heber = (Button) findViewById(R.id.heber);
        heber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int0 = new Intent(ProfileActivity.this,HeberActivity.class);
                startActivity(int0);
            }
        });
        TextView logout = (TextView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(ProfileActivity.this).logout();
                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        home = (Button) findViewById(R.id.home);
        trips = (Button) findViewById(R.id.trips);
        alert = (Button) findViewById(R.id.alert);
        me = (Button) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int0 = new Intent(ProfileActivity.this, MeActivity.class);
                int0.putExtra("email",SharedPrefManager.getInstance(ProfileActivity.this).getUserEmail());
                int0.putExtra("username",SharedPrefManager.getInstance(ProfileActivity.this).getUsername());
                startActivity(int0);
            }
        });
    }
    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
     //   getMenuInflater().inflate(R.menu.menu, menu);
     //   return true;
   // }

   // @Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) {
        //    case R.id.menuLogout:
          //      SharedPrefManager.getInstance(this).logout();
            //    finish();
             //   startActivity(new Intent(ProfileActivity.this, MainActivity.class));
             //   break;
        //}
       // return true;
    //}

}
        //textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        //textViewUserEmail = (TextView) findViewById(R.id.textViewUseremail);


        //textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        //textViewUsername.setText(SharedPrefManager.getInstance(this).getUsername());




