package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;

public class TentItemsPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public TentItems createTentItems(Cursor cursor){
        TentItems tentItems = new TentItems();

        tentItems.setTentItems_id(cursor.getString(0));
        tentItems.setCurrentAmount(cursor.getString(1));
        tentItems.setValue(cursor.getString(2));
        tentItems.setUnity(cursor.getString(3));
        tentItems.setProductId(cursor.getString(4));
        tentItems.setUser_id(cursor.getString(5));

        return tentItems;
    }

    public void deleteTentItems(String id){
        db = banco.getReadableDatabase();
        String where = DatabaseHelper.getColumnTentitemsId()+"=?;";
        db.delete(DatabaseHelper.getTableTentitemsName(),where,new String[]{id});
    }

    public void insertTentItems(TentItems tentItems){
        db = banco.getWritableDatabase();
        ContentValues tentItemsValues = new ContentValues();
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsId(), tentItems.getTentItems_id());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsAmount(), tentItems.getCurrentAmount());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsPrice(), tentItems.getValue());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsUnity(), tentItems.getUnity());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsUserId(),Session.getCurrentUser().getId_user());
        tentItemsValues.put(DatabaseHelper.getColumnTentitemsProductId(),tentItems.getProductId());
        db.insert(DatabaseHelper.getTableTentitemsName(), null, tentItemsValues);
        db.close();
    }

    public void tentItemEdit(TentItems tentItems){
        db = banco.getWritableDatabase();
        String where = DatabaseHelper.getColumnTentitemsId()+" = "+Session.getItemSelected().getTentItems_id();
        ContentValues tentItemsEditedValues = new ContentValues();
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsAmount(),tentItems.getCurrentAmount());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsPrice(),tentItems.getValue());
        tentItemsEditedValues.put(DatabaseHelper.getColumnTentitemsUnity(), tentItems.getUnity());
        db.update(DatabaseHelper.getTableTentitemsName(), tentItemsEditedValues,where,null);
        db.close();
    }
}