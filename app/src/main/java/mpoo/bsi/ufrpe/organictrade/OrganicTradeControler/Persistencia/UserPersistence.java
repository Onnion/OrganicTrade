package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio.User;
import mpoo.bsi.ufrpe.organictrade.R;

public class UserPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    public User criarUsuario(Cursor cursor) {
        User user = new User();
        user.setId_user(cursor.getString(0));
        user.setUserName(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setName(cursor.getString(4));
        user.setPhone(cursor.getString(5));
        user.setAdress(cursor.getString(6));
        return user;
    }

    public boolean searchAndLoginUser(String username, String senha) {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserFromLoginAndPass(), new String[]{username,senha});
        if(cursor.moveToFirst()) {
            User user = criarUsuario(cursor);
            userLogin(user);
            Session.setCurrentUser(user);
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public void RegisterUser(User user){
        db = banco.getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put(DatabaseHelper.getColumnUserId(),user.getId_user());
        userValues.put(DatabaseHelper.getColumnUserUsername(), user.getUserName());
        userValues.put(DatabaseHelper.getColumnUserPassword(), user.getPassword());
        userValues.put(DatabaseHelper.getColumnUserEmail(), user.getEmail());
        userValues.put(DatabaseHelper.getColumnUserName(), user.getName());
        userValues.put(DatabaseHelper.getColumnUserPhone(),user.getPhone());
        userValues.put(DatabaseHelper.getColumnUserAdress(),user.getAdress());
        db.insert(DatabaseHelper.getTableUserName(), null, userValues);
        db.close();
    }

    public void userLogin(User user){
        db = banco.getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put(DatabaseHelper.getColumnUserLoggedId(),user.getId_user());
        db.insert(DatabaseHelper.getTableUserLoggedName(), null, userValues);
        db.close();
    }

    public boolean userLogged() {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserLogged(),null);
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean userNotRegistered(String login) {
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserFromLogin(),new String[]{login});
        if (cursor.moveToFirst()) {
            Toast toast = Toast.makeText(Session.getContext(), R.string.tstUnavaliableLogin, Toast.LENGTH_LONG);
            toast.show();
            cursor.close();
            Toast toast2 = Toast.makeText(Session.getContext(), R.string.tstUnavaliableLogin, Toast.LENGTH_LONG);
            toast2.show();
            db.close();
            return false;
        }else {
            cursor.close();
            db.close();
            return true;
        }
    }

    public void userLogoff(){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserLogoff(), new String[]{Session.getCurrentUser().getId_user()});
        if(cursor.moveToFirst()) {
            Session.setCurrentUser(null);
            db.execSQL(ComandosSql.sqlLimparTabela());
        }
    }

    public void searchFromUserLogged(){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlSearchInBaseFromLoggedUser(), null);
        if(cursor.moveToFirst()){
            Cursor user = db.rawQuery(ComandosSql.sqlUserFromId(), new String[]{cursor.getString(0)});
            if (user.moveToFirst()){
                User userLogged = criarUsuario(user);
                Session.setCurrentUser(userLogged);
            }
        }
    }

    public User searchFromId(String id){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserFromId(),new String[]{id});
        if(cursor.moveToFirst()){
            return criarUsuario(cursor);
        }else return null;
    }

    public void userEdit(User user){
        db = banco.getWritableDatabase();
        String where = DatabaseHelper.getColumnUserId()+" = "+Session.getCurrentUser().getId_user();
        ContentValues userEditedValues = new ContentValues();
        userEditedValues.put(DatabaseHelper.getColumnUserName(), user.getName());
        userEditedValues.put(DatabaseHelper.getColumnUserPassword(), user.getPassword());
        userEditedValues.put(DatabaseHelper.getColumnUserEmail(), user.getEmail());
        userEditedValues.put(DatabaseHelper.getColumnUserPhone(), user.getPhone());
        db.update(DatabaseHelper.getTableUserName(), userEditedValues,where,null);
        db.close();
        userLogoff();
        searchAndLoginUser(user.getUserName(), user.getPassword());
    }
}
