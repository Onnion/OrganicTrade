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
        tentItems.setUnity(cursor.getString(3));
        tentItems.setProdutoId(cursor.getString(4));
        tentItems.setUsurio_id(cursor.getString(5));

        return tentItems;
    }

    public void inserirItensDeTenda(TentItems tentItems){
        db = banco.getWritableDatabase();
        ContentValues valoresItensDeTenda = new ContentValues();
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsId(), tentItems.getIntensdetenda_id());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsAmount(), tentItems.getQuantidadeAtual());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsPrice(), tentItems.getValor());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsUnity(), tentItems.getUnity());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsUserId(),Session.getUserAtual().getId_user());
        valoresItensDeTenda.put(DatabaseHelper.getColumnTentitemsProductId(),tentItems.getProdutoId());
        db.insert(DatabaseHelper.getTableTentitemsName(), null, valoresItensDeTenda);
        db.close();
    }
}