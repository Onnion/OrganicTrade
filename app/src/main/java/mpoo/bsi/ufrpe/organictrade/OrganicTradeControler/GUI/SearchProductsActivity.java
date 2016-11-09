package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItemListAdapter;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class SearchProductsActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private ProductPersistence productPersistence = new ProductPersistence();
    List<TentItems> listItems;
    ItemListAdapter adapter;
    ListView listView;
    EditText editText;
    UserPersistence userPersistence = new UserPersistence();
    Intent p = new Intent(Session.getContext(),ContactActivity.class);

    @Override
    public void onBackPressed() {
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        Session.setContext(getBaseContext());

        listView=(ListView)findViewById(R.id.searchProductsListViewSearch);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TentItems item = (TentItems)listView.getAdapter().getItem(position);
                Session.setItemSelected(item);
                Session.setContactSelected(userPersistence.buscarApartirDoId(item.getUsurio_id()));
                startActivity(p);
                return false;
                }
            });
        editText=(EditText)findViewById(R.id.searchProductsEdtSearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                }
                else{
                    searchItem(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void searchItem(String textToSearch){
        for(int i = 0; i < listItems.size(); i++){
            if(!productPersistence.nameProductById(listItems.get(i).getProdutoId()).contains(textToSearch)){
                listItems.remove(listItems.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        TentPersistence tentPersistence = new TentPersistence();
        Tent tent = tentPersistence.retornarTendaDosUsuarios();
        listItems = tent.getTent();
        adapter = new ItemListAdapter(listItems);
        listView.setAdapter(adapter);
    }
}