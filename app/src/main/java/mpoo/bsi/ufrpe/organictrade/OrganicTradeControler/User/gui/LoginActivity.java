package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.User.gui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.User.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.User.persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class LoginActivity extends AppCompatActivity {
    private long lastBackPressTime = 0;
    private EditText user;
    private EditText pass;
    private String userString;
    private String passString;
    private Toast toast;
    private UserNegocio userNegocio = new UserNegocio();

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
        UserPersistence crud =  new UserPersistence();
        loadValuesToLogin();
        if(userNegocio.loginOK(user,pass)){
            if(crud.searchAndLoginUser(userString,passString)){
                Intent j = new Intent(Session.getContext(), UserActivity.class);
                startActivity(j);
                finish();
            }else{
                Toast.makeText(Session.getContext(),getText(R.string.tstInvalidLoginAndPassword) , Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(Session.getContext(),getText(R.string.tstInvalidLoginAndPassword) , Toast.LENGTH_LONG).show();
        }
    }

    private void loadValuesToLogin() {
        user = (EditText)findViewById(R.id.loginEdtLogin);
        pass = (EditText)findViewById((R.id.loginEdtPassword));
        userString = user.getText().toString();
        passString = pass.getText().toString();
    }

    public void register(){
        Intent i = new Intent(Session.getContext(), RegisterUserActivity.class);
        startActivity(i);
    }
}