package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.widget.EditText;

public class TentItemValidation {

    public boolean validateEdit(EditText amount, EditText price) {
        return validateAmount(amount) && validatePrice(price);
    }

    private boolean validateAmount(EditText amount) {
        boolean validateAmount = false;
        String amountStr = amount.getText().toString();
        if(amountStr.equals("") || !checkAmountFormat(amountStr) ){
            amount.setError("");
        }else {
            validateAmount = true;
        }
        return validateAmount;
    }

    private boolean validatePrice(EditText price) {
        boolean validatePrice = false;
        String priceStr = price.getText().toString();
        if(priceStr.equals("")){
            price.setError("");
        }else {
            validatePrice = true;
        }
        return validatePrice;
    }

    private boolean checkAmountFormat(String amountStr) {
        return amountStr.length() > 4;
    }
}
