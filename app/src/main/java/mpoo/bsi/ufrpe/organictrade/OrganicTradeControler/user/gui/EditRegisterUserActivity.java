package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.negocio.Md5;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class EditRegisterUserActivity extends AppCompatActivity {
    private UserNegocio userNegocio = new UserNegocio();
    private User userToEdit = new User();
    private EditText name;
    private EditText email;
    private EditText number;

    @Override
    public void onBackPressed() {
        Session.setItemSelected(null);
        Intent i = new Intent(Session.getContext(),UserActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setContext(getBaseContext());
        setContentView(R.layout.activity_edit_register_user);
        loadValuesOfCurrentUserToEdit();
        callEditRegisterOfUser();
    }

    private void callEditRegisterOfUser() {
        Button saveEdition = (Button)findViewById(R.id.editUserBtnedit);
        saveEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userNegocio.editOk(name,email,number)) {
                    editRegisterOfUser();
                }
            }
        });
    }

    private void editRegisterOfUser() {
        userToEdit.setUserName(Session.getCurrentUser().getUserName());
        userToEdit.setName(name.getText().toString());
        userToEdit.setEmail(email.getText().toString());
        userToEdit.setPhone(number.getText().toString());
        UserPersistence userPersistence = new UserPersistence();
        userPersistence.userEdit(userToEdit);
        Intent i = new Intent(Session.getContext(), UserActivity.class);
        startActivity(i);
        finish();
    }

    private void loadValuesOfCurrentUserToEdit() {
        name = (EditText)findViewById(R.id.editUserEdtName);
        email = (EditText)findViewById(R.id.editUserEdtEmail);
        number = (EditText)findViewById(R.id.editUserEdtPhone);
        name.setText(Session.getCurrentUser().getName());
        email.setText(Session.getCurrentUser().getEmail());
        number.setText(Session.getCurrentUser().getPhone());
    }
}
