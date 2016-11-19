package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UserPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class LoadingActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Session.setContext(getBaseContext());
        Session session = new Session();
        Handler handler = new Handler();
        ProductPersistence productPersistence = new ProductPersistence();
        //--------------------------------------------------------------//
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserPersistence userPersistence = new UserPersistence();
                if(userPersistence.userLogged()){
                    userPersistence.searchFromUserLogged();
                    Intent j = new Intent(Session.getContext(), UserActivity.class);
                    startActivity(j);
                    finish();
                }else{
                    Intent j = new Intent(Session.getContext(), LoginActivity.class);
                    startActivity(j);
                    finish();
                }
            }
        }, 5000);
    }
}