package mpoo.bsi.ufrpe.organictrade.controler.user.gui;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.UserNegocio;
import mpoo.bsi.ufrpe.organictrade.R;

public class LoadingActivity extends AppCompatActivity{
    private static final int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Session.setContext(getBaseContext());
        startSession();
        setDelay();
    }

    private void setDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserNegocio userNegocio = new UserNegocio();
                if(userNegocio.getUserLogged()){
                    userNegocio.searchFromUserLogged();
                    Intent j = new Intent(Session.getContext(), UserActivity.class);
                    startActivity(j);
                    finish();
                }else{
                    Intent j = new Intent(Session.getContext(), LoginActivity.class);
                    startActivity(j);
                    finish();
                }
            }
        }, DELAY);
    }

    private void startSession() {
        Session session = new Session();
        session.startSession();
    }
}