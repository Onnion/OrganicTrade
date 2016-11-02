package mpoo.bsi.ufrpe.organictrade.Infra.Persistencia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //
    private static final int DATABASE_VERSION = 19;
    private static final String DATABASE_NAME = "organicTrade.db";

    //User
    private static final String TABLE_USER_NAME = "usuario";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_USERNAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_EMAIL = "user_email";

    private static final String COLUMN_USER_NAME = "user_nome";
    private static final String COLUMN_USER_PHONE = "user_nome";

    //UsuarioLogado
    private static final String TABLE_USER_LOGGED_NAME = "usuario_logado";
    private static final String COLUMN_USER_LOGGED_ID = "user_id";

    //TentItems
    private static final String TABLE_ITENSDETENDA_NAME = "itensdetenda";
    private static final String COLUMN_ITENSDETENDA_ID = "itensdetenda_id";
    private static final String COLUMN_ITENSDETENDA_QUANTIDADE = "itensdetenda_quantidade";
    private static final String COLUMN_ITENSDETENDA_VALOR = "itensdetenda_valor";
    private static final String COLUMN_ITENSDETENDA_NOME_PORODUTO = "itensdetenda_nome_produto";
    private static final String COLUMN_ITENSDETENDA_USER_ID = "user_id";

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


    public static String getTableItensdetendaName() {
        return TABLE_ITENSDETENDA_NAME;
    }

    public static String getColumnItensdetendaId() {
        return COLUMN_ITENSDETENDA_ID;
    }

    public static String getColumnItensdetendaQuantidade() {
        return COLUMN_ITENSDETENDA_QUANTIDADE;
    }

    public static String getColumnItensdetendaValor() {
        return COLUMN_ITENSDETENDA_VALOR;
    }

    public static String getColumnItensdetendaNomePoroduto() {
        return COLUMN_ITENSDETENDA_NOME_PORODUTO;
    }

    public static String getColumnItensdetendaUserId() {
        return COLUMN_ITENSDETENDA_USER_ID;
    }

    public static String getTableUserLoggedName() {
    return TABLE_USER_LOGGED_NAME;
}

    public static String getColumnUserLoggedId() {
    return COLUMN_USER_LOGGED_ID;
}

    public static String getColumnUserName() {
    return COLUMN_USER_NAME;
}

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ComandosSql.sqlCreateUserTable());
        db.execSQL(ComandosSql.sqlCreateItensdetendaTable());
        db.execSQL(ComandosSql.sqlCreateUsuarioLogadoTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ComandosSql.sqlDropTableUsuario());
        db.execSQL(ComandosSql.sqlDropTableUsuarioLogado());
        db.execSQL(ComandosSql.sqlDropTableItensDeTenda());
        this.onCreate(db);
    }
}