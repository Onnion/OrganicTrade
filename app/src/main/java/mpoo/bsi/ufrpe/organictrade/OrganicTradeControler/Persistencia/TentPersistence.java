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

    public Tent retornarTendaDoUsuario(){
        Tent tent = new Tent();
        TentItemsPersistence crud = new TentItemsPersistence();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUsuarioQueTemOItem(), new String[]{Session.getUserAtual().getId_user()});
        if(cursor.moveToFirst()){
            do{
                TentItems tentItems = crud.criarItensDeTenda(cursor);
                tent.setTent(tentItems);
            }while(cursor.moveToNext());
        }
        return tent;
    }
    public Tent retornarTendaDosUsuarios(){
        Tent tent = new Tent();
        TentItemsPersistence crud = new TentItemsPersistence();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlTodosOsItens(), new String[]{Session.getUserAtual().getId_user()});
        if(cursor.moveToFirst()){
            do{
                TentItems tentItems = crud.criarItensDeTenda(cursor);
                tent.setTent(tentItems);
            }while(cursor.moveToNext());
        }
        return tent;
    }

}
