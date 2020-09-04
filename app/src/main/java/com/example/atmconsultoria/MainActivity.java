package com.example.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servicos, R.id.nav_cliente,
                R.id.nav_contato, R.id.nav_sobre
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail() {

        String celular = "tel: 11996324674";
        String imagem = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQJmaeya76Z3NEYzJqaYaWaCqC1BG62PUhPaw&usqp=CAU";
        String endereco = "https://www.google.com.br/maps/place/23%C2%B032'03.9%22S+47%C2%B011'32.4%22W/@-23.534406,-47.1928892,19z/data=!3m1!4b1!4m6!3m5!1s0x0:0x0!7e2!8m2!3d-23.5344059!4d-47.1923419";

       // Intent ligar = new Intent( Intent.ACTION_DIAL, Uri.parse(celular) );
       // Intent ligar = new Intent( Intent.ACTION_VIEW, Uri.parse(imagem) );
       // Intent ligar = new Intent( Intent.ACTION_VIEW, Uri.parse(endereco) );

        Intent email = new Intent( Intent.ACTION_SEND );

        email.putExtra( Intent.EXTRA_EMAIL, new String[]{"atm@consultoria.com"} );
        email.putExtra( Intent.EXTRA_SUBJECT, "Contato pelo App" );
        email.putExtra( Intent.EXTRA_TEXT, "Mensagem automática" );

        email.setType("message/rfc822");
        //email.setType("text/plain");
        //email.setType("image/*");
        //email.setType("application/pdf");

        startActivity( Intent.createChooser( email, "Escolha um App de E-mail: " ) );
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}