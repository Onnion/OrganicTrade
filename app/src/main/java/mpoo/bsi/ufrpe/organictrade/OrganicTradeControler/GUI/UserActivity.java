package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItemListAdapter;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Session.setContext(getBaseContext());
        TextView text = (TextView)findViewById(R.id.userTextName);

        String[] nome = Session.getUserAtual().getName().split(" ");
        text.setText(nome[0]);

        //-----------------------------------PopularLisView------------------------------------//
        //--------------------------------------Instacias------------------------------------//
        //------------------------------OnClickLisView------------------------------------//
        final ListView listaDeItens = (ListView) findViewById(R.id.usuarioListViewList);
        listaDeItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TentItems item = (TentItems)listaDeItens.getAdapter().getItem(position);
                Toast.makeText(Session.getContext(),item.getProdutoId(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        //-------------------------------------------------------------------------------//
        TentPersistence tentPersistence = new TentPersistence();
        Tent tent = tentPersistence.retornarTendaDoUsuario();
        List<TentItems> tendaFinal = tent.getTent();
        //---------------------------------------------------------------------------------//
        ItemListAdapter adapter = new ItemListAdapter(tendaFinal);
        listaDeItens.setAdapter(adapter);
        //-------------------------------------------------------------------------------------//

        ImageView addBtn = (ImageView) findViewById(R.id.userImgBtnToCadastro);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),RegisterTentItemActivity.class);
                startActivity(p);
            }
        });

        ImageView searchBtn =(ImageView) findViewById(R.id.userImgBtnToSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),SearchProductsActivity.class);
                startActivity(p);
            }
        });
        ImageView logoutBtn =(ImageView) findViewById(R.id.userImgBtnLogout);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPersistence crud = new UserPersistence();
                crud.deslogarUsuario();
                Session.setUserAtual(new User());
                Intent p = new Intent(Session.getContext(),LoginActivity.class);
                startActivity(p);
                finish();
            }
        });
    }
}