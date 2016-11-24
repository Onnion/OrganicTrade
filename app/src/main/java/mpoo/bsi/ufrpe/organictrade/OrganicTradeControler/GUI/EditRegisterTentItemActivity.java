package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.GUI;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.R;

public class EditRegisterTentItemActivity extends AppCompatActivity {

    private TentItems tentItemsToEdit = new TentItems();
    private Spinner unityProduct;
    private EditText amount;
    private EditText price;
    private String picturePath = Session.getItemSelected().getImageItem();
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    public void onBackPressed() {
        Session.setItemSelected(null);
        Intent i = new Intent(Session.getContext(),UserActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            if(cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                tentItemsToEdit.setImageItem(picturePath);
                loadImgTentItem(picturePath);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register_tent_item);
        Session.setContext(getBaseContext());
        loadImgTentItem(Session.getItemSelected().getImageItem());
        loadUnitySpinner();
        loadValuesOfTentItemToEdit();
        callEditRegisterProduct();
        setFunctionToTentItemNoImgIcon();
    }

    public void EditRegisterProduct(View v) {
        tentItemsToEdit.setProductId(Session.getItemSelected().getProductId());
        tentItemsToEdit.setTentItems_id(Session.getItemSelected().getTentItems_id());
        tentItemsToEdit.setUser_id(Session.getItemSelected().getUser_id());
        tentItemsToEdit.setUnity(unityProduct.getSelectedItem().toString());
        tentItemsToEdit.setCurrentAmount(amount.getText().toString());
        tentItemsToEdit.setValue(price.getText().toString());
        tentItemsToEdit.setImageItem(picturePath);
        TentItemsPersistence tentItemsPersistence = new TentItemsPersistence();
        tentItemsPersistence.tentItemEdit(tentItemsToEdit);
        Intent i = new Intent(Session.getContext(), UserActivity.class);
        startActivity(i);
        finish();
    }

    private void loadValuesOfTentItemToEdit() {
        amount = (EditText) findViewById((R.id.editRegisterTentItemEdtProductAmount));
        price = (EditText) findViewById(R.id.editRegisterTentItemEdtProductPrice);
        amount.setText(Session.getItemSelected().getCurrentAmount());
        price.setText(Session.getItemSelected().getValue());
    }

    private void loadUnitySpinner() {
        unityProduct = (Spinner) findViewById(R.id.editRegisterTentItemEdtProductUnity);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SpnItems, android.R.layout.simple_spinner_dropdown_item);
        unityProduct.setAdapter(adapter1);
    }

    private void loadImgTentItem(String str) {
        ImageView imageView = (ImageView) findViewById(R.id.editRegisterTentItemImgProductImg);
        imageView.setImageBitmap(BitmapFactory.decodeFile(str));
    }

    private void setFunctionToTentItemNoImgIcon() {
        final ImageView imageView = (ImageView) findViewById(R.id.editRegisterTentItemImgProductImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void callEditRegisterProduct() {
        final Button button = (Button)findViewById(R.id.editRegisterTentItemBtnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditRegisterProduct(button);
            }
        });
    }
}
