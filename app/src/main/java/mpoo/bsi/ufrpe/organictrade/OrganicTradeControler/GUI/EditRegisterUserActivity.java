package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class EditRegisterUserActivity extends AppCompatActivity {
    private UserNegocio userNegocio = new UserNegocio();

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setContext(getBaseContext());
        setContentView(R.layout.activity_edit_register_user);

        final EditText name = (EditText)findViewById(R.id.editUserEdtName);
        final EditText pass = (EditText)findViewById(R.id.editUserEdtPassword);
        final EditText email = (EditText)findViewById(R.id.editUserEdtEmail);
        final EditText number = (EditText)findViewById(R.id.editUserEdtPhone);

        name.setText(Session.getCurrentUser().getName());
        pass.setText(Session.getCurrentUser().getPassword());
        email.setText(Session.getCurrentUser().getEmail());
        number.setText(Session.getCurrentUser().getPhone());

        Button salvar = (Button)findViewById(R.id.editUserBtnedit);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                if(userNegocio.editOk(name,pass,email,number)) {
                    User user = new User();
                    user.setUserName(Session.getCurrentUser().getUserName());
                    user.setName(name.getText().toString());
                    user.setPassword(pass.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setPhone(number.getText().toString());

                    UserPersistence userPersistence = new UserPersistence();
                    userPersistence.userEdit(user);

                    Intent i = new Intent(Session.getContext(), UserActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
