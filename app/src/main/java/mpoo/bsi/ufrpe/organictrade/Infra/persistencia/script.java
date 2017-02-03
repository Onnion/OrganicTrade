package mpoo.bsi.ufrpe.organictrade.infra.persistencia;

import android.database.sqlite.SQLiteDatabase;
import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.Md5;

public final class Script {

    private Script(){}

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
        db.execSQL(columnInsertUser +"('Iago', 'neresb', '"+senhaNeresb+"', 'iago@iago.com');");//1
        db.execSQL(columnInsertUser +"('Marcio', 'marcio', '"+senhaMarcio+"', 'marcio@marcio.com');");//1
    }

    private static void userForRecomendation(SQLiteDatabase db){
        String senhauser1= Md5.encrypt("user3");
        String senhauser2 = Md5.encrypt("user4");
        String senhauser3 = Md5.encrypt("user5");
        String senhauser4 = Md5.encrypt("user6");
        String senhauser5 = Md5.encrypt("user7");
        String senhauser6 = Md5.encrypt("user8");
        String senhauser7 = Md5.encrypt("user9");
        db.execSQL(columnInsertUser +"('user3', 'user3', '"+senhauser1+"', 'user3@user3.com');");//3
        db.execSQL(columnInsertUser +"('user4', 'user4', '"+senhauser2+"', 'user4@user4.com');");//4
        db.execSQL(columnInsertUser +"('user5', 'user5', '"+senhauser3+"', 'user5@user5.com');");//5
        db.execSQL(columnInsertUser +"('user6', 'user6', '"+senhauser4+"', 'user6@user6.com');");//6
        db.execSQL(columnInsertUser +"('user7', 'user7', '"+senhauser5+"', 'user7@user7.com');");//7
        db.execSQL(columnInsertUser +"('user8', 'user8', '"+senhauser6+"', 'user8@user8.com');");//8
        db.execSQL(columnInsertUser +"('user9', 'user9', '"+senhauser7+"', 'user9@user9.com');");//9
    }

    private static void tentsForRecomendation(SQLiteDatabase db){
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','3','tentUser3');");//1
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','4','tentUse4');");//2
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','5','tentUser5');");//3
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','6','tentUser6');");//4
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','7','tentUser7');");//5
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','8','tentUser8');");//6
        db.execSQL(columnInsertTent+"('-7.9927813','-34.8592229','9','tentUser9');");//7
    }

    private static void itensForRecomendation(SQLiteDatabase db){
//db.execSQL(columnInsertTentItem+"('amount','price','unity','productid','tentid');");

        //3
        db.execSQL(columnInsertTentItem+"('1','2','g','1','1');");//1
        db.execSQL(columnInsertTentItem+"('1','2','g','2','1');");//2
        db.execSQL(columnInsertTentItem+"('1','2','g','3','1');");//3
        db.execSQL(columnInsertTentItem+"('1','2','g','5','1');");//4
        db.execSQL(columnInsertTentItem+"('1','2','g','8','1');");//5
        //4
        db.execSQL(columnInsertTentItem+"('1','2','g','8','2');");//6
        db.execSQL(columnInsertTentItem+"('1','2','g','9','2');");//7
        db.execSQL(columnInsertTentItem+"('1','2','g','17','2');");//8
        db.execSQL(columnInsertTentItem+"('1','2','g','26','2');");//9
        db.execSQL(columnInsertTentItem+"('1','2','g','43','2');");//10
        //5
        db.execSQL(columnInsertTentItem+"('1','2','g','3','3');");//11
        db.execSQL(columnInsertTentItem+"('1','2','g','4','3');");//12
        db.execSQL(columnInsertTentItem+"('1','2','g','7','3');");//13
        db.execSQL(columnInsertTentItem+"('1','2','g','11','3');");//14
        db.execSQL(columnInsertTentItem+"('1','2','g','18','3');");//15
        //6
        db.execSQL(columnInsertTentItem+"('1','2','g','2','4');");//16
        db.execSQL(columnInsertTentItem+"('1','2','g','3','4');");//17
        db.execSQL(columnInsertTentItem+"('1','2','g','5','4');");//18
        db.execSQL(columnInsertTentItem+"('1','2','g','8','4');");//19
        db.execSQL(columnInsertTentItem+"('1','2','g','13','4');");//20
        //7
        db.execSQL(columnInsertTentItem+"('1','2','g','4','5');");//21
        db.execSQL(columnInsertTentItem+"('1','2','g','5','5');");//22
        db.execSQL(columnInsertTentItem+"('1','2','g','9','5');");//23
        db.execSQL(columnInsertTentItem+"('1','2','g','14','5');");//24
        db.execSQL(columnInsertTentItem+"('1','2','g','23','5');");//25
        //8
        db.execSQL(columnInsertTentItem+"('1','2','g','7','6');");//26
        db.execSQL(columnInsertTentItem+"('1','2','g','1','6');");//27
        db.execSQL(columnInsertTentItem+"('1','2','g','2','6');");//28
        db.execSQL(columnInsertTentItem+"('1','2','g','3','6');");//29
        db.execSQL(columnInsertTentItem+"('1','2','g','6','6');");//30
        //9
        db.execSQL(columnInsertTentItem+"('1','2','g','3','7');");//31
        db.execSQL(columnInsertTentItem+"('1','2','g','4','7');");//32
        db.execSQL(columnInsertTentItem+"('1','2','g','7','7');");//33
        db.execSQL(columnInsertTentItem+"('1','2','g','11','7');");//34
        db.execSQL(columnInsertTentItem+"('1','2','g','18','7');");//35
    }

    private static void transactionsForRecomendation(SQLiteDatabase db){
    //db.execSQL(columnInsertTentItem+"('data','userSelling','userBuying','itemId');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','6','3','20');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','6','3','16');");

        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','5','4','23');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','3','4','12');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','6','4','19');");

        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','6','5','20');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','4','5','7');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','3','5','2');");

        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','3','6','4');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','4','6','6');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','9','6','32');");

        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','9','7','33');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','5','7','14');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','5','7','15');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','9','7','32');");

        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','3','8','4');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','4','8','7');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','7','8','24');");

        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','7','9','25');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','3','9','4');");

        //marcio transactions
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','6','2','16');");
        db.execSQL(columnInsertTransaction+"('"+ UtilInfraPersistence.getDate()+"','9','2','35');");
    }

    public static void loadRecomendation(SQLiteDatabase db){
        userForRecomendation(db);
        tentsForRecomendation(db);
        itensForRecomendation(db);
        transactionsForRecomendation(db);
    }

}
