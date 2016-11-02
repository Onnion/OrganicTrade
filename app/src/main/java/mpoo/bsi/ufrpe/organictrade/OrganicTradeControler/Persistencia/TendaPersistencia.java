package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.ItensDeTenda;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Tenda;
public class TendaPersistencia {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public Tenda retornarTendaDoUsuario(){
        Tenda tenda = new Tenda();
        ItensDeTendaPersistencia crud = new ItensDeTendaPersistencia();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUsuarioQueTemOItem(), new String[]{Session.getUserAtual().getId()});
        if(cursor.moveToFirst()){
            do{
                ItensDeTenda itensDeTenda = crud.criarItensDeTenda(cursor);
                tenda.setItensDeTendas(itensDeTenda);
            }while(cursor.moveToNext());
        }
        return tenda;
    }
    public Tenda retornarTendaDosUsuarios(){
        Tenda tenda = new Tenda();
        ItensDeTendaPersistencia crud = new ItensDeTendaPersistencia();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlTodosOsItens(), new String[]{Session.getUserAtual().getId()});
        if(cursor.moveToFirst()){
            do{
                ItensDeTenda itensDeTenda = crud.criarItensDeTenda(cursor);
                tenda.setItensDeTendas(itensDeTenda);
            }while(cursor.moveToNext());
        }
        return tenda;
    }

}
