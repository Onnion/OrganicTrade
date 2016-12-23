package mpoo.bsi.ufrpe.organictrade.controler.item.negocio;

import android.widget.EditText;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.organictrade.controler.item.dominio.TentItem;
import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.TentItemsPersistence;

public class TentItemNegocio {
    private TentItemsPersistence tentitemspersistence = new TentItemsPersistence();

    public void deleteAllItemsOfTent(int tentId) {
        tentitemspersistence.deleteAllItemsOfTent(tentId);
    }

    public static boolean registerItemItsOk (EditText amount, EditText price){
        return (amountOk(amount) && priceOk(price));
    }

    private static boolean amountOk(EditText amount) {
        boolean ok;
        if(amount.getText().toString().equals("")){
            amount.setError("Quantidade inválida");
            ok = false;
        }else{
            ok = true;
        }
        return ok;
    }

    private static boolean priceOk(EditText price){
        boolean ok;
        if (price.getText().toString().equals("")){
            price.setError("Valor inválido");
            ok = false;
        }else{
            ok = true;
        }
        return ok;

    }

    public void tentItemEdit(TentItem tentItemToEdit) {
        tentitemspersistence.tentItemEdit(tentItemToEdit);
    }

    public void insertTentItems(TentItem tentItem) {
        tentitemspersistence.insertTentItems(tentItem);
    }

    public ArrayList<TentItem> getAllItems(int idUser) {
        return tentitemspersistence.getAllItems(idUser);
    }

    public void deleteTentItems(int tentItemsId) {
        tentitemspersistence.deleteTentItems(tentItemsId);
    }

    public ArrayList<TentItem> getItemsOfTent(int tentId) {
        return tentitemspersistence.getItemsOfTent(tentId);
    }
}