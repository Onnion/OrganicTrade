package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ItensDeTendaPersistencia;
import mpoo.bsi.ufrpe.organictrade.R;

public class CadastroProdutoActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        Session.setContext(getBaseContext());
    }

    public void cadastrarProduto(View v) {
        ItensDeTendaPersistencia crud = new ItensDeTendaPersistencia();
        EditText nomeProduto = (EditText) findViewById(R.id.cadastroProdutoEdtNameProduto);
        EditText quantidade = (EditText) findViewById((R.id.cadastroProdutoEdtQuantidade));
        EditText price = (EditText) findViewById(R.id.cadastroProdutoEdtPrice);

        String nomeProduroString = nomeProduto.getText().toString();
        String quantidadetring = quantidade.getText().toString();
        String priceString = price.getText().toString();

        //validação

        TentItems tentItems = new TentItems();
        tentItems.setNomeProduto(nomeProduroString);
        tentItems.setQuantidadeAtual(quantidadetring);
        tentItems.setValor(priceString);
        tentItems.setUsurio_id(Session.getUserAtual().getId_user());

        crud.inserirItensDeTenda(tentItems);

        Intent i = new Intent(Session.getContext(), UsuarioActivity.class);
        startActivity(i);

    }
}