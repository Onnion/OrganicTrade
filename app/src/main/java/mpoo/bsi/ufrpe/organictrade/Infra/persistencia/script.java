package mpoo.bsi.ufrpe.organictrade.infra.persistencia;

import android.database.sqlite.SQLiteDatabase;

import mpoo.bsi.ufrpe.organictrade.controler.user.negocio.Md5;

public class Script {

    public static void populateProductTable(SQLiteDatabase db){
        String columns = "INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+ DatabaseHelper.getColumnProductType()+") VALUES  ";
        db.execSQL(columns +"('Abacate', 'Fruta');");
        db.execSQL(columns +"('Abacaxi', 'Fruta');");
        db.execSQL(columns +"('Açaí', 'Fruta');");
        db.execSQL(columns +"('Acerola', 'Fruta');");
        db.execSQL(columns +"('Ameixa', 'Fruta');");
        db.execSQL(columns +"('Amora', 'Fruta');");
        db.execSQL(columns +"('Banana', 'Fruta');");
        db.execSQL(columns +"('Cajá', 'Fruta');");
        db.execSQL(columns +"('Caju', 'Fruta');");
        db.execSQL(columns +"('Carambola', 'Fruta');");
        db.execSQL(columns +"('Cereja', 'Fruta');");
        db.execSQL(columns +"('Coco', 'Fruta');");
        db.execSQL(columns +"('Cupuaçu', 'Fruta');");
        db.execSQL(columns +"('Damasco', 'Fruta');");
        db.execSQL(columns +"('Fruta Pão', 'Fruta');");
        db.execSQL(columns +"('Goiaba', 'Fruta');");
        db.execSQL(columns +"('Graviola', 'Fruta');");
        db.execSQL(columns +"('Jabuticaba', 'Fruta');");
        db.execSQL(columns +"('Jaca', 'Fruta');");
        db.execSQL(columns +"('Jambo', 'Fruta');");
        db.execSQL(columns +"('Laranja', 'Fruta');");
        db.execSQL(columns +"('Limão', 'Fruta');");
        db.execSQL(columns +"('Lichia', 'Fruta');");
        db.execSQL(columns +"('Maçã', 'Fruta');");
        db.execSQL(columns +"('Manga', 'Fruta');");
        db.execSQL(columns +"('Maracujá', 'Fruta');");
        db.execSQL(columns +"('Melância', 'Fruta');");
        db.execSQL(columns +"('Melão', 'Fruta');");
        db.execSQL(columns +"('Mexerica', 'Fruta');");
        db.execSQL(columns +"('Pêra', 'Fruta');");
        db.execSQL(columns +"('Pêssego', 'Fruta');");
        db.execSQL(columns +"('Pinha', 'Fruta');");
        db.execSQL(columns +"('Pinhão', 'Fruta');");
        db.execSQL(columns +"('Pitanga', 'Fruta');");
        db.execSQL(columns +"('Pitomba', 'Fruta');");
        db.execSQL(columns +"('Romã', 'Fruta');");
        db.execSQL(columns +"('Sapoti', 'Fruta');");
        db.execSQL(columns +"('Tamarindo', 'Fruta');");
        db.execSQL(columns +"('Tangerina', 'Fruta');");
        db.execSQL(columns +"('Tomate', 'Fruta');");
        db.execSQL(columns +"('Toranja', 'Fruta');");
        db.execSQL(columns +"('Umbu', 'Fruta');");
        db.execSQL(columns +"('Uva', 'Fruta');");
        db.execSQL(columns +"('Acelga', 'Verduras');");
        db.execSQL(columns +"('Agrião', 'Verduras');");
        db.execSQL(columns +"('Alcachofra', 'Verduras');");
        db.execSQL(columns +"('Alface', 'Verduras');");
        db.execSQL(columns +"('Aspargo', 'Verduras');");
        db.execSQL(columns +"('Brócolis', 'Verduras');");
        db.execSQL(columns +"('Cebolinha', 'Verduras');");
        db.execSQL(columns +"('Coentro', 'Verduras');");
        db.execSQL(columns +"('Couve', 'Verduras');");
        db.execSQL(columns +"('Espinafre', 'Verduras');");
        db.execSQL(columns +"('Hortelã', 'Verduras');");
        db.execSQL(columns +"('Manjericão', 'Verduras');");
        db.execSQL(columns +"('Mostarda', 'Verduras');");
        db.execSQL(columns +"('Rúcula', 'Verduras');");
        db.execSQL(columns +"('Salsa', 'Verduras');");
        db.execSQL(columns +"('Abóbora', 'Legumes');");
        db.execSQL(columns +"('Abobrinha', 'Legumes');");
        db.execSQL(columns +"('Alho', 'Legumes');");
        db.execSQL(columns +"('Berinjela', 'Legumes');");
        db.execSQL(columns +"('Beterraba', 'Legumes');");
        db.execSQL(columns +"('Cebola', 'Legumes');");
        db.execSQL(columns +"('Cenoura', 'Legumes');");
        db.execSQL(columns +"('Chuchu', 'Legumes');");
        db.execSQL(columns +"('Couve-flor', 'Legumes');");
        db.execSQL(columns +"('Ervilha', 'Legumes');");
        db.execSQL(columns +"('Fava', 'Legumes');");
        db.execSQL(columns +"('Feijão', 'Legumes');");
        db.execSQL(columns +"('Gengibre', 'Legumes');");
        db.execSQL(columns +"('Jiló', 'Legumes');");
        db.execSQL(columns +"('Milho', 'Legumes');");
        db.execSQL(columns +"('Nabo', 'Legumes');");
        db.execSQL(columns +"('Pepino', 'Legumes');");
        db.execSQL(columns +"('Pimenta', 'Legumes');");
        db.execSQL(columns +"('Pimentão', 'Legumes');");
        db.execSQL(columns +"('Quiabo', 'Legumes');");
        db.execSQL(columns +"('Rabanete', 'Legumes');");
        db.execSQL(columns +"('Repolho', 'Legumes');");
        db.execSQL(columns +"('Soja', 'Legumes');");
        db.execSQL(columns +"('Vagem', 'Legumes');");
    }

    public static void standardUsers(SQLiteDatabase db){
        String senhaNeresb = Md5.encrypt("neresb");
        String senhaMarcio = Md5.encrypt("marcio");
        String columns = "INSERT INTO "+DatabaseHelper.getTableUserName()+ " (" +
                DatabaseHelper.getColumnUserName()+","+
                DatabaseHelper.getColumnUserUsername()+","+
                DatabaseHelper.getColumnUserPassword()+","+
                DatabaseHelper.getColumnUserEmail()+
                ") VALUES  ";
        db.execSQL(columns +"('Iago', 'neresb', '"+senhaNeresb+"', 'iago@iago.com');");
        db.execSQL(columns +"('Marcio', 'marcio', '"+senhaMarcio+"', 'marcio@marcio.com');");
    }
}
