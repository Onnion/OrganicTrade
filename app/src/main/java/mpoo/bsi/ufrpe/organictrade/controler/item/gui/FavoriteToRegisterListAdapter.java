package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.R;

public class FavoriteToRegisterListAdapter extends ArrayAdapter<Product>{
    private Context context;
    private List<Product> products;
    private List<Product> selecionados;

    public FavoriteToRegisterListAdapter(List<Product> products, List<Product> selecionados){
        super(Session.getContext(), 0, products);
        this.products = products;
        this.selecionados = selecionados;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Product product = products.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.favorites_to_register_listvew_adapter, null);
        }
        loadTextViwes(product,view);
        return view;
    }

    private void loadTextViwes(Product product, View convertView){
        TextView productType = (TextView) convertView.findViewById(R.id.registerProductType);
        productType.setText(product.getProductType());
        TextView productName = (TextView) convertView.findViewById(R.id.registerProductName);
        productName.setText(product.getProductName());
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.registerProductCheckBox);
        checkBox.setChecked(selecionados.contains(product));
    }
}