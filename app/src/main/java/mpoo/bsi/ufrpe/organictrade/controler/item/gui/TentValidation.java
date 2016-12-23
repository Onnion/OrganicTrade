package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.widget.EditText;

public class TentValidation {
    public boolean validateRegister(EditText name) {
        boolean validateRegister = false;
        String nameStr = name.getText().toString();
        if(nameStr.equals("") || nameStr.length() > 16 || nameStr.length() < 4 ){
            name.setError("");
        }else {
            validateRegister = true;
        }
        return validateRegister;
    }
}
