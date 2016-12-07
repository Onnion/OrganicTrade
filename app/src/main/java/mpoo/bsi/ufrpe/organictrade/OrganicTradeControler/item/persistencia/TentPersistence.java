package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.dominio.Tent;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.persistencia.UserPersistence;

public class TentPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    private Tent createTent(Cursor cursor){
        Tent tent = new Tent();
        UserPersistence userPersistence = new UserPersistence();
        tent.setTentId(cursor.getInt(0));
        tent.setLongi(cursor.getDouble(1));
        tent.setLagi(cursor.getDouble(2));
        tent.setUser(userPersistence.searchFromId(cursor.getInt(3)));
        tent.setName(cursor.getString(4));
        tent.setImg(cursor.getString(5));
        tent.setNote(cursor.getInt(6));
        tent.setNumberOfVotes(cursor.getInt(7));
        return tent;
    }

    public ArrayList<Tent> getTentOfUser(int userId){
        ArrayList<Tent> tents = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursorLocal = db.rawQuery(ComandosSql.sqlTentFromUser(),new String[]{Integer.toString(userId)});
        if(cursorLocal.moveToFirst()){
            do{
                Tent tent = createTent(cursorLocal);
                tents.add(tent);
            }while (cursorLocal.moveToNext());
        }
        cursorLocal.close();
        db.close();
        return tents;
    }
    public Tent getTent(int tentId){
        Tent tent = null;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlTentById(),new String[]{Integer.toString(tentId)});
        if(cursor.moveToFirst()){
            tent = createTent(cursor);
        }
        cursor.close();
        db.close();
        return tent;
    }

    public void registerTent(Tent tent){
        db = banco.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.getColumnTentLongi(),tent.getLongi());
        contentValues.put(DatabaseHelper.getColumnTentLagi(),tent.getLagi());
        contentValues.put(DatabaseHelper.getColumnTentUserId(),tent.getUser().getId_user());
        contentValues.put(DatabaseHelper.getColumnTentImg(),tent.getImg());
        contentValues.put(DatabaseHelper.getColumnTentName(),tent.getName());
        contentValues.put(DatabaseHelper.getColumnTentNote(),tent.getNote());
        contentValues.put(DatabaseHelper.getColumnTentNumberofvotes(),tent.getNumberOfVotes());
        db.insert(DatabaseHelper.getTableTentName(),null,contentValues);
        db.close();
    }

    public void deleteTent(int id){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentId()+"=?;";
        db.delete(DatabaseHelper.getTableTentName(),where,new String[]{Integer.toString(id)});
    }


}
