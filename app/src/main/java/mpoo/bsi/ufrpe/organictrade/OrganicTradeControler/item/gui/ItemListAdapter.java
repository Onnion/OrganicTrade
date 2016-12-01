package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class ItemListAdapter extends ArrayAdapter<TentItems> {
    private Context context;
    private List<TentItems> tentItems = null;
    private ProductPersistence productPersistence = new ProductPersistence();

    public ItemListAdapter(List<TentItems> tentItems) {
        super(Session.getContext(),0, tentItems);
        this.tentItems = tentItems;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TentItems itenDeTenda = tentItems.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_adapter, null);
        loadImg(itenDeTenda,view);

        TextView textoNomeProduto = (TextView) view.findViewById(R.id.itemTxtNome);
        textoNomeProduto.setText(productPersistence.nameProductById(itenDeTenda.getProduct().getProductId()));
        TextView textoPriceProduto = (TextView)view.findViewById(R.id.itemTxtPrice);
        textoPriceProduto.setText(itenDeTenda.getValue());

        return view;
    }

    private void loadImg(TentItems item,View view){
        ImageView imageView = (ImageView) view.findViewById(R.id.itemImg);
        if (item.getImageItem() == null){
            imageView.setImageResource(R.drawable.icon_item_no_img);
        }else{
            imageView.setImageBitmap(BitmapFactory.decodeFile(item.getImageItem()));
        }
    }
}