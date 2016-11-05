package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class ContactActivity extends AppCompatActivity {
    private User contatSelected = Session.getContactSelected();
    private TentItems itemSelected = Session.getItemSelected();
    private ProductPersistence productPersistence = new ProductPersistence();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Session.setContext(getBaseContext());
        TextView name = (TextView)findViewById(R.id.contatTextName);
        TextView phone = (TextView)findViewById(R.id.contatTextPhone);
        TextView productName = (TextView)findViewById(R.id.contatTextProductName);
        TextView productAmount = (TextView)findViewById(R.id.contatTextProductAmount);
        TextView productPrice = (TextView)findViewById(R.id.contatTextProductPrice);

        name.setText(contatSelected.getName());
        phone.setText(contatSelected.getPhone());
        productName.setText(productPersistence.nameProductById(itemSelected.getProdutoId()));
        productAmount.setText(itemSelected.getQuantidadeAtual());
        productPrice.setText("R$ "+itemSelected.getValor()+",00");
    }
}