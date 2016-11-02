package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mpoo.bsi.ufrpe.organictrade.Infra.Persistencia.DatabaseHelper;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.R;

public class UsuarioNegocio {

    private static SQLiteDatabase db;
    private static DatabaseHelper banco = Session.getDbAtual();

    private static final Pattern EMAIL_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_VERIFICATION_CHARACTERS = Pattern.compile("^[a-zA-Z\\sà-ùÀ-Ù]{0,}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern LOGIN_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Za-z0-9]{0,}$$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASS_VERIFICATION_CHARACTERS = Pattern.compile("^[A-Za-z0-9]{0,}$", Pattern.CASE_INSENSITIVE);

    private static boolean usuarioNaoCadastrado(String username) {
        String sql =
                "SELECT * FROM " + DatabaseHelper.getTableUserName() + " WHERE "
                        + DatabaseHelper.getColumnUserUsername() + "=?;";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }

    private static boolean emailNaoCadastrado(String email) {
        String sql =
                "SELECT * FROM " + DatabaseHelper.getTableUserName() + " WHERE "
                        + DatabaseHelper.getColumnUserEmail()+ "=?;";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }

    private static boolean campoVazio(String string) {
        return (string.equals(""));
    }

    private static boolean loginSmaller(String username) {
        if (username.length() <= 3) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean senhaSmaller(String pass) {
        if (pass.length() <= 3) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean nameSmaller(String name) {
        if (name.length() <= 3) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean emailValido(String emailStr) {
        Matcher matcher = EMAIL_VERIFICATION_CHARACTERS.matcher(emailStr);
        if (matcher.find()) {
            return true;
        } else {
            Toast.makeText(Session.getContext(), R.string.emailInvalido, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private static boolean nameValido(String nameStr) {
        Matcher matcher = NAME_VERIFICATION_CHARACTERS.matcher(nameStr);
        if (matcher.find() && !nameSmaller(nameStr)) {
            return true;
        } else {
            Toast.makeText(Session.getContext(), R.string.nomeInvalido, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private static boolean passValido(String passStr) {
        Matcher matcher = PASS_VERIFICATION_CHARACTERS.matcher(passStr);
        if (matcher.find() && !senhaSmaller(passStr)) {
            return true;
        } else {
            Toast.makeText(Session.getContext(), R.string.senhaInvalida, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private static boolean loginValido(String loginStr) {
        Matcher matcher = LOGIN_VERIFICATION_CHARACTERS.matcher(loginStr);
        if (matcher.find() && !loginSmaller(loginStr)) {
            return true;
        } else {
            Toast.makeText(Session.getContext(), R.string.loginInvalido, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public static boolean verificacaoLogin(String login, String senha) {
        if (!(campoVazio(login)) && !(campoVazio(senha))) {
            if (usuarioNaoCadastrado(login)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean verificacaoCadastro(String login, String pass, String rpass, String email, String nome) {
        if (!campoVazio(login) && !campoVazio(pass) && !campoVazio(rpass) && !campoVazio(email) && !campoVazio(nome)) {
            if (loginValido(login) && passValido(pass) && emailValido(email) && nameValido(nome)) {
                if(usuarioNaoCadastrado(login)&& emailNaoCadastrado(email)){
                    return true;
                }else{
                    Toast.makeText(Session.getContext(), R.string.loginemailInvalido, Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}