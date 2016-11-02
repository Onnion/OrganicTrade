package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.UsuarioNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UsuarioPersistencia;
import mpoo.bsi.ufrpe.organictrade.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Session.setContext(getBaseContext());
    }

    public void logar(View v){

        UsuarioPersistencia crud =  new UsuarioPersistencia();

        EditText user = (EditText)findViewById(R.id.mainEdUsername);
        EditText pass = (EditText)findViewById((R.id.mainEtPassword));

        String userString = user.getText().toString();
        String passString = pass.getText().toString();

        if(UsuarioNegocio.verificacaoLogin(userString,passString)){
            if(crud.buscarELogarUsuario(userString,passString)){
                Toast.makeText(Session.getContext(),"Ola "+Session.getUserAtual().getName(), Toast.LENGTH_LONG).show();
                Intent j = new Intent(Session.getContext(), UsuarioActivity.class);
                startActivity(j);
                finish();
            }else{
                Toast.makeText(Session.getContext(),getText(R.string.loginsenhaInvalido) , Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(Session.getContext(),getText(R.string.loginsenhaInvalido) , Toast.LENGTH_LONG).show();
        }
    }

    public void toCadastro(View v){
        Intent i = new Intent(Session.getContext(), CadastroUsuarioActivity.class);
        startActivity(i);
    }
}