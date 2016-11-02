package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterTentItemActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tent_item);
        Session.setContext(getBaseContext());
    }

    public void cadastrarProduto(View v) {
        TentItemsPersistence crud = new TentItemsPersistence();


        Spinner nameProduct = (Spinner) findViewById(R.id.registerTentItemSpiProductName);
        EditText unidade = (EditText) findViewById(R.id.registerTentItemEdtProductUnity);
        EditText quantidade = (EditText) findViewById((R.id.registerTentItemEdtProductAmount));
        EditText price = (EditText) findViewById(R.id.registerTentItemEdtProductPrice);

        String nameProductString = nameProduct.getSelectedItem().toString();
        String unidadeString = unidade.getText().toString();
        String quantidadeString = quantidade.getText().toString();
        String priceString = price.getText().toString();

        //validação

        TentItems tentItems = new TentItems();
        tentItems.setProdutoId(unidadeString);
        tentItems.setQuantidadeAtual(quantidadeString);
        tentItems.setValor(priceString);
        tentItems.setProdutoId(nameProductString);
        tentItems.setUsurio_id(Session.getUserAtual().getId_user());

        crud.inserirItensDeTenda(tentItems);

        Intent i = new Intent(Session.getContext(), UserActivity.class);
        startActivity(i);

    }
}