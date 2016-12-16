package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.negocio;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;
import mpoo.bsi.ufrpe.organictrade.R;
import mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.item.persistencia.TentItemsPersistence;

public class TentsItemsNegocio {
    private static final Pattern DECIMAL_VERIFICATION_CHARACTERS = Pattern.compile("^\\d+(\\.\\d{1,2})?$", Pattern.CASE_INSENSITIVE);
    private static TentItemsPersistence tentItemsPersistence = new TentItemsPersistence();

    //preco com apenas duas casas deciamais
    private boolean isNegative(String value) {
        double sign = Double.parseDouble(value);
        if (sign < 0) {
            Toast.makeText(Session.getContext(), R.string.tstInvalidValue, Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }
    }

    private static boolean isDecimal(String valor) {
        Matcher matcher = DECIMAL_VERIFICATION_CHARACTERS.matcher(valor);
        if (matcher.find()) {
            return true;
        } else {
            Toast.makeText(Session.getContext(), R.string.tstNotDecimal, Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean amountVerify(String amount) {
        return
                (isNegative(amount));
    }

    public static TentItemsPersistence tentItemsPersistence(){
        return tentItemsPersistence;
    }

}