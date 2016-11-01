package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.UsuarioPersistencia;
import mpoo.bsi.ufrpe.organictrade.R;

public class TelaDeCarregamentoActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_carregamento);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                UsuarioPersistencia crud = new UsuarioPersistencia();
                if(crud.usuarioLogado()){
                    Intent j = new Intent(Session.getContext(), UsuarioActivity.class);
                    startActivity(j);
                }else{
                    Intent j = new Intent(Session.getContext(), LoginActivity.class);
                    startActivity(j);
                }
            }
        }, 5000);
    }
}