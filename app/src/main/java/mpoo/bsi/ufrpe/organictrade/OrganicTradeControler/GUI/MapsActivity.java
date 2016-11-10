package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.R;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map1223);
        mapFragment.getMapAsync(this);
        String[] locationContactSelected = Session.getContactSelected().getAdress().split(",");
        Toast.makeText(Session.getContext(),locationContactSelected[1],Toast.LENGTH_LONG).show();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng tentContactSelected = new LatLng(31, 151);
        mMap.addMarker(new MarkerOptions().position(tentContactSelected).title("User Tent"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tentContactSelected));
    }
}
