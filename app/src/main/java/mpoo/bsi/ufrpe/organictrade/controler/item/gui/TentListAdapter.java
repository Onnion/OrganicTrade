package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.R;

public class TentListAdapter extends ArrayAdapter<Tent> {
    private Context context;
    private ArrayList<Tent> tent = null;

    public TentListAdapter(ArrayList<Tent> tent) {
        super(Session.getContext(), 0, tent);
        this.tent = tent;
        this.context = Session.getContext();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Tent tenda = tent.get(position);
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.tent_listview_adapter, null);
        loadImg(tenda,view);
        TextView textoNomeTent = (TextView) view.findViewById(R.id.tentTxtNome);
        textoNomeTent.setText(tenda.getName());
        return view;
    }

    private void loadImg(Tent tent,View view){
        ImageView imageView = (ImageView) view.findViewById(R.id.tentImg);
        if (tent.getImg() == null){
            imageView.setImageResource(R.drawable.icon_tent_no_img);
        }else{
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(tent.getImg(),0,tent.getImg().length));
        }
    }
}