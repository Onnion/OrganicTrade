package mpoo.bsi.ufrpe.organictrade.util;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class Util {
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


}
