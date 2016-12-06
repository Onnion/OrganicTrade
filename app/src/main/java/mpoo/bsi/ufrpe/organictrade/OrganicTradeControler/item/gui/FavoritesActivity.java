package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.gui.UserActivity;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoritesActivity extends AppCompatActivity {
    private ImageView addBtn;
    private ArrayList<Product> products;
    private FavoriteListAdapter adapter;

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
        setContentView(R.layout.activity_favorites);
        initializeGridView();
        loadAddBtn();
    }

    private void initializeGridView(){
        UserPersistence userPersistence = new UserPersistence();
        products = userPersistence.getFavorites();
        adapter = new FavoriteListAdapter(products);
        GridView gridView = (GridView) findViewById(R.id.favoritesGridViewListProduct);
        gridView.setAdapter(adapter);
        registerForContextMenu(gridView);
    }

    private void loadAddBtn() {
        initializeAddBtn();
        setFunctionAddBtn();
    }

    private void setFunctionAddBtn() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),RegisterFavoritesActivity.class);
                startActivity(p);
            }
        });
    }

    private void initializeAddBtn() {
        addBtn = (ImageView) findViewById(R.id.favoritesImgToRegisterFavorites);
    }
}
