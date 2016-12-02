package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoritesActivity extends AppCompatActivity {
    private ArrayList<Product> listProducts;
    private FavoriteListAdapter adapter;
    private GridView listView;
    private ArrayList<Product> selecionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setContext(getBaseContext());
        setContentView(R.layout.activity_favorites);
        initTentList();
    }

    private void setFunctionFavoriteBtn(){
        listView = (GridView) findViewById(R.id.favoritesListViewListProduct);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox)view.findViewById(R.id.productCheckBox);
                Product product = (Product)listView.getAdapter().getItem(position);
                if(!selecionados.contains(product)){
                    selecionados.add(product);
                }else{
                    selecionados.remove(product);
                }
                if (selecionados.contains(product)){
                    checkBox.setChecked(true);
                }else{
                    checkBox.setChecked(false);
                }
            }
        });
    }

    public void initTentList(){
        listView = (GridView) findViewById(R.id.favoritesListViewListProduct);
        ProductPersistence productPersistence = new ProductPersistence();
        listProducts = productPersistence.getAllProducts();
        adapter = new FavoriteListAdapter(listProducts,selecionados);
        listView.setAdapter(adapter);
        setFunctionFavoriteBtn();

    }
}