package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoriteListAdapter extends ArrayAdapter<Product>{
    private Context context;
    private List<Product> products;

    public FavoriteListAdapter(List<Product> products){
        super(Session.getContext(), 0, products);
        this.products = products;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Product product = products.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.favorite_listview_adapter, null);
        }
        loadTextViwes(product,view);
        return view;
    }

    private void loadTextViwes(Product product, View convertView){
        TextView productType = (TextView) convertView.findViewById(R.id.productType);
        productType.setText(product.getProductType());
        TextView productName = (TextView) convertView.findViewById(R.id.productName);
        productName.setText(product.getProductName());
    }
}