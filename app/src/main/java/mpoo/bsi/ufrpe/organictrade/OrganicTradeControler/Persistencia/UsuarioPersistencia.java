package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.Usuario;

public class UsuarioPersistencia {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public Usuario criarUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getString(0));
        usuario.setUname(cursor.getString(1));
        usuario.setPass(cursor.getString(2));
        usuario.setEmail(cursor.getString(3));
        usuario.setNome(cursor.getString(4));
        return usuario;
    }

    public boolean buscarELogarUsuario(String username, String senha) {

        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUsuarioApartirDoLoginESenha(), new String[]{username,senha});
        if(cursor.moveToFirst()) {
            Usuario usuario = criarUsuario(cursor);
            //this.logarUsuario(usuario);
            Session.setUserAtual(usuario);
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public void inserirUsuario(Usuario user){
        db = banco.getWritableDatabase();
        ContentValues valoresUsuario = new ContentValues();
        valoresUsuario.put(DatabaseHelper.getColumnUserId(),user.getId());
        valoresUsuario.put(DatabaseHelper.getColumnUserName(), user.getUname());
        valoresUsuario.put(DatabaseHelper.getColumnUserPass(), user.getPass());
        valoresUsuario.put(DatabaseHelper.getColumnUserEmail(), user.getEmail());
        valoresUsuario.put(DatabaseHelper.getColumnUserNome(), user.getNome());
        db.insert(DatabaseHelper.getTableUserName(), null, valoresUsuario);
        db.close();
    }

    public void logarUsuario(Usuario user){
        db = banco.getWritableDatabase();
        ContentValues valoresUsuario = new ContentValues();
        valoresUsuario.put(DatabaseHelper.getColumnUserLogadoId(),user.getId());
        db.insert(DatabaseHelper.getTableUserLogadoName(), null, valoresUsuario);
        db.close();
    }

    public boolean usuarioLogado() {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUsuarioLogado(),null);
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public void deslogarUsuario(String userId){

        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlDeslogarUsuario(), new String[]{userId});
        if(cursor.moveToFirst()) {
            Session.setUserAtual(null);
            db.execSQL(ComandosSql.sqlDropTableUsuarioLogado());
        }
    }
}
