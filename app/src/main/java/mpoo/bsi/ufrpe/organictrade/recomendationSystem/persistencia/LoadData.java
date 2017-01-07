package mpoo.bsi.ufrpe.organictrade.recomendationSystem.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.recomendationSystem.ItemId;
import mpoo.bsi.ufrpe.organictrade.recomendationSystem.UserId;

public class LoadData {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public Map<UserId,Map<ItemId,Float>> loadData(){
        Map<UserId,Map<ItemId,Float>> data = new HashMap<>();
        db = banco.getReadableDatabase();
        Cursor usuario = db.rawQuery(ComandosSql.sqlLoadUsersToRecomendationSystem(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});

        if(usuario.moveToFirst()) {
            do {
                HashMap<ItemId,Float> compras = new HashMap<>();
                Cursor itemDoUsuario = db.rawQuery(ComandosSql.sqlLoadProductsOfUserToRecomendationSystem(), new String[]{usuario.getString(0)});
                if(itemDoUsuario.moveToFirst()){
                    do{
                        ItemId item = new ItemId(itemDoUsuario.getString(0));
                        compras.put(item,1.0f);
                    }while (itemDoUsuario.moveToNext());
                    data.put(new UserId(usuario.getString(0)),compras);
                }
                itemDoUsuario.close();
            } while (usuario.moveToNext());
        }
        usuario.close();
        db.close();
        return data;
    }

    public  HashMap<ItemId,Float> loadCurrentUser(){
        HashMap<ItemId,Float> user = new HashMap<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlLoadProductsOfCurrentUserToRecomendationSystem(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if(cursor.moveToFirst()){
            do {
                ItemId item = new ItemId(cursor.getString(0));
                user.put(item,1.0f);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;
    }
}
