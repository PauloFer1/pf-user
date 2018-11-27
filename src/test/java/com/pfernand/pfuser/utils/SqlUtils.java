package com.pfernand.pfuser.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class SqlUtils {

    public static int[] runSqlScriptFile(Jdbi jdbi, String resource) throws IOException {
        String resourceContent = readResourceToString(resource);
        log.debug("Running sql script: {}", resourceContent);
        return jdbi.withHandle(h -> h.createScript(resourceContent).execute());
    }

    public static String readResourceToString(String resource) throws IOException {
        return IOUtils.toString(SqlUtils.class.getResourceAsStream(resource),
            Charset.forName("UTF-8"));
    }

    public static int[] runSqlScript(Jdbi jdbi, String sql) throws IOException {
        log.debug("Running sql script: {}", sql);
        return jdbi.withHandle(h -> h.createScript(sql).execute());
    }
}
