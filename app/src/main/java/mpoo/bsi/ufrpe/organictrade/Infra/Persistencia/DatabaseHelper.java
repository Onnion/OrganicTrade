package mpoo.bsi.ufrpe.organictrade.Infra.Persistencia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //
    private static final int DATABASE_VERSION = 27;
    private static final String DATABASE_NAME = "organicTrade.db";

    //User
    private static final String TABLE_USER_NAME = "usuario";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_USERNAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_NAME = "user_nome";
    private static final String COLUMN_USER_PHONE = "user_phone";
    private static final String COLUMN_USER_ADRESS = "user_adress";
    private static final String COLUMN_USER_IMG = "user_img";

    //UserLogged
    private static final String TABLE_USER_LOGGED_NAME = "user_logged";
    private static final String COLUMN_USER_LOGGED_ID = "user_id";

    //TentItems
    private static final String COLUMN_TENTITEMS_ID = "tentiterms_id";
    private static final String TABLE_TENTITEMS_NAME = "tentitems_name";
    private static final String COLUMN_TENTITEMS_AMOUNT = "tentitems_amount";
    private static final String COLUMN_TENTITEMS_PRICE = "tentitems_price";
    private static final String COLUMN_TENTITEMS_UNITY = "tentitems_unity";
    private static final String COLUMN_TENTITEMS_PRODUCT_ID = "product_id";
    private static final String COLUMN_TENTITEMS_USER_ID = "user_id";
    private static final String COLUMN_TENTITEMS_IMG = "tentitems_img";

    //Products
    private static final String TABLE_PRODUCT_NAME = "product";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";

    //
    private SQLiteDatabase db;

    //
    public static String getTableUserName() {
        return TABLE_USER_NAME;
    }

    public static String getColumnUserId() {
        return COLUMN_USER_ID;
    }

    public static String getColumnUserUsername() {
        return COLUMN_USER_USERNAME;
    }

    public static String getColumnUserPassword() {
        return COLUMN_USER_PASSWORD;
    }

    public static String getColumnUserEmail() {
        return COLUMN_USER_EMAIL;
    }

    public static String getColumnUserPhone() {
        return COLUMN_USER_PHONE;
    }

    public static String getColumnUserAdress() {
        return COLUMN_USER_ADRESS;
    }

    public static String getColumnUserName() {
        return COLUMN_USER_NAME;
    }

    public static String getColumnUserImg() {
        return COLUMN_USER_IMG;
    }

    //----------------------------------------------------------------------------//
    public static String getTableTentitemsName() {
        return TABLE_TENTITEMS_NAME;
    }

    public static String getColumnTentitemsId() {
        return COLUMN_TENTITEMS_ID;
    }

    public static String getColumnTentitemsAmount() {
        return COLUMN_TENTITEMS_AMOUNT;
    }

    public static String getColumnTentitemsPrice() {
        return COLUMN_TENTITEMS_PRICE;
    }

    public static String getColumnTentitemsProductId() {
        return COLUMN_TENTITEMS_PRODUCT_ID;
    }

    public static String getColumnTentitemsUserId() {
        return COLUMN_TENTITEMS_USER_ID;
    }

    public static String getColumnTentitemsUnity() {
        return COLUMN_TENTITEMS_UNITY;
    }

    public static String getColumnTentitemsImg(){
        return COLUMN_TENTITEMS_IMG;
    }
    //----------------------------------------------------------------------------//

    public static String getTableUserLoggedName() {
        return TABLE_USER_LOGGED_NAME;
    }

    public static String getColumnUserLoggedId() {
        return COLUMN_USER_LOGGED_ID;
    }
    //----------------------------------------------------------------------------//

    public static String getColumnProductName() {
        return COLUMN_PRODUCT_NAME;
    }

    public static String getTableProductName() {
        return TABLE_PRODUCT_NAME;
    }

    public static String getColumnProductId() {
        return COLUMN_PRODUCT_ID;
    }
    //----------------------------------------------------------------------------//

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ComandosSql.sqlCreateUserTable());
        db.execSQL(ComandosSql.sqlCreateTentItemsTable());
        db.execSQL(ComandosSql.sqlCreateUserLoggedTable());
        db.execSQL(ComandosSql.sqlCreateProductTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ComandosSql.sqlDropTableUsuario());
        db.execSQL(ComandosSql.sqlDropTableUsuarioLogado());
        db.execSQL(ComandosSql.sqlDropTableItensDeTenda());
        db.execSQL(ComandosSql.sqlDropTableProduct());
        this.onCreate(db);
    }
}