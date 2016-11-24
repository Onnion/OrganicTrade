package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.UserLocation;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterUserActivity extends AppCompatActivity {
    private UserNegocio userNegocio = new UserNegocio();
    private EditText nome;
    private EditText user;
    private EditText pass;
    private EditText rpass;
    private EditText email;
    private EditText phone;
    private String nomeString;
    private String userString;
    private String passString;
    private String rpassString;
    private String remail;
    private String phoneString;
    private UserPersistence crud;
    private UserLocation userLocation;

    @Override
    public void onBackPressed() {
        Intent j = new Intent(Session.getContext(), LoginActivity.class);
        startActivity(j);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Session.setContext(getBaseContext());
        userLocation = new UserLocation();
        callRegisterUser();
    }

    private void callRegisterUser() {
        Button button = (Button)findViewById(R.id.registerUserBtnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationRegisterUser();
            }
        });
    }

    public void validationRegisterUser(){
        loadValuesToRegister();
        crud = new UserPersistence();
        if (!passString.equals(rpassString)){
            Toast.makeText(Session.getContext(),getText(R.string.tstPasswordDontMatch), Toast.LENGTH_LONG).show();
        }else{
            if(crud.userNotRegistered(userString) && userNegocio.registerOK(email,user,nome,pass,phone,rpass)){
                registerUser();
                Intent i = new Intent(Session.getContext(),LoginActivity.class);
                startActivity(i);
            }
        }
    }

    private void registerUser() {
        User usuario = new User();
        usuario.setUserName(userString);
        usuario.setPassword(passString);
        usuario.setEmail(remail);
        usuario.setName(nomeString);
        usuario.setPhone(phoneString);
        usuario.setAdress(userLocation.getAdress());
        crud.RegisterUser(usuario);
    }

    private void loadValuesToRegister() {
        nome = (EditText)findViewById(R.id.registerUserEdtName);
        user = (EditText)findViewById(R.id.registerUserEdtLogin);
        pass = (EditText)findViewById((R.id.registerUserEdtPassword));
        rpass = (EditText)findViewById(R.id.registerUserEdtRepeatPassword);
        email = (EditText)findViewById(R.id.registerUserEdtEmail);
        phone = (EditText)findViewById(R.id.registerUserEdtPhone);
        nomeString = nome.getText().toString();
        userString = user.getText().toString();
        passString = pass.getText().toString();
        rpassString = rpass.getText().toString();
        remail = email.getText().toString();
        phoneString = phone.getText().toString();
    }
}
