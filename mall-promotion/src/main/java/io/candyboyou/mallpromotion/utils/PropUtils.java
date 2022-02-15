package io.candyboyou.mallpromotion.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtils {

    public static Properties load(Class<?> clazz, String fileName) {

        Properties prop = new Properties();

        try {
            // 这个地方有问题
            InputStream input = clazz.getClassLoader().getResourceAsStream(fileName);
            IOException ioException = null;

            try {
                prop.load(input);
            } catch (IOException ioException1) {
                ioException = ioException1;
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException ioException2) {
                        if (ioException != null) {
                            ioException.addSuppressed(ioException2);
                        } else {
                            ioException = ioException2;
                        }
                    }
                }

                if (ioException != null) {
                    throw ioException;
                }

            }

            return prop;
        } catch (IOException e) {
            throw new RuntimeException("cannot find file : " + fileName, e);
        }
    }
}
