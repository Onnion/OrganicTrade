package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Item.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Item.dominio.TentItems;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.User.persistencia.UserPersistence;

public class TentPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    private Tent createTent(Cursor cursor){
        Tent tent = new Tent();
        UserPersistence userPersistence = new UserPersistence();
        tent.setTentId(cursor.getString(0));
        tent.setLongi(cursor.getString(1));
        tent.setLagi(cursor.getString(2));
        tent.setUser(userPersistence.searchFromId(cursor.getString(3)));
        tent.setName(cursor.getString(4));
        tent.setImg(cursor.getString(5));
        return tent;
    }

    public ArrayList<Tent> getTent(String userId){
        ArrayList<Tent> tents = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursorLocal = db.rawQuery(ComandosSql.sqlTentFromUser(),new String[]{userId});
        if(cursorLocal.moveToFirst()){
            Toast.makeText(Session.getContext(),"foi",Toast.LENGTH_LONG).show();
            do{
                Tent tent = createTent(cursorLocal);
                tents.add(tent);
            }while (cursorLocal.moveToNext());
        }else{
            Toast.makeText(Session.getContext(),"nao foi",Toast.LENGTH_LONG).show();

        }
        cursorLocal.close();
        db.close();
        return tents;
    }

    public void registerTent(Tent tent){
        db = banco.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.getColumnTentLongi(),tent.getLongi());
        contentValues.put(DatabaseHelper.getColumnTentLagi(),tent.getLagi());
        contentValues.put(DatabaseHelper.getColumnTentUserId(),tent.getUser().getId_user());
        contentValues.put(DatabaseHelper.getColumnTentImg(),tent.getImg());
        contentValues.put(DatabaseHelper.getColumnTentName(),tent.getName());
        db.insert(DatabaseHelper.getTableTentName(),null,contentValues);
        db.close();
    }

    public void deleteTent(String id){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentId()+"=?;";
        db.delete(DatabaseHelper.getTableTentName(),where,new String[]{id});
    }

//    public List<TentItems> retornarTendaDosUsuarios(){
//        List<TentItems> tent;
//        TentItemsPersistence crud = new TentItemsPersistence();
//        db = banco.getReadableDatabase();
//        Cursor cursor = db.rawQuery(ComandosSql.sqlAllItems(), new String[]{Session.getCurrentUser().getId_user()});
//        if(cursor.moveToFirst()){
//            Toast.makeText(Session.getContext(),"foi",Toast.LENGTH_LONG).show();
//            tent = List<TentItems>;
//            do{
//                TentItems tentItems = crud.createTentItems(cursor);
//                tent.addTentItem(tentItems);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return tent;
//    }
}
