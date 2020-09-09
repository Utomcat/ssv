package com.ranyk.ssv.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * ClassName:IOUtils
 * Description:IO相关工具类
 *
 * @author ranyi
 * @date 2019-12-19 22:34
 * Version: V1.0
 */
public class IOUtils {

    /**
     * 关闭对象，连接
     * @param closeable
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }

}
