package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

import static java.lang.Double.parseDouble;

public class ContactActivity extends FragmentActivity implements OnMapReadyCallback {
    private User contactSelected = Session.getContactSelected();
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

        name.setText(contactSelected.getName());
        phone.setText(contactSelected.getPhone());
        productName.setText(productPersistence.nameProductById(itemSelected.getProdutoId()));
        productAmount.setText(itemSelected.getQuantidadeAtual());
        productPrice.setText("R$ "+ itemSelected.getValor());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] locationContactSelected = contactSelected.getAdress().split(",");
        String[] locationUser = Session.getUserAtual().getAdress().split(",");
        LatLng tentContactSelected = new LatLng(parseDouble(locationContactSelected[0]),parseDouble(locationContactSelected[1]));
        LatLng tentUser = new LatLng(parseDouble(locationUser[0]),parseDouble(locationUser[1]));
        mMap.addMarker(new MarkerOptions().position(tentContactSelected).title("Tenda de "+ contactSelected.getName()));
        mMap.addMarker(new MarkerOptions().position(tentUser).title("Minha tenda").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tentContactSelected));
        mMap.setMinZoomPreference(13);

    }
}