package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui.SearchProductsActivity;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

import static java.lang.Double.parseDouble;

public class TentSelectedActivity extends FragmentActivity implements OnMapReadyCallback {
    private Tent tentSelected = Session.getTentSelected();
    private User contactSelected = Session.getContactSelected();
    private TentItems itemSelected = Session.getItemSelected();
    private ProductPersistence productPersistence = new ProductPersistence();
    private GoogleMap mMap;
    private LatLng locationTentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tent_selected);
        Session.setContext(getBaseContext());
        loadContactOfTentItemSelected();
        loadMapFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationTentSelected = new LatLng(parseDouble(tentSelected.getLagi()),parseDouble(tentSelected.getLongi()));
        loadLocationContactSelect();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationTentSelected));
        mMap.setMinZoomPreference(13);
    }

    @Override
    public void onBackPressed() {
        Session.setContactSelected(null);
        Session.setItemSelected(null);
        Session.setTentSelected(null);
        Intent i = new Intent(Session.getContext(),SearchProductsActivity.class);
        startActivity(i);
        finish();
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

    private void loadLocationContactSelect() {
        if(Session.getContactSelected().getImage() == null){
            mMap.addMarker(new MarkerOptions()
                    .position(locationTentSelected)
                    .title(getText(R.string.tentOf) +" "+ contactSelected.getName()));
        }else {
            mMap.addMarker(new MarkerOptions()
                    .position(locationTentSelected)
                    .title(getText(R.string.tentOf) +" "+ contactSelected.getName())
                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromImageUser(Session.getContactSelected())))
            );
        }
    }

    private void loadContactOfTentItemSelected() {
        TextView phone = (TextView)findViewById(R.id.contatTextPhone);
        TextView productName = (TextView)findViewById(R.id.contatTextProductName);
        TextView productAmount = (TextView)findViewById(R.id.contatTextProductAmount);
        TextView productPrice = (TextView)findViewById(R.id.contatTextProductPrice);
        loadImgUser();
        loadImgTent();
        phone.setText(contactSelected.getPhone());
        productName.setText(productPersistence.nameProductById(itemSelected.getProduct().getProductId()));
        productAmount.setText(itemSelected.getCurrentAmount());
        productPrice.setText(new StringBuilder().append("R$ ").append(itemSelected.getValue()).toString());
    }

    private void loadImgTent() {
        ImageView imageTent = (ImageView)findViewById(R.id.contactImgTent);
        if (!(tentSelected.getImg() == null)){
            imageTent.setImageBitmap(BitmapFactory.decodeFile(tentSelected.getImg()));
        }else {
            imageTent.setImageResource(R.drawable.icon_tent_no_img);
        }
    }

    private void loadImgUser() {
        ImageView imageView = (ImageView) findViewById(R.id.contactImgUser);
        if (!(contactSelected.getImage() == null)){
            imageView.setImageBitmap(BitmapFactory.decodeFile(contactSelected.getImage()));
        }else {
            imageView.setImageResource(R.drawable.no_img_icon);
        }
    }

    private void loadMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.tentselectedFragMap);
        mapFragment.getMapAsync(this);
    }
}