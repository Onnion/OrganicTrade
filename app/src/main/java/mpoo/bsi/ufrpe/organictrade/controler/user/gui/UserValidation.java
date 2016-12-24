package mpoo.bsi.ufrpe.organictrade.controler.user.gui;

import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_VERIFICATION_CHARACTERS = Pattern.compile("^[a-zA-Z\\sà-ùÀ-Ù]{0,}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PHONE_VERIFICATION_CHARACTERS = Pattern.compile("^[1-9]{2}[9]{0,1}[6-9]{1}[0-9]{3}[0-9]{4}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASS_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Za-z0-9]{0,}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern LOGIN_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Za-z]{0,}$", Pattern.CASE_INSENSITIVE);

    private boolean checkUserNameFormat(String login){
        Matcher matcher = LOGIN_VERIFICATION_CHARACTERS.matcher(login);
        return matcher.find();
    }

    private boolean checkPasswordFormat(String pass){
        Matcher matcher = PASS_VERIFICATION_CHARACTERS.matcher(pass);
        return matcher.find();
    }

    private boolean checkPhoneFormat(String phone){
        Matcher matcher = PHONE_VERIFICATION_CHARACTERS.matcher(phone);
        return matcher.find();
    }

    private boolean checkEmailFormat(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean checkNameFormat(String name){
        Matcher matcher = NAME_VERIFICATION_CHARACTERS.matcher(name);
        return matcher.find();
    }

    private boolean validateUserName(EditText username){
        boolean validateUserName = false;
        String userNameStr = username.getText().toString();
        if(userNameStr.equals("")|| 16 < userNameStr.length() || userNameStr.length() < 4 ||!checkUserNameFormat(userNameStr) ){
            username.setError("");
        }else {
            validateUserName = true;
        }
        return validateUserName;
    }

    private boolean validatePassword(EditText password){
        boolean validatePassword = false;
        String passwordStr = password.getText().toString();
        if(passwordStr.equals("") || 16 < passwordStr.length() || passwordStr.length() < 4 || !checkPasswordFormat(passwordStr) ){
            password.setError("");
        }else {
            validatePassword = true;
        }
        return validatePassword;
    }

    private boolean validatePhone(EditText phone){
        boolean validatePhone = false;
        String phoneStr = phone.getText().toString();
        if(phoneStr.equals("") || !checkPhoneFormat(phoneStr) ){
            phone.setError("");
        }else {
            validatePhone = true;
        }
        return validatePhone;
    }

    private boolean validateMatchPasswords(EditText password, EditText rpassword){
        boolean validateMatchPasswords = false;
        if(password.getText().toString().equals(rpassword.getText().toString()) && rpassword.getText()!=null){
            validateMatchPasswords = true;
        }else {
            rpassword.setError("");
        }
        return validateMatchPasswords;
    }

    private boolean validateEmail(EditText email){
        boolean validateEmail = false;
        String emailStr = email.getText().toString();
        if(emailStr.equals("") || !checkEmailFormat(emailStr) ){
            email.setError("");
        }else {
            validateEmail = true;
        }
        return validateEmail;
    }

    private boolean validateName(EditText name){
        boolean validateName = false;
        String nameStr = name.getText().toString();
        if(nameStr.equals("") || !checkNameFormat(nameStr) ){
            name.setError("");
        }else {
            validateName = true;
        }
        return validateName;
    }

    public boolean validateLogin(EditText username, EditText password){
        return validateUserName(username) && validatePassword(password);
    }

    public boolean validateRegister(EditText name, EditText username, EditText password,
                                    EditText rpassword, EditText email, EditText phone){
        return
            validateName(name) &&
            validateUserName(username) &&
            validatePassword(password) &&
            validateMatchPasswords(password,rpassword) &&
            validateEmail(email) &&
            validatePhone(phone);
    }

    public boolean validateEdit(EditText name, EditText email, EditText phone){
        return validateName(name) &&
                validateEmail(email) &&
                validatePhone(phone);
    }

}



