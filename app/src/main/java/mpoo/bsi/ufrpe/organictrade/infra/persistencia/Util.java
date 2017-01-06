package mpoo.bsi.ufrpe.organictrade.infra.persistencia;

import java.util.Calendar;

public class Util {
    public static String getDate() {
        String data = "";
        Calendar calendar = Calendar.getInstance();
        data += calendar.get(Calendar.HOUR_OF_DAY) + "-";
        data += calendar.get(Calendar.MINUTE) + "-";
        data += calendar.get(Calendar.SECOND) + "-";
        data += calendar.get(Calendar.DAY_OF_MONTH) + "-";
        data += calendar.get(Calendar.MONTH) + "-";
        data += calendar.get(Calendar.YEAR) + "-";
        return data;
    }
}
