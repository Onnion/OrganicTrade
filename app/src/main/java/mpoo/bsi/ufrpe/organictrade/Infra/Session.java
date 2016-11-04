package mpoo.bsi.ufrpe.organictrade.Infra;

import android.content.Context;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;

public class Session {
    private static DatabaseHelper dbAtual;
    private static User userAtual;
    private static User contactSelected;
    private static TentItems itemSelected;
    private static Context context;

    public static DatabaseHelper getDbAtual() {
        return dbAtual;
    }

    public static User getUserAtual() {
        return userAtual;
    }

    public static void setUserAtual(User userAtual) {
        Session.userAtual = userAtual;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Session.context = context;
    }

    public static User getContactSelected() {
        return contactSelected;
    }

    public static void setContactSelected(User contactSelected) {
        Session.contactSelected = contactSelected;
    }

    public static TentItems getItemSelected() {
        return itemSelected;
    }

    public static void setItemSelected(TentItems itemSelected) {
        Session.itemSelected = itemSelected;
    }


    public Session(){
        DatabaseHelper db = new DatabaseHelper(Session.getContext());
        Session.dbAtual = db;
    }
}