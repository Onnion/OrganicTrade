package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class LoginActivity extends AppCompatActivity {
    private long lastBackPressTime = 0;
    private Toast toast;
    private UserNegocio userNegocio = new UserNegocio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Session.setContext(getBaseContext());
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    public void logar(View v){

        UserPersistence crud =  new UserPersistence();

        EditText user = (EditText)findViewById(R.id.loginEdtLogin);
        EditText pass = (EditText)findViewById((R.id.loginEdtPassword));

        String userString = user.getText().toString();
        String passString = pass.getText().toString();

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

    public void toCadastro(View v){
        Intent i = new Intent(Session.getContext(), RegisterUserActivity.class);
        startActivity(i);
    }
}