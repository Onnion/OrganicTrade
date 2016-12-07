package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.gui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterTentItemActivity extends AppCompatActivity {

    private TentItems tentItems;
    private static int RESULT_LOAD_IMAGE = 1;
    private String idProductString;
    private String unityString;
    private String amountString;
    private String priceString;
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
            if(cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                tentItems.setImageItem(picturePath);
                loadImgTentItem(picturePath);
            }
        }
    }

    private void loadImgTentItem(String str) {
        ImageView imageView = (ImageView) findViewById(R.id.registerTentItemImgProductImg);
        imageView.setImageBitmap(BitmapFactory.decodeFile(str));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Session.setContext(getBaseContext());
        setContentView(R.layout.activity_register_tent_item);
        tentItems = new TentItems();
        loadUnitySpinner();
        loadProductNameSpinner();
        callRegisterProduct();
        setFunctionToTentItemNoImgIcon();
    }

    private void loadProductNameSpinner() {
        String[] productList = productPersistence.getNameProducts();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, productList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameProduct = (Spinner) findViewById(R.id.registerTentItemSpiProductName);
        nameProduct.setAdapter(adapter);
    }

    private void loadUnitySpinner() {
        unityProduct = (Spinner) findViewById(R.id.registerTentItemEdtProductUnity);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SpnItems, android.R.layout.simple_spinner_dropdown_item);
        unityProduct.setAdapter(adapter1);
    }

    private void setFunctionToTentItemNoImgIcon() {
        final ImageView imageView = (ImageView) findViewById(R.id.registerTentItemImgProductImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void callRegisterProduct() {
        Button button = (Button)findViewById(R.id.registerTentItemBtnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProduct();
            }
        });
    }

    public void registerProduct() {
        loadValuesToRegister();
        TentItemsPersistence crud = new TentItemsPersistence();
        tentItems.setProduct(productPersistence.getProductById(Integer.parseInt(idProductString)));
        tentItems.setCurrentAmount(Integer.parseInt(amountString));
        tentItems.setValue(Double.parseDouble(priceString));
        tentItems.setUnity(unityString);
        tentItems.setTent(Session.getTentSelected());
        crud.insertTentItems(tentItems);
        Intent i = new Intent(Session.getContext(), TentActivity.class);
        startActivity(i);
        finish();
    }

    private void loadValuesToRegister() {
        EditText amount = (EditText) findViewById((R.id.registerTentItemEdtProductAmount));
        EditText price = (EditText) findViewById(R.id.registerTentItemEdtProductPrice);
        idProductString = productPersistence.idProductByName(nameProduct.getSelectedItem().toString());
        unityString = unityProduct.getSelectedItem().toString();
        amountString = amount.getText().toString();
        priceString = price.getText().toString();
        formatPrice();
    }

    private void formatPrice() {
        //validação
        if (priceString.contains(",")){
            priceString = priceString.replace(",", ".");
        }
        else {
            priceString = priceString + ".00";
        }

    }
}