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
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Usuario;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.UsuarioNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UsuarioPersistencia;
import mpoo.bsi.ufrpe.organictrade.R;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        Session.setContext(getBaseContext());
    }

    public void cadastrarUsuario(View v){
        UsuarioPersistencia crud = new UsuarioPersistencia();

        EditText nome = (EditText)findViewById(R.id.cadastroEtNome);
        EditText user = (EditText)findViewById(R.id.cadastroEtUsername);
        EditText pass = (EditText)findViewById((R.id.cadastroEtPassword));
        EditText rpass = (EditText)findViewById(R.id.cadastroEtRepeatPass);
        EditText email = (EditText)findViewById(R.id.cadastroEdtEmail);

        String nomeString = nome.getText().toString();
        String userString = user.getText().toString();
        String passString = pass.getText().toString();
        String rpassString = rpass.getText().toString();
        String remail = email.getText().toString();

        if (!passString.equals(rpassString)){
            Toast.makeText(CadastroUsuarioActivity.this,getText(R.string.senhasnaoconferem), Toast.LENGTH_LONG).show();
        }else{
            if(UsuarioNegocio.verificacaoCadastro(userString,passString,rpassString,remail,nomeString)) {
                Usuario usuario = new Usuario();
                usuario.setUname(userString);
                usuario.setPass(passString);
                usuario.setEmail(remail);
                usuario.setNome(nomeString);

                crud.inserirUsuario(usuario);

                Intent i = new Intent(Session.getContext(),LoginActivity.class);
                startActivity(i);
            }
        }
    }
}
