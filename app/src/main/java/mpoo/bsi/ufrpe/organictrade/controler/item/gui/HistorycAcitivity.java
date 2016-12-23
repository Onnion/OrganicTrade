package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class HistorycAcitivity extends AppCompatActivity {
    private ListView listOfItems;
    private UserNegocio userNegocio = new UserNegocio();
    private ItemListAdapter adapter;
    private ImageView selling;
    private ImageView buying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyc_acitivity);
        Session.setContext(getBaseContext());
        loadBuyingImg();
        loadSellingImg();
    }

    private void loadBuyingImg(){
        initializeBuyingImg();
        setFunctionBuyingHistorycImg();
    }

    private void loadSellingImg(){
        initializeSellingImg();
        setFunctionSellingHistorycImg();

    }

    private void initializeBuyingImg(){
        buying = (ImageView)findViewById(R.id.historicImgBuying);
        buying.setImageResource(R.mipmap.ic_buying);
    }

    private void initializeSellingImg(){
        selling = (ImageView)findViewById(R.id.historicImgSelling);
        selling.setImageResource(R.mipmap.ic_selling);
    }

    private void setFunctionBuyingHistorycImg(){
        buying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView(userNegocio.getBuyingHistoryc());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setFunctionSellingHistorycImg(){
        selling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView(userNegocio.getSellingHistoryc());
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void loadListView(){
        listOfItems = (ListView) findViewById(R.id.historicListListView);
    }

    private void populateListView(ArrayList<TentItem> finalTent) {
        loadListView();
        adapter = new ItemListAdapter(finalTent);
        listOfItems.setAdapter(adapter);
    }
}
