package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.R;

public class ContactActivity extends AppCompatActivity {
    private User contatSelected = Session.getContactSelected();
    private TentItems itemSelected = Session.getItemSelected();
    TextView name = (TextView)findViewById(R.id.contatTextName);
    TextView phone = (TextView)findViewById(R.id.contatTextPhone);
    TextView productName = (TextView)findViewById(R.id.contatTextProductName);
    TextView productAmount = (TextView)findViewById(R.id.contatTextProductAmount);
    TextView productPrice = (TextView)findViewById(R.id.contatTextProductPrice);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name.setText(contatSelected.getName());
        //tratamento celular
        phone.setText(contatSelected.getPhone());
        //pegar do banco
        productName.setText("");
        productAmount.setText(itemSelected.getQuantidadeAtual());
        productPrice.setText(itemSelected.getValor());



    }
}
