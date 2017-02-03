package mpoo.bsi.ufrpe.organictrade.controler.item.gui;

import android.widget.EditText;

public class TentValidation {
    private static final int NUMBER_MAX_OF_NAME = 16;
    private static final int NUMER_MIN_OF_NAME = 4;

    public boolean validateRegister(EditText name) {
        boolean validateRegister = false;
        String nameStr = name.getText().toString();
        if(nameStr.equals("") || nameStr.length() > NUMBER_MAX_OF_NAME || nameStr.length() < NUMER_MIN_OF_NAME ){
            name.setError("");
        }else {
            validateRegister = true;
        }
        return validateRegister;
    }
}
