package io.candyboyou.mallpromotion.config;

import com.alibaba.druid.sql.dialect.oracle.parser.OracleExprParser;

public enum Env {
    local,
    qa,
    dev,
    test,
    prod;

    private Env() {
    }
}
