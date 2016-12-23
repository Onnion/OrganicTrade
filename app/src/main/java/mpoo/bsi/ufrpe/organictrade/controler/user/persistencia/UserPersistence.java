package mpoo.bsi.ufrpe.organictrade.controler.user.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.ComandosSql;
import mpoo.bsi.ufrpe.organictrade.infra.persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.infra.Session;
import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.Product;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.ProductPersistence;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.TentItemsPersistence;
import mpoo.bsi.ufrpe.organictrade.controler.user.dominio.User;

public class UserPersistence {
    private SQLiteDatabase db;
    private DatabaseHelper banco = Session.getDbAtual();

    private User criarUsuario(Cursor cursor) {
        User user = new User();
        user.setIdUser(cursor.getInt(0));
        user.setUserName(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setName(cursor.getString(4));
        user.setPhone(cursor.getLong(5));
        user.setImage(cursor.getBlob(6));
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

    public void registerUser(User user){
        db = banco.getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put(DatabaseHelper.getColumnUserUsername(), user.getUserName());
        userValues.put(DatabaseHelper.getColumnUserPassword(), user.getPassword());
        userValues.put(DatabaseHelper.getColumnUserEmail(), user.getEmail());
        userValues.put(DatabaseHelper.getColumnUserName(), user.getName());
        userValues.put(DatabaseHelper.getColumnUserPhone(),user.getPhone());
        userValues.put(DatabaseHelper.getColumnUserImg(), user.getImage());
        db.insert(DatabaseHelper.getTableUserName(), null, userValues);
        db.close();
    }

    private void userLogin(User user){
        db = banco.getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put(DatabaseHelper.getColumnUserLoggedId(),user.getIdUser());
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
        boolean userNotRegistered = false;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserFromLogin(),new String[]{login});
        if (!cursor.moveToFirst()) {
            userNotRegistered = true;
        }
        cursor.close();
        db.close();
        return userNotRegistered;
    }

    public void userLogoff(){
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlUserLogoff(), new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if(cursor.moveToFirst()) {
            Session.setCurrentUser(null);
            db.execSQL(ComandosSql.sqlLimparTabela());
        }
        cursor.close();
        db.close();
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
        cursor.close();
        db.close();
    }

    public User searchFromId(int id){
        db = banco.getReadableDatabase();
        User user = null;
        Cursor cursorLocal1 = db.rawQuery(ComandosSql.sqlUserFromId(),new String[]{Integer.toString(id)});
        if(cursorLocal1.moveToFirst()){
            user = criarUsuario(cursorLocal1);
        }
        cursorLocal1.close();
        db.close();
        return user;
    }

    public void userEdit(User user){
        db = banco.getWritableDatabase();
        String where = DatabaseHelper.getColumnUserId()+" = "+Session.getCurrentUser().getIdUser();
        ContentValues userEditedValues = new ContentValues();
        userEditedValues.put(DatabaseHelper.getColumnUserName(), user.getName());
        userEditedValues.put(DatabaseHelper.getColumnUserEmail(), user.getEmail());
        userEditedValues.put(DatabaseHelper.getColumnUserPhone(), user.getPhone());
        db.update(DatabaseHelper.getTableUserName(), userEditedValues,where,null);
        db.close();
        userLogoff();
        searchAndLoginUser(user.getUserName(),user.getPassword());
    }

    public void setImageUser(byte[] img){
        db = banco.getWritableDatabase();
        String where = DatabaseHelper.getColumnUserId()+" = "+Session.getCurrentUser().getIdUser();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.getColumnUserImg(), img);
        db.update(DatabaseHelper.getTableUserName(),contentValues, where, null);
        db.close();
    }

    public void registerFavorites(ArrayList<Product> products){
        for(Product product: products) {
            setFavorite(product);
        }
    }

    private void setFavorite(Product product){
        db = banco.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.getColumnUserproductUserId(),Session.getCurrentUser().getIdUser());
        contentValues.put(DatabaseHelper.getColumnUserproductProductId(),product.getProductId());
        db.insert(DatabaseHelper.getTableUserproductName(),null,contentValues);
        db.close();
    }

    public ArrayList<Product> getFavorites(){
        ArrayList<Product> products = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(ComandosSql.sqlGetFavorites(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if(cursor.moveToFirst()){
            ProductPersistence productPersistence = new ProductPersistence();
            do {
                products.add(productPersistence.getProductById(cursor.getInt(1)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

    public ArrayList<TentItem> getSellingHistoryc(){
        db = banco.getReadableDatabase();
        ArrayList<TentItem> tentItems = new ArrayList<>();
        TentItemsPersistence tentItemsPersistence = new TentItemsPersistence();
        Cursor cursor = db.rawQuery(ComandosSql.sqlGetSellingHistoryc(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if (cursor.moveToFirst()){
            do{
                tentItems.add(tentItemsPersistence.createTentItemsById(cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        return tentItems;
    }

    public ArrayList<TentItem> getBuyingHistoryc(){
        db = banco.getReadableDatabase();
        ArrayList<TentItem> tentItems = new ArrayList<>();
        TentItemsPersistence tentItemsPersistence = new TentItemsPersistence();
        Cursor cursor = db.rawQuery(ComandosSql.sqlGetBuyingHistoryc(),new String[]{Integer.toString(Session.getCurrentUser().getIdUser())});
        if (cursor.moveToFirst()){
            do{
                tentItems.add(tentItemsPersistence.createTentItemsById(cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        return tentItems;
    }
}