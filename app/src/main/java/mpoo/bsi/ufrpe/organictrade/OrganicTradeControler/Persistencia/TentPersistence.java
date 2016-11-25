package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Tent;

public class TentPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public Tent getUserTent(){
        Tent tent = null;
        TentItemsPersistence crud = new TentItemsPersistence();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlItemFromUser(), new String[]{Session.getCurrentUser().getId_user()});
        if(cursor.moveToFirst()){
            tent = new Tent();
            do{
                TentItems tentItems = crud.createTentItems(cursor);
                tent.addTentItem(tentItems);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tent;
    }

    public Tent retornarTendaDosUsuarios(){
        Tent tent = null;
        TentItemsPersistence crud = new TentItemsPersistence();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlAllItems(), new String[]{Session.getCurrentUser().getId_user()});
        if(cursor.moveToFirst()){
            tent = new Tent();
            do{
                TentItems tentItems = crud.createTentItems(cursor);
                tent.addTentItem(tentItems);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tent;
    }
}
