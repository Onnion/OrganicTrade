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

    public TentItems criarItensDeTenda(Cursor cursor){
        TentItems tentItems = new TentItems();
        tentItems.setIntensdetenda_id(cursor.getString(0));
        tentItems.setQuantidadeAtual(cursor.getString(1));
        tentItems.setValor(cursor.getString(2));
        tentItems.setNomeProduto(cursor.getString(3));
        tentItems.setUsurio_id(cursor.getString(4));
        return tentItems;
    }

    public void inserirItensDeTenda(TentItems tentItems){
        db = banco.getWritableDatabase();
        ContentValues valoresItensDeTenda = new ContentValues();
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsId(), tentItems.getIntensdetenda_id());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsAmount(), tentItems.getQuantidadeAtual());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsPrice(), tentItems.getValor());
        valoresItensDeTenda.put(DatabaseHelper.getColumnProductName(), tentItems.getNomeProduto());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsUserId(),Session.getUserAtual().getId_user());
        db.insert(DatabaseHelper.getTableTentitemsName(), null, valoresItensDeTenda);
        db.close();
    }
}