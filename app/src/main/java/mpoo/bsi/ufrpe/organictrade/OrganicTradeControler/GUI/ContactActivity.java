package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

import static java.lang.Double.parseDouble;

public class ContactActivity extends FragmentActivity implements OnMapReadyCallback {
    private User contatSelected = Session.getContactSelected();
    private TentItems itemSelected = Session.getItemSelected();
    private ProductPersistence productPersistence = new ProductPersistence();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragmenttest);
        mapFragment.getMapAsync(this);
        //
        Session.setContext(getBaseContext());
        TextView name = (TextView)findViewById(R.id.contatTextName);
        TextView phone = (TextView)findViewById(R.id.contatTextPhone);
        TextView productName = (TextView)findViewById(R.id.contatTextProductName);
        TextView productAmount = (TextView)findViewById(R.id.contatTextProductAmount);
        TextView productPrice = (TextView)findViewById(R.id.contatTextProductPrice);

        name.setText(contatSelected.getName());
        phone.setText(contatSelected.getPhone());
        productName.setText(productPersistence.nameProductById(itemSelected.getProdutoId()));
        productAmount.setText(itemSelected.getQuantidadeAtual());
        productPrice.setText("R$ "+itemSelected.getValor()+",00");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] locationContactSelected = contatSelected.getAdress().split(",");
        Log.i("LOG",locationContactSelected[0]+"--"+locationContactSelected[1]);
        LatLng tentContactSelected = new LatLng(parseDouble(locationContactSelected[0]),parseDouble(locationContactSelected[1]));
        mMap.addMarker(new MarkerOptions().position(tentContactSelected).title("Tenda de "+contatSelected.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tentContactSelected));
        mMap.setMinZoomPreference(13);

    }
}