package mpoo.bsi.ufrpe.organictrade.controler.user.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.Md5;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterUserActivity extends AppCompatActivity {

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
        callRegisterUser();
    }

    private void callRegisterUser() {
        Button button = (Button)findViewById(R.id.registerUserBtnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        EditText nome = (EditText)findViewById(R.id.registerUserEdtName);
        EditText user = (EditText)findViewById(R.id.registerUserEdtLogin);
        EditText pass = (EditText)findViewById((R.id.registerUserEdtPassword));
        EditText rpass = (EditText)findViewById(R.id.registerUserEdtRepeatPassword);
        EditText email = (EditText)findViewById(R.id.registerUserEdtEmail);
        EditText phone = (EditText)findViewById(R.id.registerUserEdtPhone);
        UserValidation userValidation = new UserValidation();
        UserNegocio userNegocio = new UserNegocio();
        if( userValidation.validateRegister(nome,user,pass,rpass,email,phone)){
            if(userNegocio.validateRegister(user.getText().toString())) {
                User userLocal = new User();
                userLocal.setName(nome.getText().toString());
                userLocal.setPassword(Md5.encrypt(pass.getText().toString()));
                userLocal.setUserName(user.getText().toString());
                userLocal.setEmail(email.getText().toString());
                userLocal.setPhone(Long.parseLong(phone.getText().toString()));
                userNegocio.register(userLocal);
                Toast.makeText(Session.getContext(),getText(R.string.tstSuccessfullyRegistered),Toast.LENGTH_LONG).show();
                Intent j = new Intent(Session.getContext(), LoginActivity.class);
                startActivity(j);
                finish();
            }else{
                Toast.makeText(Session.getContext(),getText(R.string.tstUnavaliableLogin),Toast.LENGTH_LONG).show();
            }
        }

    }
}