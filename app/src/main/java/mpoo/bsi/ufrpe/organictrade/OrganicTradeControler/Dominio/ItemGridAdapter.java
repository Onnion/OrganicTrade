package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.R;

public class ItemGridAdapter extends BaseAdapter{
    private Context context;
    private List<ItensDeTenda> itensDeTenda = null;

    public ItemGridAdapter(List<ItensDeTenda> itensDeTenda) {
        this.itensDeTenda = itensDeTenda;
        this.context = Session.getContext();
    }

    @Override
    public int getCount() {
        return itensDeTenda.size();
    }

    @Override
    public Object getItem(int position) {
        return itensDeTenda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItensDeTenda itenDeTenda = itensDeTenda.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_adapter, null);

        TextView textoNomeProduto = (TextView) view.findViewById(R.id.itemTxtNome);
        textoNomeProduto.setText(itenDeTenda.getNomeProduto());

        TextView textoPriceProduto = (TextView)view.findViewById(R.id.itemTxtPrice);
        textoPriceProduto.setText(itenDeTenda.getValor());

        return view;
    }
}
