package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Product;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterTentItemActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private Spinner nameProduct;
    private ProductPersistence productPersistence = new ProductPersistence();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tent_item);
        Session.setContext(getBaseContext());
        String[] lista = productPersistence.getNameProducts();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameProduct = (Spinner) findViewById(R.id.registerTentItemSpiProductName);
        nameProduct.setAdapter(adapter);
        //-------------------------------------------------------------//
    }

    public void cadastrarProduto(View v) {
        TentItemsPersistence crud = new TentItemsPersistence();

        EditText unidade = (EditText) findViewById(R.id.registerTentItemEdtProductUnity);
        EditText quantidade = (EditText) findViewById((R.id.registerTentItemEdtProductAmount));
        EditText price = (EditText) findViewById(R.id.registerTentItemEdtProductPrice);

        String idProductString = productPersistence.idProductByName(nameProduct.getSelectedItem().toString());
        Toast.makeText(Session.getContext(),productPersistence.idProductByName(nameProduct.getSelectedItem().toString()), Toast.LENGTH_LONG).show();

        String unidadeString = unidade.getText().toString();
        String quantidadeString = quantidade.getText().toString();
        String priceString = price.getText().toString();

        //validação

        TentItems tentItems = new TentItems();
        tentItems.setProdutoId(unidadeString);
        tentItems.setQuantidadeAtual(quantidadeString);
        tentItems.setValor(priceString);
        tentItems.setProdutoId(idProductString);
        tentItems.setUsurio_id(Session.getUserAtual().getId_user());
        crud.inserirItensDeTenda(tentItems);
        Intent i = new Intent(Session.getContext(), UserActivity.class);
        startActivity(i);
    }
}