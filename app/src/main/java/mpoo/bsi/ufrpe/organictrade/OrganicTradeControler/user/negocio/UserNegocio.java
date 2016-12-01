package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.user.negocio;

import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mpoo.bsi.ufrpe.organictrade.Infra.persistencia.DatabaseHelper;
public class UserNegocio{
    private SQLiteDatabase db;
    private DatabaseHelper banco;
    private EditText loginEdtLogin, loginEdtPass, registerUserEdtName,registerUserEdtPhone, registerUserEdtLogin, registerUserEdtPass, registerUserEdtRpass, registerUserEdtEmail;
    private String sl_login, sl_pass, sc_name, sc_login, sc_pass, sc_rpass, sc_email,sc_phone;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_VERIFICATION_CHARACTERS = Pattern.compile("^[a-zA-Z\\sà-ùÀ-Ù]{0,}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PHONE_VERIFICATION_CHARACTERS = Pattern.compile("^[1-9]{2}[9]{0,1}[6-9]{1}[0-9]{3}[0-9]{4}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASS_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Za-z0-9]{0,}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern LOGIN_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Za-z]{0,}$", Pattern.CASE_INSENSITIVE);

    public void initializeLogin(EditText loginEdtLogin, EditText loginEdtPass){
        this.loginEdtLogin = loginEdtLogin;
        this.loginEdtPass = loginEdtPass;
        sl_login = loginEdtLogin.getText().toString();
        sl_pass = loginEdtPass.getText().toString();
    }

    public void initializeRegister(EditText registerUserEdtEmail, EditText registerUserEdtLogin, EditText registerUserEdtName, EditText registerUserEdtPass, EditText registerUserEdtPhone, EditText registerUserEdtRpass){
        this.registerUserEdtEmail = registerUserEdtEmail;
        this.registerUserEdtLogin = registerUserEdtLogin;
        this.registerUserEdtName = registerUserEdtName;
        this.registerUserEdtPass = registerUserEdtPass;
        this.registerUserEdtPhone = registerUserEdtPhone;
        this.registerUserEdtRpass = registerUserEdtRpass;
        sc_name = registerUserEdtName.getText().toString();
        sc_login = registerUserEdtLogin.getText().toString();
        sc_pass = registerUserEdtPass.getText().toString();
        sc_rpass = registerUserEdtRpass.getText().toString();
        sc_email = registerUserEdtEmail.getText().toString();
        sc_phone = registerUserEdtPhone.getText().toString();
    }

    public void initializeEdit(EditText Name, EditText Pass, EditText Email, EditText Phone){
        this.registerUserEdtName = Name;
        this.registerUserEdtPass = Pass;
        this.registerUserEdtEmail = Email;
        this.registerUserEdtPhone = Phone;
        sc_name = registerUserEdtName.getText().toString();
        sc_pass = registerUserEdtPass.getText().toString();
        sc_email = registerUserEdtEmail.getText().toString();
        sc_phone = registerUserEdtPhone.getText().toString();
    }

    public boolean loginOK(EditText login, EditText pass){
        initializeLogin(login,pass);
        if (!loginValidate()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean registerOK(EditText email, EditText login, EditText name, EditText pass, EditText phone, EditText rpass ){
        initializeRegister(email,login,name,pass,phone,rpass);
        if (!validateRegister()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean editOk(EditText name,EditText pass, EditText email, EditText phone){
        initializeEdit(name,pass,email,phone);
        if (!editValidate()){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean invalidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if( matcher.find()){
            return true;
        }else{
            return false;
        }
    }

    private static boolean invalidName(String name){
        Matcher matcher = NAME_VERIFICATION_CHARACTERS.matcher(name);
        if (matcher.find()){
            return true;
        }else{
            return false;
        }
    }

    private static boolean invalidPass(String pass){
        Matcher matcher = PASS_VERIFICATION_CHARACTERS.matcher(pass);
        if (matcher.find()){
            return true;
        }else{
            return false;
        }
    }

    private static boolean validLogin(String login){
        Matcher matcher = LOGIN_VERIFICATION_CHARACTERS.matcher(login);
        if (matcher.find()){
            return true;
        }else{
            return false;
        }
    }

    private static boolean invalidPhone(String phone){
        Matcher matcher = PHONE_VERIFICATION_CHARACTERS.matcher(phone);
        if (matcher.find()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validateRegister(){
        boolean tc_valid = true;
        if(sc_name.isEmpty() || sc_name.length() < 4 ||(!invalidName(sc_name))){
            registerUserEdtName.setError("Please Enter with a valid name");
            tc_valid = false;
        }
        if(sc_login.length() == 0 || sc_login.length() < 4 || sc_rpass.length() > 16 || (!validLogin(sc_login))){
            registerUserEdtLogin.setError("Please Enter with a valid login");
            tc_valid = false;
        }
        if(sc_pass.length()  == 0|| sc_pass.length() < 6 || sc_rpass.length() > 16 || (!invalidPass(sc_pass))){
            registerUserEdtPass.setError("Please Enter with a valid pass");
            tc_valid = false;
        }
        if(sc_rpass.length() == 0 || sc_rpass.length() < 6 || sc_rpass.length() > 16 || (!invalidPass(sc_rpass))){
            registerUserEdtRpass.setError("Please Enter with a valid pass");
            tc_valid = false;
        }
        if(sc_email.length() == 0 || (!invalidEmail(sc_email))){
            registerUserEdtEmail.setError("Please Enter with a valid email");
            tc_valid = false;
        }
        if(sc_phone.length() == 0 || (!invalidPhone(sc_phone))){
            registerUserEdtPhone.setError("xx 9 99999999 - xx 99999999");
            tc_valid = false;
        }
        return tc_valid;
    }

    public boolean loginValidate(){
        boolean tl_valid = true;
        if(sl_login.length() == 0 || sl_login.length() < 4 ){
            loginEdtLogin.setError("Please Enter with a valid login");
            tl_valid = false;
        }
        if(sl_pass.length() == 0 || sl_pass.length() < 6 || sl_pass.length() >16 ){
            loginEdtPass.setError("Please Enter with a valid pass");
            tl_valid = false;
        }
        return tl_valid;
    }

    public boolean editValidate(){
        boolean tc_valid = true;
        if(sc_name.isEmpty() || sc_name.length() < 4 ||(!invalidName(sc_name))){
            registerUserEdtName.setError("Please Enter with a valid name");
            tc_valid = false;
        }
        if(sc_pass.length()  == 0|| sc_pass.length() < 6 || sc_pass.length() > 16 || (!invalidPass(sc_pass))){
            registerUserEdtPass.setError("Please Enter with a valid pass");
            tc_valid = false;
        }
        if(sc_email.length() == 0 || (!invalidEmail(sc_email))){
            registerUserEdtEmail.setError("Please Enter with a valid email");
            tc_valid = false;
        }
        if(sc_phone.length() == 0 || (!invalidPhone(sc_phone))){
            registerUserEdtPhone.setError("xx 9 99999999 - xx 99999999");
            tc_valid = false;
        }
        return tc_valid;
    }
}