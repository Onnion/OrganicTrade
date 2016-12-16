package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.TentsItemsNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class TentActivity extends AppCompatActivity {
    private ImageView addBtn;
    private ImageView historycBtn;
    private ListView listOfItems;
    private ArrayList<TentItems> finalTent;
    private ItemListAdapter adapter;
    private TentItems itemSelected;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_tent_item, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deletarItem:
                delete(info);
                return true;
            case R.id.editarItem:
                toEdit(info);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Session.setTentSelected(null);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tent);
        Session.setContext(getBaseContext());
        loadAddBtn();
        loadHistorycBtn();
        loadTentAtributes();
    }

    private void setFunctionItemOfListView() {
        listOfItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Session.getContext(), "Mantenha o item pressionado para mais detalhes", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateListView() {
        listOfItems = (ListView) findViewById(R.id.tentListTentItems);
        setFunctionItemOfListView();
        TentsItemsNegocio tentsItemsNegocio = new TentsItemsNegocio();
        finalTent = tentsItemsNegocio.getTentItemsPersistence().getItemsOfTent(Session.getTentSelected().getTentId());
        adapter = new ItemListAdapter(finalTent);
        listOfItems.setAdapter(adapter);
        registerForContextMenu(listOfItems);
    }

    private void setFunctionAddBtn() {
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
                finish();
            }
        });
    }

    private void initializeAddBtn() {
        addBtn = (ImageView) findViewById(R.id.tentImgRegisterTentItem);
        addBtn.setImageResource(R.mipmap.ic_add);
    }

    private void loadAddBtn() {
        initializeAddBtn();
        setFunctionAddBtn();
    }

    private void setFunctionHistorycBtn() {
        historycBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayToastAboveButton(v,R.string.txtNewItem);
                return false;
            }
        });
        historycBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                historycBtn.setImageResource(R.mipmap.ic_history);
                return false;
            }
        });
        historycBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(Session.getContext(),HistorycAcitivity.class);
                historycBtn.setImageResource(R.mipmap.ic_history);
                startActivity(p);
                finish();
            }
        });
    }

    private void initializeHistorycBtn() {
        historycBtn = (ImageView) findViewById(R.id.tentImgToHistory);
        historycBtn.setImageResource(R.mipmap.ic_history);
    }

    private void loadHistorycBtn() {
        initializeHistorycBtn();
        setFunctionHistorycBtn();
    }

    private void loadTentName(){
        TextView textView = (TextView)findViewById(R.id.tentTextTentName);
        textView.setText(Session.getTentSelected().getName());
    }

    private  void loadTentAtributes(){
        loadTentName();
        loadImgTent();
        populateListView();
    }

    private void loadImgTent(){
        ImageView imgTent = (ImageView)findViewById(R.id.tentImgTentImg);
        if (Session.getTentSelected().getImg() == null){
            imgTent.setImageResource(R.drawable.icon_tent_no_img);
        }else{
            imgTent.setImageBitmap(BitmapFactory.decodeByteArray(Session.getTentSelected().getImg(),0,Session.getTentSelected().getImg().length));
        }
    }

    private void delete(AdapterView.AdapterContextMenuInfo info ) {
        TentsItemsNegocio tentsItemsNegocio = new TentsItemsNegocio();
        itemSelected =(TentItems) listOfItems.getAdapter().getItem(info.position);
        tentsItemsNegocio.getTentItemsPersistence().deleteTentItems(itemSelected.getTentItemsId());
        finalTent.remove(info.position);
        adapter.notifyDataSetChanged();
    }

    private void toEdit( AdapterView.AdapterContextMenuInfo info ) {
        itemSelected =(TentItems) listOfItems.getAdapter().getItem(info.position);
        Session.setItemSelected(itemSelected);
        Intent i = new Intent(Session.getContext(), EditRegisterTentItemActivity.class);
        startActivity(i);
        finish();
    }

    private void displayToastAboveButton(View v, int messageId) {
        int xOffset = 0;
        int yOffset = 0;
        Rect gvr = new Rect();

        View parent = (View) v.getParent();
        int parentHeight = parent.getHeight();

        if (v.getGlobalVisibleRect(gvr)) {
            View root = v.getRootView();

            int halfWidth = root.getRight() / 2;
            int halfHeight = root.getBottom() / 2;

            int parentCenterX = ((gvr.right - gvr.left) / 2) + gvr.left;

            int parentCenterY = ((gvr.bottom - gvr.top) / 2) + gvr.top;

            if (parentCenterY <= halfHeight) {
                yOffset = -(halfHeight - parentCenterY) - parentHeight;
            } else {
                yOffset = (parentCenterY - halfHeight) - parentHeight;
            }

            if (parentCenterX < halfWidth) {
                xOffset = -(halfWidth - parentCenterX);
            }

            if (parentCenterX >= halfWidth) {
                xOffset = parentCenterX - halfWidth;
            }
        }
        Toast toast = Toast.makeText(Session.getContext(), messageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, xOffset, yOffset);
        toast.show();
    }
}
