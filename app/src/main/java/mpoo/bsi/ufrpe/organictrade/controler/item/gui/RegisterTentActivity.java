package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.TentNegocio;
import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;
import mpoo.bsi.ufrpe.organictrade.controler.user.gui.UserActivity;
import mpoo.bsi.ufrpe.organictrade.R;
import mpoo.bsi.ufrpe.organictrade.infra.gui.Util;

public class RegisterTentActivity extends FragmentActivity implements OnMapReadyCallback {
    private static int RESULT_LOAD_IMAGE = 1;
    private Tent tent;
    private GoogleMap mMap;
    private LatLng locationTent;
    private Locality locality = new Locality();

    @Override
    public void onBackPressed() {
        Session.setItemSelected(null);
        Intent i = new Intent(Session.getContext(),UserActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            if(cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                byte[] imgFinal = Util.getBytes(BitmapFactory.decodeFile(picturePath));
                tent.setImg(imgFinal);
                loadImgTentItem(imgFinal);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tent);
        tent = new Tent();
        loadMapFragment();
        Session.setContext(getBaseContext());
        setFunctionImgNoIcon();
        callRegisterItem();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                locationTent = new LatLng(Double.parseDouble(locality.getLagi()),Double.parseDouble(locality.getLongi()));
                loadMarkerLocationContactSelect();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(locationTent));
                mMap.setMinZoomPreference(13);
                }
        }, 1000);
    }

    private void setMarkerFunction() {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(Session.getContext(),"Mantenha precionado para selecionar o local de sua tenda",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(Session.getContext(), "Selecione o local de sua tenda " , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                locationTent = marker.getPosition();
            }
        });
    }

    private Bitmap getMarkerBitmapFromImageUser(User user) {
        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.custom_marker);
        markerImageView.setImageBitmap(BitmapFactory.decodeByteArray(user.getImage(),0,user.getImage().length));
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

    private void loadImgTentItem(byte[] str) {
        ImageView imageView = (ImageView) findViewById(R.id.registerTentImgTentImg);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(str,0,str.length));
    }

    private void callRegisterItem() {
        Button button = (Button)findViewById(R.id.registerTentBtnregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        EditText name = (EditText) findViewById((R.id.registerTentEdtName));
        TentValidation tentValidation = new TentValidation();
        if(tentValidation.validateRegister(name)) {
            TentNegocio tentNegocio = new TentNegocio();
            tent.setLongi(locationTent.longitude);
            tent.setLagi(locationTent.latitude);
            tent.setName(name.getText().toString());
            tent.setUser(Session.getCurrentUser());
            tentNegocio.registerTent(tent);
            Intent i = new Intent(Session.getContext(), UserActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void setFunctionImgNoIcon() {
        ImageView imageView = (ImageView) findViewById(R.id.registerTentImgTentImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void loadMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.registerFragMap);
        mapFragment.getMapAsync(this);
    }

    private void loadMarkerLocationContactSelect() {
        if(Session.getCurrentUser().getImage() == null){
            mMap.addMarker(new MarkerOptions()
                    .position(locationTent)
                    .draggable(true));
        }else {
            mMap.addMarker(new MarkerOptions()
                    .position(locationTent)
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromImageUser(Session.getCurrentUser())))
            );
        }
        setMarkerFunction();
    }
}