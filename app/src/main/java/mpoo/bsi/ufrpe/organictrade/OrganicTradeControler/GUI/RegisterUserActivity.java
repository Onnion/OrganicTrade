package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterUserActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private UserNegocio userNegocio = new UserNegocio();

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Session.setContext(getBaseContext());
    }

    public void cadastrarUsuario(View v){
        UserPersistence crud = new UserPersistence();

        EditText nome = (EditText)findViewById(R.id.registerUserEdtName);
        EditText user = (EditText)findViewById(R.id.registerUserEdtLogin);
        EditText pass = (EditText)findViewById((R.id.registerUserEdtPassword));
        EditText rpass = (EditText)findViewById(R.id.registerUserEdtRepeatPassword);
        EditText email = (EditText)findViewById(R.id.registerUserEdtEmail);
        EditText phone = (EditText)findViewById(R.id.registerUserEdtPhone);

        String nomeString = nome.getText().toString();
        String userString = user.getText().toString();
        String passString = pass.getText().toString();
        String rpassString = rpass.getText().toString();
        String remail = email.getText().toString();
        String phoneString = phone.getText().toString();

        if (!passString.equals(rpassString)){
            Toast.makeText(RegisterUserActivity.this,getText(R.string.tstPasswordDontMatch), Toast.LENGTH_LONG).show();
        }else{
            if(userNegocio.registerOK(email,user,nome,pass,phone,rpass)){
                User usuario = new User();
                usuario.setUserName(userString);
                usuario.setPassword(passString);
                usuario.setEmail(remail);
                usuario.setName(nomeString);
                usuario.setPhone(phoneString);

                crud.RegisterUser(usuario);

                Intent i = new Intent(Session.getContext(),LoginActivity.class);
                startActivity(i);
            }
        }
    }
}
