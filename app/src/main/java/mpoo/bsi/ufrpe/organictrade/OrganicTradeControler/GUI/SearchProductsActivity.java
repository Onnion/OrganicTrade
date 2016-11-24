package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItemListAdapter;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class SearchProductsActivity extends AppCompatActivity {
    private ProductPersistence productPersistence = new ProductPersistence();
    List<TentItems> listItems;
    ItemListAdapter adapter;
    ListView listView;
    EditText editText;
    UserPersistence userPersistence = new UserPersistence();

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Session.getContext(),UserActivity.class);
        startActivity(i);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        Session.setContext(getBaseContext());
        setFunctionItemOfListView();
        initList();
        initializeSearchFunctions();
    }

    private void initializeSearchFunctions() {
        editText=(EditText)findViewById(R.id.searchProductsEdtSearch);
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

    private void setFunctionItemOfListView() {
        listView=(ListView)findViewById(R.id.searchProductsListViewSearch);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Session.getContext(),"Mantenha o item pressionado para mais detalhes",Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                TentItems item = (TentItems)listView.getAdapter().getItem(position);
                Session.setItemSelected(item);
                Session.setContactSelected(userPersistence.searchFromId(item.getUser_id()));
                Intent p = new Intent(Session.getContext(),ContactActivity.class);
                startActivity(p);
                return false;
            }
        });
    }

    public void searchItem(String textToSearch){
        for(int i = 0; i < listItems.size(); i++){
            if(!productPersistence.nameProductById(listItems.get(i).getProductId()).contains(textToSearch)){
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