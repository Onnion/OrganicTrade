package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

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

    private void setFunctionFavoriteBtn(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                ImageView imageView= (ImageView)parent.findViewById(R.id.cardviewImgBtnFavorite);
//                imageView.setImageResource(R.mipmap.ic_favoriteonclick);
                return false;
            }
        });
    }

    public void initTentList(){
        listView = (GridView) findViewById(R.id.favoritesListViewListProduct);
        ProductPersistence productPersistence = new ProductPersistence();
        listProducts = productPersistence.getAllProducts();
        adapter = new FavoriteListAdapter(listProducts);
        listView.setAdapter(adapter);
        setFunctionFavoriteBtn();

    }
}