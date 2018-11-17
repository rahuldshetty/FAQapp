package com.dentalcare.dentalhelpcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Toolbar menuToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        mAuth=FirebaseAuth.getInstance();

        menuToolbar=findViewById(R.id.maintoolbar);
        setSupportActionBar(menuToolbar);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.menulogout :
                mAuth.signOut();
                Intent gotoStart=new Intent(MainActivity.this,StartScreen.class);
                startActivity(gotoStart);
                finish();
                return true;

            case R.id.menusettings:
                return true;

            default:return false;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            Intent toStart=new Intent(MainActivity.this,StartScreen.class);
            startActivity(toStart);
            finish();

        }
    }
}
