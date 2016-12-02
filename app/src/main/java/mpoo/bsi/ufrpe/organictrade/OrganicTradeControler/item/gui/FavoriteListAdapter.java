package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Product;
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
        Product product = products.get(position);
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.cardview_adapter, null);

        loadTextViwes(product,convertView);
        //loadImg(product,convertView);
        //setFunctionFavoriteBtn(convertView);

        return convertView;
    }

    private void loadTextViwes(Product product, View convertView){
        TextView productType = (TextView) convertView.findViewById(R.id.productType);
        productType.setText(product.getProductType());
        TextView productName = (TextView) convertView.findViewById(R.id.productName);
        productName.setText(product.getProductName());
    }

    private void setFuncitonCheckBox(View view, ArrayList<String> arrayList){
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.productCheckBox);
        if(!checkBox.isChecked()){

        }else{

        }

    }

//    private void loadImg(Product product, View view){
//        ImageView imageView = (ImageView)view.findViewById(R.id.productImg);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(product.getProductImg()));
//    }
}
