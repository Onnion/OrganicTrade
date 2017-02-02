package mpoo.bsi.ufrpe.organictrade.infra.gui;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class UtilInfraGui {
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}
