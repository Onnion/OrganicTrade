package mpoo.bsi.ufrpe.organictrade.recommendationsystem.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;

import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.recommendationsystem.ItemId;
import mpoo.bsi.ufrpe.organictrade.recommendationsystem.RecommendationItem;
import mpoo.bsi.ufrpe.organictrade.recommendationsystem.UserId;

public class LoadData {
    private Map<String,ItemId> mItens;
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public LoadData(){
        loadMItens();
    }

    private void loadMItens(){
        db = banco.getReadableDatabase();
        Map<String,ItemId> itens = new HashMap<>();
        Cursor itensOfTransitions = db.rawQuery(ComandosSql.sqlGetAllItensOfTransitions(),null);
        if(itensOfTransitions.moveToFirst()){
            do {
                ItemId item = new ItemId(itensOfTransitions.getString(0));
                itens.put(itensOfTransitions.getString(0),item);
            }while (itensOfTransitions.moveToNext());
        }
        itensOfTransitions.close();
        db.close();
        mItens = itens;
    }

    public Map<UserId,Map<ItemId,Float>> loadData(){
        Map<UserId,Map<ItemId,Float>> data = new HashMap<>();
        db = banco.getReadableDatabase();
        Cursor usuario = db.rawQuery(ComandosSql.sqlLoadUsersToRecomendationSystem(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if(usuario.moveToFirst()) {
            do {
                HashMap<ItemId,Float> compras = new HashMap<>();
                Cursor itemDoUsuario = db.rawQuery(ComandosSql.sqlLoadProductsOfUserToRecomendationSystem(), new String[]{usuario.getString(0),Integer.toString(Session.getCurrentUser().getIdUser())});
                if(itemDoUsuario.moveToFirst()){
                    do{
                        compras.put(mItens.get(itemDoUsuario.getString(0)),1.0f);
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

    public  HashMap<ItemId,Float> loadCurrentUser(ArrayList<RecommendationItem> itensOfUser){
        HashMap<ItemId,Float> user = new HashMap<>();
        ProductPersistence productPersistence = new ProductPersistence();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlLoadProductsOfCurrentUserToRecomendationSystem(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if(cursor.moveToFirst()){
            do {
                itensOfUser.add(new RecommendationItem(productPersistence.getProductById(cursor.getInt(0)),1.0f));
                user.put(mItens.get(cursor.getString(0)),1.0f);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return user;
    }
}
