package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterTentItemActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();
    private TentItems tentItems;
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private Spinner nameProduct;
    private Spinner unityProduct;
    private ProductPersistence productPersistence = new ProductPersistence();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            tentItems.setImageItem(picturePath);
            imageView = (ImageView) findViewById(R.id.registerTentItemImgProductImg);
            imageView.setImageBitmap(BitmapFactory.decodeFile(tentItems.getImageItem()));
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tentItems = new TentItems();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tent_item);
        Session.setContext(getBaseContext());
        String[] productList = productPersistence.getNameProducts();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, productList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameProduct = (Spinner) findViewById(R.id.registerTentItemSpiProductName);
        nameProduct.setAdapter(adapter);
        //-------------------------------------------------------------//
        unityProduct = (Spinner) findViewById(R.id.registerTentItemEdtProductUnity);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SpnItems, android.R.layout.simple_spinner_dropdown_item);
        unityProduct.setAdapter(adapter1);

        final ImageView imageView = (ImageView) findViewById(R.id.registerTentItemImgProductImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    public void registerProduct(View v) {
        TentItemsPersistence crud = new TentItemsPersistence();
        EditText amount = (EditText) findViewById((R.id.registerTentItemEdtProductAmount));
        EditText price = (EditText) findViewById(R.id.registerTentItemEdtProductPrice);
        String idProductString = productPersistence.idProductByName(nameProduct.getSelectedItem().toString());
        String unityString = unityProduct.getSelectedItem().toString();
        String amountString = amount.getText().toString();
        String priceString = price.getText().toString();

        //validação
        if (priceString.contains(".")){
            priceString = priceString.replace(".", ",");
        }
        else {
            priceString = priceString + ",00";
        }

        tentItems.setProductId(idProductString);
        tentItems.setCurrentAmount(amountString);
        tentItems.setValue(priceString);
        tentItems.setUnity(unityString);
        tentItems.setUser_id(Session.getCurrentUser().getId_user());
        crud.insertTentItems(tentItems);
        Intent i = new Intent(Session.getContext(), UserActivity.class);
        startActivity(i);
        finish();
    }
}