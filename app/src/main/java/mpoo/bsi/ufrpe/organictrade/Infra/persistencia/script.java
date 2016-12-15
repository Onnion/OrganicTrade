package mpoo.bsi.ufrpe.organictrade.Infra.persistencia;

import android.database.sqlite.SQLiteDatabase;


public class script {
    public static void populateProductTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Abacate', 'Fruta')");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Abacaxi', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Açaí', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Acerola', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Ameixa', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Amora', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Banana', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Cajá', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Caju', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Carambola', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Cereja', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Coco', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Cupuaçu', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Damasco', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Fruta Pão', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Goiaba', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Graviola', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Jabuticaba', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Jaca', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Jambo', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Laranja', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Limão', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Lichia', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Maçã', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Manga', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Maracujá', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Melância', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Melão', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Mexerica', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pêra', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pêssego', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pinha', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pinhão', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pitanga', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pitomba', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Romã', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Sapoti', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Tamarindo', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Tangerina', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Tomate', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Toranja', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Umbu', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Uva', 'Fruta');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Acelga', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Agrião', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Alcachofra', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Alface', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Aspargo', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Brócolis', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Cebolinha', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Coentro', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Couve', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Espinafre', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Hortelã', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Manjericão', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Mostarda', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Rúcula', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Salsa', 'Verduras');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Abóbora', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Abobrinha', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Alho', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Berinjela', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Beterraba', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Cebola', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Cenoura', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Chuchu', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Couve-flor', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Ervilha', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Fava', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Feijão', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Gengibre', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Jiló', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Milho', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Nabo', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pepino', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pimenta', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Pimentão', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Quiabo', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Rabanete', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Repolho', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Soja', 'Legumes');");
        db.execSQL("INSERT INTO "+DatabaseHelper.getTableProductName()+" ("+DatabaseHelper.getColumnProductName()+","+DatabaseHelper.getColumnProductType()+") VALUES  ('Vagem', 'Legumes');");


    }
}
