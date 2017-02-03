package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.content.Intent;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import mpoo.bsi.ufrpe.organictrade.R;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.infra.gui.UtilInfraGui;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.controler.user.gui.UserActivity;
import mpoo.bsi.ufrpe.organictrade.controler.item.negocio.TentItemNegocio;

public class EditRegisterTentItemActivity extends AppCompatActivity {
    private TentItem tentItemToEdit = new TentItem();
    private Spinner unityProduct;
    private EditText amount;
    private EditText price;
    private byte[] picturePath = Session.getItemSelected().getImageItem();
    private static final int RESULT_LOAD_IMAGE = 1;

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
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                byte[] imgFinal = UtilInfraGui.getBytes(BitmapFactory.decodeFile(picturePath));
                tentItemToEdit.setImageItem(imgFinal);
                loadImgTentItem(imgFinal);
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

    public void editRegisterProduct() {
        TentItemValidation tentItemValidation = new TentItemValidation();
        if(tentItemValidation.validateEdit(amount,price)) {
            tentItemToEdit.setProduct(Session.getItemSelected().getProduct());
            tentItemToEdit.setTentItemsId(Session.getItemSelected().getTentItemsId());
            tentItemToEdit.setTent(Session.getItemSelected().getTent());
            tentItemToEdit.setUnity(unityProduct.getSelectedItem().toString());
            tentItemToEdit.setCurrentAmount(Integer.parseInt(amount.getText().toString()));
            tentItemToEdit.setValue(Double.parseDouble(price.getText().toString()));
            tentItemToEdit.setImageItem(picturePath);
            TentItemNegocio tentItemNegocio = new TentItemNegocio();
            tentItemNegocio.tentItemEdit(tentItemToEdit);
            Intent i = new Intent(Session.getContext(), TentActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void loadValuesOfTentItemToEdit() {
        amount = (EditText) findViewById((R.id.editRegisterTentItemEdtProductAmount));
        price = (EditText) findViewById(R.id.editRegisterTentItemEdtProductPrice);
        amount.setText(Integer.toString(Session.getItemSelected().getCurrentAmount()));
        price.setText(Session.getItemSelected().getValue().toString());
    }

    private void loadUnitySpinner() {
        unityProduct = (Spinner) findViewById(R.id.editRegisterTentItemEdtProductUnity);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SpnItems, android.R.layout.simple_spinner_dropdown_item);
        unityProduct.setAdapter(adapter1);
    }

    private void loadImgTentItem(byte[] str) {
        ImageView imageView = (ImageView) findViewById(R.id.editRegisterTentItemImgProductImg);
        if(str != null) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(str, 0, str.length));
        }else{
            imageView.setImageResource(R.drawable.icon_item_no_img);
        }
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
                editRegisterProduct();
            }
        });
    }
}
