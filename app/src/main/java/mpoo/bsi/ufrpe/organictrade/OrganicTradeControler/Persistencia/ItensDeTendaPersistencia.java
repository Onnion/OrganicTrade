package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItensDeTenda;

public class ItensDeTendaPersistencia {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public ItensDeTenda criarItensDeTenda(Cursor cursor){
        ItensDeTenda itensDeTenda = new ItensDeTenda();
        itensDeTenda.setIntensdetenda_id(cursor.getString(0));
        itensDeTenda.setQuantidadeAtual(cursor.getString(1));
        itensDeTenda.setValor(cursor.getString(2));
        itensDeTenda.setNomeProduto(cursor.getString(3));
        itensDeTenda.setUsurio_id(cursor.getString(4));
        return itensDeTenda;
    }

    public void inserirItensDeTenda(ItensDeTenda itensDeTenda){
        db = banco.getWritableDatabase();
        ContentValues valoresItensDeTenda = new ContentValues();
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaId(),itensDeTenda.getIntensdetenda_id());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaQuantidade(), itensDeTenda.getQuantidadeAtual());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaValor(), itensDeTenda.getValor());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaNomePoroduto(), itensDeTenda.getNomeProduto());
        valoresItensDeTenda.put(DatabaseHelper.getColumnItensdetendaUserId(),Session.getUserAtual().getId());
        db.insert(DatabaseHelper.getTableItensdetendaName(), null, valoresItensDeTenda);
        db.close();
    }
}