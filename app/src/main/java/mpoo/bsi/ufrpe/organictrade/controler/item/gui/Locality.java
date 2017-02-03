package mpoo.bsi.ufrpe.organictrade.controler.item.gui;
import android.location.Location;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import mpoo.bsi.ufrpe.organictrade.infra.Session;

public class Locality implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient;
    private String longi;
    private String lagi;

    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            this.setLagi(String.valueOf(mLastLocation.getLatitude()));
            this.setLongi(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspeded(" + i + ")");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed(" + connectionResult + ")");
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(Session.getContext())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    public Locality() {
        callConnection();
        mGoogleApiClient.connect();
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLagi() {
        return lagi;
    }

    public void setLagi(String lagi) {
        this.lagi = lagi;
    }
}