package mpoo.bsi.ufrpe.organictrade.controler.item.negocio;

import android.widget.EditText;

import mpoo.bsi.ufrpe.organictrade.controler.item.persistencia.TentItemsPersistence;

public class TentsItemsNegocio {
    private static TentItemsPersistence tentItemsPersistence = new TentItemsPersistence();


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


    public static TentItemsPersistence getTentItemsPersistence(){
        return tentItemsPersistence;
    }

}