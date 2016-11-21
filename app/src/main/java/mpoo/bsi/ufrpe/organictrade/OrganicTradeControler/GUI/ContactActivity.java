package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
        productName.setText(productPersistence.nameProductById(itemSelected.getProductId()));
        productAmount.setText(itemSelected.getCurrentAmount());
        productPrice.setText(new StringBuilder().append("R$ ").append(itemSelected.getValue()).toString());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] locationContactSelected = contactSelected.getAdress().split(",");
        String[] locationUser = Session.getCurrentUser().getAdress().split(",");
        LatLng tentContactSelected = new LatLng(parseDouble(locationContactSelected[0]),parseDouble(locationContactSelected[1]));
        LatLng tentUser = new LatLng(parseDouble(locationUser[0]),parseDouble(locationUser[1]));
        if(Session.getContactSelected().getImage().equals("0")){
            mMap.addMarker(new MarkerOptions()
                    .position(tentContactSelected)
                    .title("Tenda de " + contactSelected.getName()));
        }else {
            mMap.addMarker(new MarkerOptions()
                    .position(tentContactSelected)
                    .title("Tenda de " + contactSelected.getName())
                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromImageUser(Session.getContactSelected())))

            );
        }
        if(Session.getCurrentUser().getImage().equals("0")){
            mMap.addMarker(new MarkerOptions()
                    .position(tentUser)
                    .title("Minha tenda")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }else {
            mMap.addMarker(new MarkerOptions()
                    .position(tentUser)
                    .title("Minha tenda")
                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromImageUser(Session.getCurrentUser()))));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tentContactSelected));
        mMap.setMinZoomPreference(13);
    }

    private Bitmap getMarkerBitmapFromImageUser(User user) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.custom_marker);
        markerImageView.setImageBitmap(BitmapFactory.decodeFile(user.getImage()));
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
}