package mpoo.bsi.ufrpe.organictrade.Infra;

import android.content.Context;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Usuario;

public class Session {
    private static DatabaseHelper dbAtual;
    private static Usuario userAtual;
    private static Context context;

    public static DatabaseHelper getDbAtual() {
        return dbAtual;
    }

    public static Usuario getUserAtual() {
        return userAtual;
    }

    public static void setUserAtual(Usuario userAtual) {
        Session.userAtual = userAtual;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Session.context = context;
    }

    public Session(){
        DatabaseHelper db = new DatabaseHelper(Session.getContext());
        Session.dbAtual = db;
    }
}