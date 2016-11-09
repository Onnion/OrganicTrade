package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.location.Location;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;

public class UserLocation implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private String adress;

    public UserLocation() {
        callConnection();
        mGoogleApiClient.connect();
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(Session.getContext())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            String lati = String.valueOf(mLastLocation.getLatitude());
            String longi = String.valueOf(mLastLocation.getLongitude());
            String adressFinal = lati + "," + longi;
            setAdress(adressFinal);
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

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}