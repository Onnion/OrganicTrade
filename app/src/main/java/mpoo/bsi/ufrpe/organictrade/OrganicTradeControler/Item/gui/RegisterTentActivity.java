package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Item.gui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Item.persistencia.TentPersistence;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.User.gui.UserActivity;
import mpoo.bsi.ufrpe.organictrade.R;

public class RegisterTentActivity extends AppCompatActivity {
    private TentLocation tentLocation;
    private static int RESULT_LOAD_IMAGE = 1;
    private Tent tent;
    private String nameStr;

    @Override
    public void onBackPressed() {
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
                tent.setImg(picturePath);
                loadImgTentItem(picturePath);
            }
        }
    }

    private void loadImgTentItem(String str) {
        ImageView imageView = (ImageView) findViewById(R.id.registerTentImgTentImg);
        imageView.setImageBitmap(BitmapFactory.decodeFile(str));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tent);
        tentLocation = new TentLocation();
        tent = new Tent();
        Session.setContext(getBaseContext());
        //------------------------------------------------//
        ImageView imageView = (ImageView) findViewById(R.id.registerTentImgTentImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        //------------------------------------------------//
        Button button = (Button)findViewById(R.id.registerTentBtnregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TentPersistence tentPersistence = new TentPersistence();
                loadValuesToRegister();
                tent.setLongi(tentLocation.getLongi());
                tent.setLagi(tentLocation.getLagi());
                tent.setName(nameStr);
                tent.setUser(Session.getCurrentUser());
                tentPersistence.registerTent(tent);
                Intent i = new Intent(Session.getContext(), UserActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void loadValuesToRegister() {
        EditText name = (EditText) findViewById((R.id.registerTentEdtName));
        nameStr = name.getText().toString();
    }
}
