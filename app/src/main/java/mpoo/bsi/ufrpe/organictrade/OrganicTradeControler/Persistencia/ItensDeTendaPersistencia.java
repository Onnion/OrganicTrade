package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.TentItems;

public class ItensDeTendaPersistencia {
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
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaId(), tentItems.getIntensdetenda_id());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaQuantidade(), tentItems.getQuantidadeAtual());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaValor(), tentItems.getValor());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaNomePoroduto(), tentItems.getNomeProduto());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaUserId(),Session.getUserAtual().getId_user());
        db.insert(DatabaseHelper.getTableItensdetendaName(), null, valoresItensDeTenda);
        db.close();
    }
}