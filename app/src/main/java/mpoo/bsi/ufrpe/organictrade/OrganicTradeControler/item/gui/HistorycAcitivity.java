package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class HistorycAcitivity extends AppCompatActivity {
    private ListView listOfItems;
    private UserPersistence userPersistence = new UserPersistence();
    private ItemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyc_acitivity);
        Session.setContext(getBaseContext());
        populateListView(userPersistence.getSellingHistoryc());
    }

    private void loadListView(){
        listOfItems = (ListView) findViewById(R.id.historicListListView);
    }

    private void populateListView(ArrayList<TentItems> finalTent) {
        loadListView();
        adapter = new ItemListAdapter(finalTent);
        listOfItems.setAdapter(adapter);
    }
}
