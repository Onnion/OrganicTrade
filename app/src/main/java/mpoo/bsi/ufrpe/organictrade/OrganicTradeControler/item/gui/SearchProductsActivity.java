package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

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

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.gui.TentSelectedActivity;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.gui.UserActivity;
import mpoo.bsi.ufrpe.organictrade.R;

public class SearchProductsActivity extends AppCompatActivity {
    private ProductPersistence productPersistence = new ProductPersistence();
    ArrayList<TentItems> listTent;
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
        initTentList();
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
                    initTentList();
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
                Session.setTentSelected(item.getTent());
                Session.setContactSelected(userPersistence.searchFromId(item.getTent().getUser().getId_user()));
                Intent p = new Intent(Session.getContext(),TentSelectedActivity.class);
                startActivity(p);
                return false;
            }
        });
    }

    public void searchItem(String textToSearch){
        for(int i = 0; i < listTent.size(); i++){
            if(!productPersistence.nameProductById(listTent.get(i).getProduct().getProductId()).contains(textToSearch)){
                listTent.remove(listTent.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initTentList(){
        TentItemsPersistence tentItemsPersistence = new TentItemsPersistence();
        listTent = tentItemsPersistence.getAllItems(Session.getCurrentUser().getId_user());
        adapter = new ItemListAdapter(listTent);
        listView.setAdapter(adapter);
    }
}