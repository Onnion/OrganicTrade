package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Negocio;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mpoo.bsi.ufrpe.organictrade.Infra.Session;

public class ItensDeTendaNegocio {
    private static final Pattern DECIMAL_VERIFICATION_CHARACTERS = Pattern.compile("^\\d+(\\.\\d{1,2})?$", Pattern.CASE_INSENSITIVE);

    //preco com apenas duas casas deciamais
    private boolean isNegativo(String valor) {
        double sinal = Double.parseDouble(valor);
        if (sinal < 0) {
            Toast.makeText(Session.getContext(), "Valor invalido", Toast.LENGTH_LONG).show();
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
            Toast.makeText(Session.getContext(), "@string/naoDecimal", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean verificandoQuantidade(String quantidade) {
        return (isNegativo(quantidade));
    }

}