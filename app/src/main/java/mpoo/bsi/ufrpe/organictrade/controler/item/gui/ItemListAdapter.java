package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.ProductNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class ItemListAdapter extends ArrayAdapter<TentItem> {
    private Context context;
    private List<TentItem> tentItems = null;
    private ProductNegocio productNegocio = new ProductNegocio();

    public ItemListAdapter(List<TentItem> tentItems) {
        super(Session.getContext(),0, tentItems);
        this.tentItems = tentItems;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TentItem itenDeTenda = tentItems.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_adapter, null);
        loadImg(itenDeTenda,view);

        TextView textoNomeProduto = (TextView) view.findViewById(R.id.itemTxtNome);
        textoNomeProduto.setText(productNegocio.nameProductById(itenDeTenda.getProduct().getProductId()));
        TextView textoPriceProduto = (TextView)view.findViewById(R.id.itemTxtPrice);
        textoPriceProduto.setText(itenDeTenda.getValue().toString());

        return view;
    }

    private void loadImg(TentItem item, View view){
        ImageView imageView = (ImageView) view.findViewById(R.id.itemImg);
        if (item.getImageItem() == null){
            imageView.setImageResource(R.drawable.icon_item_no_img);
        }else{
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(item.getImageItem(),0,item.getImageItem().length));
        }
    }
}