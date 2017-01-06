package mpoo.bsi.ufrpe.organictrade.infra.persistencia;

import android.database.sqlite.SQLiteDatabase;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.Md5;

public class Script {

    private static String columnInsertUser = "INSERT INTO " + DatabaseHelper.getTableUserName() + " (" +
            DatabaseHelper.getColumnUserName() +" , " +
            DatabaseHelper.getColumnUserUsername() +" , " +
            DatabaseHelper.getColumnUserPassword() +" , " +
            DatabaseHelper.getColumnUserEmail() +
            ") VALUES  ";

    private static String columnInsertProduct = "INSERT INTO " + DatabaseHelper.getTableProductName() + " (" +
            DatabaseHelper.getColumnProductName() + " , " +
            DatabaseHelper.getColumnProductType() +
            ") VALUES  ";

    private static String columnInsertTent = "INSERT INTO " + DatabaseHelper.getTableTentName() + " (" +
            DatabaseHelper.getColumnTentLagi() + " , " +
            DatabaseHelper.getColumnTentLongi() + " , " +
            DatabaseHelper.getColumnTentUserId() + " , " +
            DatabaseHelper.getColumnTentName() +
            ") VALUES ";

    private static String columnInsertTentItem = "INSERT INTO " + DatabaseHelper.getTableTentitemsName() + " (" +
            DatabaseHelper.getColumnTentitemsAmount() + " , " +
            DatabaseHelper.getColumnTentitemsPrice() + " , " +
            DatabaseHelper.getColumnTentitemsUnity() + " , " +
            DatabaseHelper.getColumnTentitemsProductId() + " , " +
            DatabaseHelper.getColumnTentitemsTentId() +
            ") VALUES ";

    private static String columnInsertTransaction = "INSERT INTO " + DatabaseHelper.getTableTransactionName() + " (" +
            DatabaseHelper.getColumnTransactionDate() + " , " +
            DatabaseHelper.getColumnTransactionUserSellingId() + " , " +
            DatabaseHelper.getColumnTransactionUserBuyingId() + " , " +
            DatabaseHelper.getColumnTransactionTentitemId() +
            ") VALUES ";


    public static void populateProductTable(SQLiteDatabase db){
        db.execSQL(columnInsertProduct +"('Abacate', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Abacaxi', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Açaí', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Acerola', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Ameixa', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Amora', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Banana', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Cajá', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Caju', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Carambola', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Cereja', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Coco', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Cupuaçu', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Damasco', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Fruta Pão', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Goiaba', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Graviola', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Jabuticaba', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Jaca', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Jambo', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Laranja', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Limão', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Lichia', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Maçã', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Manga', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Maracujá', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Melância', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Melão', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Mexerica', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Pêra', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Pêssego', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Pinha', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Pinhão', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Pitanga', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Pitomba', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Romã', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Sapoti', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Tamarindo', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Tangerina', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Tomate', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Toranja', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Umbu', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Uva', 'Fruta');");
        db.execSQL(columnInsertProduct +"('Acelga', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Agrião', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Alcachofra', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Alface', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Aspargo', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Brócolis', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Cebolinha', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Coentro', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Couve', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Espinafre', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Hortelã', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Manjericão', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Mostarda', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Rúcula', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Salsa', 'Verduras');");
        db.execSQL(columnInsertProduct +"('Abóbora', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Abobrinha', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Alho', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Berinjela', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Beterraba', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Cebola', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Cenoura', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Chuchu', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Couve-flor', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Ervilha', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Fava', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Feijão', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Gengibre', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Jiló', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Milho', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Nabo', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Pepino', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Pimenta', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Pimentão', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Quiabo', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Rabanete', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Repolho', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Soja', 'Legumes');");
        db.execSQL(columnInsertProduct +"('Vagem', 'Legumes');");
    }

    public static void standardUsers(SQLiteDatabase db){
        String senhaNeresb = Md5.encrypt("neresb");
        String senhaMarcio = Md5.encrypt("marcio");
        db.execSQL(columnInsertUser +"('Iago', 'neresb', '"+senhaNeresb+"', 'iago@iago.com');");
        db.execSQL(columnInsertUser +"('Marcio', 'marcio', '"+senhaMarcio+"', 'marcio@marcio.com');");
    }

    private static void userForRecomendation(SQLiteDatabase db){
        String senhauser1= Md5.encrypt("user1");
        String senhauser2 = Md5.encrypt("user2");
        String senhauser3 = Md5.encrypt("user3");
        String senhauser4 = Md5.encrypt("user4");
        String senhauser5 = Md5.encrypt("user5");
        String senhauser6 = Md5.encrypt("user6");
        String senhauser7 = Md5.encrypt("user7");
        db.execSQL(columnInsertUser +"('user1', 'user1', '"+senhauser1+"', 'user1@user1.com');");
        db.execSQL(columnInsertUser +"('user2', 'user2', '"+senhauser2+"', 'user2@user2.com');");
        db.execSQL(columnInsertUser +"('user3', 'user3', '"+senhauser3+"', 'user3@user3.com');");
        db.execSQL(columnInsertUser +"('user4', 'user4', '"+senhauser4+"', 'user4@user4.com');");
        db.execSQL(columnInsertUser +"('user5', 'user5', '"+senhauser5+"', 'user5@user5.com');");
        db.execSQL(columnInsertUser +"('user6', 'user6', '"+senhauser6+"', 'user6@user6.com');");
        db.execSQL(columnInsertUser +"('user7', 'user7', '"+senhauser7+"', 'user7@user7.com');");
    }

    private static void tentsForRecomendation(SQLiteDatabase db){
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','3','tentUser2');");
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','4','tentUser3');");
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','5','tentUser4');");
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','6','tentUser5');");
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','7','tentUser6');");
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','8','tentUser7');");
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','9','tentUser8');");
    }

    private static void itensForRecomendation(SQLiteDatabase db){
//db.execSQL(columnInsertTentItem+"('amount','price','unity','productid','tentid');");
        db.execSQL(columnInsertTentItem+"('1','2','g','1','0');");
        db.execSQL(columnInsertTentItem+"('1','2','g','2','0');");
        db.execSQL(columnInsertTentItem+"('1','2','g','3','0');");
        db.execSQL(columnInsertTentItem+"('1','2','g','5','0');");
        db.execSQL(columnInsertTentItem+"('1','2','g','8','0');");

        db.execSQL(columnInsertTentItem+"('1','2','g','8','1');");
        db.execSQL(columnInsertTentItem+"('1','2','g','9','1');");
        db.execSQL(columnInsertTentItem+"('1','2','g','17','1');");
        db.execSQL(columnInsertTentItem+"('1','2','g','26','1');");
        db.execSQL(columnInsertTentItem+"('1','2','g','43','1');");

        db.execSQL(columnInsertTentItem+"('1','2','g','3','2');");
        db.execSQL(columnInsertTentItem+"('1','2','g','4','2');");
        db.execSQL(columnInsertTentItem+"('1','2','g','7','2');");
        db.execSQL(columnInsertTentItem+"('1','2','g','11','2');");
        db.execSQL(columnInsertTentItem+"('1','2','g','18','2');");

        db.execSQL(columnInsertTentItem+"('1','2','g','2','3');");
        db.execSQL(columnInsertTentItem+"('1','2','g','3','3');");
        db.execSQL(columnInsertTentItem+"('1','2','g','5','3');");
        db.execSQL(columnInsertTentItem+"('1','2','g','8','3');");
        db.execSQL(columnInsertTentItem+"('1','2','g','13','3');");

        db.execSQL(columnInsertTentItem+"('1','2','g','4','4');");
        db.execSQL(columnInsertTentItem+"('1','2','g','5','4');");
        db.execSQL(columnInsertTentItem+"('1','2','g','9','4');");
        db.execSQL(columnInsertTentItem+"('1','2','g','14','4');");
        db.execSQL(columnInsertTentItem+"('1','2','g','23','4');");

        db.execSQL(columnInsertTentItem+"('1','2','g','0','5');");
        db.execSQL(columnInsertTentItem+"('1','2','g','1','5');");
        db.execSQL(columnInsertTentItem+"('1','2','g','2','5');");
        db.execSQL(columnInsertTentItem+"('1','2','g','3','5');");
        db.execSQL(columnInsertTentItem+"('1','2','g','6','5');");

        db.execSQL(columnInsertTentItem+"('1','2','g','3','6');");
        db.execSQL(columnInsertTentItem+"('1','2','g','4','6');");
        db.execSQL(columnInsertTentItem+"('1','2','g','7','6');");
        db.execSQL(columnInsertTentItem+"('1','2','g','11','6');");
        db.execSQL(columnInsertTentItem+"('1','2','g','18','6');");
    }

    private static void transactionsForRecomendation(SQLiteDatabase db){
    //db.execSQL(columnInsertTentItem+"('data','userSelling','userBuying','itemId');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','0','1');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','0','2');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','4','1','3');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','1','5');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','3','1','8');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','5','2','13');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','3','2','9');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','2','2');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','2','3');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','3','5');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','3','3','8');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','5','3','13');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','3','3');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','8','3','4');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','8','4','7');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','4','4','11');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','4','4','18');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','8','4','4');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','5','5');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','3','5','9');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','6','5','14');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','6','6','23');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','2','6','5');");

        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','7','7','6');");

        //marcio transactions
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','7','0','0');");
        db.execSQL(columnInsertTransaction+"('"+Util.getDate()+"','7','0','6');");
    }

    public static void loadRecomendation(SQLiteDatabase db){
        userForRecomendation(db);
        tentsForRecomendation(db);
        itensForRecomendation(db);
        transactionsForRecomendation(db);
    }

}
