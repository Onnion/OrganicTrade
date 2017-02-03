package mpoo.bsi.ufrpe.organictrade.infra;

import android.content.Context;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;

public class Session {
    private static DatabaseHelper dbAtual;
    private static User currentUser;
    private static User contactSelected;
    private static TentItem itemSelected;
    private static Tent tentSelected;
    private static Context context;

    public static Tent getTentSelected() {
        return tentSelected;
    }

    public static void setTentSelected(Tent tentSelected) {
        Session.tentSelected = tentSelected;
    }

    public static DatabaseHelper getDbAtual() {
        return dbAtual;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
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

    public static TentItem getItemSelected() {
        return itemSelected;
    }

    public static void setItemSelected(TentItem itemSelected) {
        Session.itemSelected = itemSelected;
    }

    public Session() {}

    public void startSession(){
        Session.dbAtual = new DatabaseHelper(Session.getContext());
    }
}