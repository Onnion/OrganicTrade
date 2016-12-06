package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoriteToRegisterListAdapter extends ArrayAdapter<Product>{
    private Context context;
    private ArrayList<Product> products;
    private ArrayList<Product> selecionados;

    public FavoriteToRegisterListAdapter(ArrayList<Product> products, ArrayList<Product> selecionados){
        super(Session.getContext(), 0, products);
        this.products = products;
        this.selecionados = selecionados;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Product product = products.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.favorites_to_register_listvew_adapter, null);
        loadTextViwes(product,convertView);
        //loadImg(product,convertView);
        //setFunctionFavoriteBtn(convertView);
        return convertView;
    }

    private void loadTextViwes(Product product, View convertView){
        TextView productType = (TextView) convertView.findViewById(R.id.registerProductType);
        productType.setText(product.getProductType());
        TextView productName = (TextView) convertView.findViewById(R.id.registerProductName);
        productName.setText(product.getProductName());
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.registerProductCheckBox);
        checkBox.setChecked(selecionados.contains(product));
    }

//    private void loadImg(Product product, View view){
//        ImageView imageView = (ImageView)view.findViewById(R.id.productImg);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(product.getProductImg()));
//    }
}