package mpoo.bsi.ufrpe.organictrade.controler.user.gui;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.ProductNegocio;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class LoadingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Session.setContext(getBaseContext());
        startSession();
        setDelay();
        ProductNegocio productNegocio = new ProductNegocio();
        productNegocio.productPersistence();
    }

    private void setDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserNegocio userNegocio = new UserNegocio();
                if(userNegocio.getUserPersistence().userLogged()){
                    userNegocio.getUserPersistence().searchFromUserLogged();
                    Intent j = new Intent(Session.getContext(), UserActivity.class);
                    startActivity(j);
                    finish();
                }else{
                    Intent j = new Intent(Session.getContext(), LoginActivity.class);
                    startActivity(j);
                    finish();
                }
            }
        }, 3000);
    }

    private void startSession() {
        Session session = new Session();
    }
}