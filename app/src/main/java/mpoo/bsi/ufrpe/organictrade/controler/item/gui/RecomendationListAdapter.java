package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.R;

public class RecomendationListAdapter extends ArrayAdapter<Product> {
    private Context context;
    private ArrayList<Product> products = null;

    public RecomendationListAdapter(ArrayList<Product> product) {
        super(Session.getContext(), 0, product);
        this.products = product;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Product product = products.get(position);
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.recomendation_listview_adapter, null);
        TextView textoNomeProduct = (TextView) view.findViewById(R.id.productName);
        textoNomeProduct.setText(product.getProductName());
        return view;
    }
}