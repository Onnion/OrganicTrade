package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.ProductNegocio;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.TentItemNegocio;
import mpoo.bsi.ufrpe.organictrade.controler.user.gui.UserActivity;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;
import mpoo.bsi.ufrpe.organictrade.recommendationsystem.SlopeOne;

public class SearchProductsActivity extends AppCompatActivity {
    private ProductNegocio productNegocio = new ProductNegocio();
    private List<TentItem> listTent;
    private ItemListAdapter adapter;
    private ListView listView;
    private UserNegocio userNegocio = new UserNegocio();

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
        loadRecomendation();
    }

    private void initializeSearchFunctions() {
        EditText editText = (EditText)findViewById(R.id.searchProductsEdtSearch);
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
                TentItem item = (TentItem)listView.getAdapter().getItem(position);
                Session.setItemSelected(item);
                Session.setTentSelected(item.getTent());
                Session.setContactSelected(userNegocio.searchFromId(item.getTent().getUser().getIdUser()));
                Intent p = new Intent(Session.getContext(),TentSelectedActivity.class);
                startActivity(p);
                return false;
            }
        });
    }

    public void searchItem(String textToSearch){
        for(int i = 0; i < listTent.size(); i++){
            if(!productNegocio.nameProductById(listTent.get(i).getProduct().getProductId()).contains(textToSearch)){
                listTent.remove(listTent.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initTentList(){
        TentItemNegocio tentItemNegocio = new TentItemNegocio();
        listTent = tentItemNegocio.getAllItems(Session.getCurrentUser().getIdUser());
        adapter = new ItemListAdapter(listTent);
        listView.setAdapter(adapter);
    }

    private void loadRecomendation() {
        List<Product> recomendation =  SlopeOne.getRecomendation();
        GridView gridView = (GridView) findViewById(R.id.searchProductsGridRecomendation);
        RecomendationListAdapter adapter = new RecomendationListAdapter(recomendation);
        gridView.setAdapter(adapter);
    }
}