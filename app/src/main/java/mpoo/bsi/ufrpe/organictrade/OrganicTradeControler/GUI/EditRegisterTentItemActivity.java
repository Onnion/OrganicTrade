package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class EditRegisterTentItemActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private Spinner unityProduct;
    private EditText amount;
    private EditText price;
    private TentItemsPersistence tentItemsPersistence;
    @Override
    public void onBackPressed() {
        Session.setItemSelected(null);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register_tent_item);
        Session.setContext(getBaseContext());
        //-------------------------------------------------------------//
        unityProduct = (Spinner) findViewById(R.id.editRegisterTentItemEdtProductUnity);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.SpnItems, android.R.layout.simple_spinner_dropdown_item);
        unityProduct.setAdapter(adapter1);

        amount = (EditText) findViewById((R.id.editRegisterTentItemEdtProductAmount));
        price = (EditText) findViewById(R.id.editRegisterTentItemEdtProductPrice);
        amount.setText(Session.getItemSelected().getCurrentAmount());
        price.setText(Session.getItemSelected().getValue());

        final Button button = (Button)findViewById(R.id.editRegisterTentItemBtnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditRegisterProduct(button);
            }
        });
    }

    public void EditRegisterProduct(View v) {
        TentItems tentItems = new TentItems();
        tentItems.setProductId(Session.getItemSelected().getProductId());
        tentItems.setTentItems_id(Session.getItemSelected().getTentItems_id());
        tentItems.setUser_id(Session.getItemSelected().getUser_id());

        tentItems.setUnity(unityProduct.getSelectedItem().toString());
        tentItems.setCurrentAmount(amount.getText().toString());
        tentItems.setValue(price.getText().toString());
        tentItemsPersistence = new TentItemsPersistence();
        tentItemsPersistence.tentItemEdit(tentItems);
        Intent i = new Intent(Session.getContext(), UserActivity.class);
        startActivity(i);
        finish();
    }
}
