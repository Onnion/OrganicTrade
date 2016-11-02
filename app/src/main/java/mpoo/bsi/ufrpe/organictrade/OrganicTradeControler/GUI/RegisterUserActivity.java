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
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.userNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterUserActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

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

        String nomeString = nome.getText().toString();
        String userString = user.getText().toString();
        String passString = pass.getText().toString();
        String rpassString = rpass.getText().toString();
        String remail = email.getText().toString();

        if (!passString.equals(rpassString)){
            Toast.makeText(RegisterUserActivity.this,getText(R.string.tstPasswordDontMatch), Toast.LENGTH_LONG).show();
        }else{
            if(userNegocio.verificacaoCadastro(userString,passString,rpassString,remail,nomeString)) {
                User usuario = new User();
                usuario.setUserName(userString);
                usuario.setPassword(passString);
                usuario.setEmail(remail);
                usuario.setName(nomeString);

                crud.inserirUsuario(usuario);

                Intent i = new Intent(Session.getContext(),LoginActivity.class);
                startActivity(i);
            }
        }
    }
}
