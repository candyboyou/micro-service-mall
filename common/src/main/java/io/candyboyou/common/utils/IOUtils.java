package io.candyboyou.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    public static byte[] toByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read = 0;

        while(read != -1) {
            read = stream.read(buffer);
            if (read > 0) {
                outputStream.write(buffer, 0, read);
            }
        }

        return outputStream.toByteArray();
    }
}
