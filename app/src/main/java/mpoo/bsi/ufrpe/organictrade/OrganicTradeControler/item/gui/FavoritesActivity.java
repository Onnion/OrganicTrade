package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoritesActivity extends AppCompatActivity {
    private ArrayList<Product> listProducts;
    private FavoriteListAdapter adapter;
    private GridView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setContext(getBaseContext());
        setContentView(R.layout.activity_favorites);
        initTentList();

    }

    public void initTentList(){
        listView = (GridView) findViewById(R.id.favoritesListViewListProduct);
        ProductPersistence productPersistence = new ProductPersistence();
        listProducts = productPersistence.getAllProducts();
        adapter = new FavoriteListAdapter(listProducts);
        listView.setAdapter(adapter);
    }
}