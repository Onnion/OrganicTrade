package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import mpoo.bsi.ufrpe.organictrade.R;

public class teste extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks{

    private TextView texto;
    private GoogleApiClient mGoogleApiClient;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private LocationRequest mLocationRequest;
    private final long UPDATE_INTERVAL = 10 * 1000;
    private final long FASTEST_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        texto = (TextView)findViewById(R.id.tvParaTeste);
        setContentView(R.layout.activity_teste);
        callConnection();
    }

    private synchronized void callConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            String lati = String.valueOf(mLastLocation.getLatitude());
            String longe = String.valueOf(mLastLocation.getLongitude());
            Log.i("LOG","Latitude: "+lati);
            Log.w("LOG","Longitude: "+longe);
            Toast t = Toast.makeText(this,lati,Toast.LENGTH_LONG);
            Toast w = Toast.makeText(this,longe,Toast.LENGTH_LONG);
            t.show();
            w.show();
            //+"----"+String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG","onConnectionSuspeded("+i+")");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("LOG","onConnectionFailed("+connectionResult+")");
    }

//
//    protected void startLocationUpdates() {
//        // Create the location request
//        mLocationRequest = LocationRequest.create()
//                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                .setInterval(UPDATE_INTERVAL)
//                .setFastestInterval(FASTEST_INTERVAL);
//        // Request location updates
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
//    }
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        texto = (TextView)findViewById(R.id.teste);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            // Here, thisActivity is the current activity
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED) {
//                // Should we show an explanation?
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)) {
//                    // Show an expanation to the user *asynchronously* -- don't block
//                    // this thread waiting for the user's response! After the user
//                    // sees the explanation, try again to request the permission.
//                } else {
//                    // No explanation needed, we can request the permission.
//                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                    // app-defined int constant. The callback method gets the
//                    // result of the request.
//                    //----------------------------------------------------------------//
//                    Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//                    if(l!=null){
//                        Log.i("LOG","latitude"+l.getLatitude());
//                        Log.i("LOG","longitude"+l.getLongitude());
//                        texto.setText(l.getLatitude()+"---"+l.getLongitude());
//                    }else{
//                    }
//                }
//            }
//        }else{
//            Toast.makeText(this,"else do primeiro if" , Toast.LENGTH_LONG).show();
//            Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            if(l!= null){
//                Toast.makeText(this,"terceiro if" , Toast.LENGTH_LONG).show();
//                Log.i("LOG","latitude"+l.getLatitude());
//                Log.i("LOG","longitude"+l.getLongitude());
//                texto.setText(l.getLatitude()+"---"+l.getLongitude());
//            }else{
//                Toast.makeText(this,"else do terceiro if" , Toast.LENGTH_LONG).show();
//                startLocationUpdates();
//            }
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        if (i == CAUSE_SERVICE_DISCONNECTED) {
//            Toast.makeText(this, "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
//        } else if (i == CAUSE_NETWORK_LOST) {
//            Toast.makeText(this, "Network lost. Please re-connect.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.i("LOG","onConnectionFailed("+connectionResult+")");
//    }
//
//
//
//
//    public void onLocationChanged(Location location) {
//        // New location has now been determined
//        String msg = "Updated Location: " +
//                Double.toString(location.getLatitude()) + "," +
//                Double.toString(location.getLongitude());
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        // You can now create a LatLng Object for use with maps
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//    }
}
