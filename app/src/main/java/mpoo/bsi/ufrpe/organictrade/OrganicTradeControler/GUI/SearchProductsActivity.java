package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import mpoo.bsi.ufrpe.organictrade.R;

public class SearchProductsActivity extends AppCompatActivity {
//    private SQLiteDatabase db;
//    private DatabaseHelper banco = Session.getDbAtual();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_products);
//        Session.setContext(getBaseContext());
//
//        final GridView listaDeItens = (GridView) findViewById(R.id.searchProductsGridItens);
//        listaDeItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                TentItems item = (TentItems)listaDeItens.getAdapter().getItem(position);
//                Toast.makeText(Session.getContext(),item.getProdutoId(), Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });
//        //-------------------------------------------------------------------------------//
//        TentPersistence tentPersistence = new TentPersistence();
//        Tent tent = tentPersistence.retornarTendaDosUsuarios();
//        List<TentItems> tendaFinal = tent.getTent();
//        //---------------------------------------------------------------------------------//
//        ItemListAdapter adapter = new ItemListAdapter(tendaFinal);
//        listaDeItens.setAdapter(adapter);
//        //-------------------------------------------------------------------------------------//
//

    String[] names;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);
        listView=(ListView)findViewById(R.id.listview);
        editText=(EditText)findViewById(R.id.searchProductsEdtSearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    // reset listview
                    initList();
                }
                else{
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void searchItem(String textToSearch){
        for(String name: names){
            if(!name.contains(textToSearch)){
                listItems.remove(name);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        names =new String[]{"Banana","Manga","Uva","Maçã"};
        listItems=new ArrayList<>(Arrays.asList(names));
        adapter=new ArrayAdapter<String>(this,
                R.layout.list_item, R.id.listItemTextName, listItems);
        listView.setAdapter(adapter);

    }
}
