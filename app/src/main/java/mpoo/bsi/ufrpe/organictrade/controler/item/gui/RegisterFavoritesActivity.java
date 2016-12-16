package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.ProductNegocio;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterFavoritesActivity extends AppCompatActivity {
    private ArrayList<Product> listProducts;
    private FavoriteToRegisterListAdapter adapter;
    private GridView listView;
    private ArrayList<Product> selecionados = new ArrayList<>();

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Session.getContext(),FavoritesActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setContext(getBaseContext());
        setContentView(R.layout.activity_register_favorites);
        initTentList();
        callRegisterItens();
    }

    private void setFunctionFavoriteBtn(){
        listView = (GridView) findViewById(R.id.registerFavoritesListViewListProduct);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox)view.findViewById(R.id.registerProductCheckBox);
                Product product = (Product)listView.getAdapter().getItem(position);
                if(!selecionados.contains(product)){
                    selecionados.add(product);
                    checkBox.setChecked(true);
                }else{
                    selecionados.remove(product);
                    checkBox.setChecked(false);
                }
            }
        });
    }

    private void initTentList(){
        listView = (GridView) findViewById(R.id.registerFavoritesListViewListProduct);
        ProductNegocio productNegocio = new ProductNegocio();
        listProducts = productNegocio.productPersistence().getAllProducts();
        adapter = new FavoriteToRegisterListAdapter(listProducts,selecionados);
        listView.setAdapter(adapter);
        setFunctionFavoriteBtn();
    }

    private void callRegisterItens(){
        final UserNegocio userNegocio = new UserNegocio();
        Button button = (Button)findViewById(R.id.registerFavoriteBtnSaveFavorites);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            userNegocio.getUserPersistence().registerFavorites(selecionados);
            Intent i = new Intent(Session.getContext(),FavoritesActivity.class);
            startActivity(i);
            finish();
            }
        });
    }
}