package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
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
    private long lastBackPressTime = 0;
    private Toast toast;

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Session.setContext(getBaseContext());
        TextView text = (TextView)findViewById(R.id.userTextName);
        String[] nome = Session.getUserAtual().getName().split(" ");
        text.setText(nome[0]);
        //-----------------------------------PopularLisView------------------------------------//
        final ListView listaDeItens = (ListView) findViewById(R.id.usuarioListViewList);
        listaDeItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TentItems item = (TentItems)listaDeItens.getAdapter().getItem(position);
                //editar
                return false;
            }
        });
        TentPersistence tentPersistence = new TentPersistence();
        Tent tent = tentPersistence.retornarTendaDoUsuario();
        List<TentItems> tendaFinal = tent.getTent();
        ItemListAdapter adapter = new ItemListAdapter(tendaFinal);
        listaDeItens.setAdapter(adapter);
        //-------------------------------------------------------------------------------------//

        final ImageView addBtn = (ImageView) findViewById(R.id.userImgBtnToCadastro);
        addBtn.setImageResource(R.mipmap.ic_add);
        addBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayToastAboveButton(v,R.string.txtNewItem);
                return false;
            }
        });
        addBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addBtn.setImageResource(R.mipmap.ic_addonclick);
                return false;
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),RegisterTentItemActivity.class);
                addBtn.setImageResource(R.mipmap.ic_add);
                startActivity(p);
            }
        });

        final ImageView searchBtn =(ImageView) findViewById(R.id.userImgBtnToSearch);
        searchBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayToastAboveButton(v,R.string.txtSearch);
                return false;
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),SearchProductsActivity.class);
                startActivity(p);
            }
        });
        ImageView logoutBtn =(ImageView) findViewById(R.id.userImgBtnLogout);
        logoutBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayToastAboveButton(v,R.string.txtLogout);
                return false;
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
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

    private void displayToastAboveButton(View v, int messageId)
    {
        int xOffset = 0;
        int yOffset = 0;
        Rect gvr = new Rect();

        View parent = (View) v.getParent();
        int parentHeight = parent.getHeight();

        if (v.getGlobalVisibleRect(gvr))
        {
            View root = v.getRootView();

            int halfWidth = root.getRight() / 2;
            int halfHeight = root.getBottom() / 2;

            int parentCenterX = ((gvr.right - gvr.left) / 2) + gvr.left;

            int parentCenterY = ((gvr.bottom - gvr.top) / 2) + gvr.top;

            if (parentCenterY <= halfHeight)
            {
                yOffset = -(halfHeight - parentCenterY) - parentHeight;
            }
            else
            {
                yOffset = (parentCenterY - halfHeight) - parentHeight;
            }

            if (parentCenterX < halfWidth)
            {
                xOffset = -(halfWidth - parentCenterX);
            }

            if (parentCenterX >= halfWidth)
            {
                xOffset = parentCenterX - halfWidth;
            }
        }

        Toast toast = Toast.makeText(Session.getContext(), messageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, xOffset, yOffset);
        toast.show();
    }
}