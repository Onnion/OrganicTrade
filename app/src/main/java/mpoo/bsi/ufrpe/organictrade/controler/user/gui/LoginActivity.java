package mpoo.bsi.ufrpe.organictrade.controler.user.gui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.Md5;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private long lastBackPressTime = 0;
    private Toast toast;

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this , R.string.tstPressAgainToCloseApp , Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Session.setContext(getBaseContext());
        callLogin();
        callRegister();
    }

    private void callRegister(){
        Button button = (Button)findViewById(R.id.loginBtnToCadastro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void callLogin() {
        Button button = (Button)findViewById(R.id.loginBtnEntrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        UserValidation userValidation = new UserValidation();
        UserNegocio userNegocio = new UserNegocio();
        EditText username = (EditText) findViewById(R.id.loginEdtLogin);
        EditText password = (EditText) findViewById(R.id.loginEdtPassword);
        if(userValidation.validateLogin(username,password)){
            User userLocal = new User();
            userLocal.setUserName(username.getText().toString());
            userLocal.setPassword(password.getText().toString());
            if(userNegocio.login(userLocal)){
                Intent i = new Intent(Session.getContext(), UserActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(Session.getContext(),getText(R.string.tstInvalidLoginAndPassword),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void register(){
        Intent i = new Intent(Session.getContext(), RegisterUserActivity.class);
        startActivity(i);
    }

}