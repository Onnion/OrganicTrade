package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItensDeTenda;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Tenda;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItemListAdapter;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Usuario;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TendaPersistencia;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UsuarioPersistencia;
import mpoo.bsi.ufrpe.organictrade.R;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Session.setContext(getBaseContext());
        TextView text = (TextView)findViewById(R.id.usuarioTextName);

        String[] nome = Session.getUserAtual().getNome().split(" ");
        text.setText(nome[0]);

        //-----------------------------------PopularLisView------------------------------------//
        //--------------------------------------Instacias------------------------------------//
        //------------------------------OnClickLisView------------------------------------//
        final ListView listaDeItens = (ListView) findViewById(R.id.usuarioListViewList);
        listaDeItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ItensDeTenda item = (ItensDeTenda)listaDeItens.getAdapter().getItem(position);
                Toast.makeText(Session.getContext(),item.getNomeProduto(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        //-------------------------------------------------------------------------------//
        TendaPersistencia tendaPersistencia = new TendaPersistencia();
        Tenda tenda = tendaPersistencia.retornarTendaDoUsuario();
        List<ItensDeTenda> tendaFinal = tenda.getItensDeTendas();
        //---------------------------------------------------------------------------------//
        ItemListAdapter adapter = new ItemListAdapter(tendaFinal);
        listaDeItens.setAdapter(adapter);
        //-------------------------------------------------------------------------------------//

        ImageButton addBtn = (ImageButton) findViewById(R.id.usuarioImgButnToCadastro);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),CadastroProdutoActivity.class);
                startActivity(p);
            }
        });

        ImageButton searchBtn =(ImageButton) findViewById(R.id.usuarioImgButnToSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),PesquisarProdutosActivity.class);
                startActivity(p);
            }
        });
    }

    public void sair(View v){
        UsuarioPersistencia crud = new UsuarioPersistencia();
        crud.deslogarUsuario();
        Session.setUserAtual(new Usuario());
        Intent p = new Intent(Session.getContext(),LoginActivity.class);
        startActivity(p);

    }
}