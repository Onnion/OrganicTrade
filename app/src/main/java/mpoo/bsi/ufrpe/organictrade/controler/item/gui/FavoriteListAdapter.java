package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoriteListAdapter extends ArrayAdapter<Product>{
    Context context;
    ArrayList<Product> products;

    public FavoriteListAdapter(ArrayList<Product> products){
        super(Session.getContext(), 0, products);
        this.products = products;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Product product = products.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.favorite_listview_adapter, null);
        loadTextViwes(product,convertView);
        //loadImg(product,convertView);
        return convertView;
    }

    private void loadTextViwes(Product product, View convertView){
//        TextView productType = (TextView) convertView.findViewById(R.id.productType);
//        productType.setText(product.getProductType());
        TextView productName = (TextView) convertView.findViewById(R.id.productName);
        productName.setText(product.getProductName());
    }

//    private void loadImg(Product product, View view){
//        ImageView imageView = (ImageView)view.findViewById(R.id.productImg);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(product.getProductImg()));
//    }
}