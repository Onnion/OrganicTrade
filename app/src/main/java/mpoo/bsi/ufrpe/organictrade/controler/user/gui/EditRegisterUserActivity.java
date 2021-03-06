package mpoo.bsi.ufrpe.organictrade.controler.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class EditRegisterUserActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText number;

    @Override
    public final void onBackPressed() {
        Session.setItemSelected(null);
        Intent i = new Intent(Session.getContext(),UserActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
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
                editRegisterOfUser();
            }
        });
    }

    private void editRegisterOfUser() {
        UserValidation userValidation = new UserValidation();
        if (userValidation.validateEdit(name,email,number)) {
            User userToEdit = new User();

            userToEdit.setUserName(Session.getCurrentUser().getUserName());
            userToEdit.setPassword(Session.getCurrentUser().getPassword());

            userToEdit.setName(name.getText().toString());
            userToEdit.setEmail(email.getText().toString());
            userToEdit.setPhone(Long.parseLong(number.getText().toString()));
            UserNegocio userNegocio = new UserNegocio();
            userNegocio.edit(userToEdit);
            Intent i = new Intent(Session.getContext(), UserActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void loadValuesOfCurrentUserToEdit() {
        name = (EditText)findViewById(R.id.editUserEdtName);
        email = (EditText)findViewById(R.id.editUserEdtEmail);
        number = (EditText)findViewById(R.id.editUserEdtPhone);
        name.setText(Session.getCurrentUser().getName());
        email.setText(Session.getCurrentUser().getEmail());
        number.setText(Session.getCurrentUser().getPhone().toString());
    }
}
