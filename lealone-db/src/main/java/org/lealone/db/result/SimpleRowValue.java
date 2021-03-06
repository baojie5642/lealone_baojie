/*
 * Copyright 2004-2013 H2 Group. Multiple-Licensed under the H2 License,
 * Version 1.0, and under the Eclipse Public License, Version 1.0
 * (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.lealone.db.result;

import org.lealone.db.Constants;
import org.lealone.db.table.Column;
import org.lealone.db.value.Value;

/**
 * A simple row that contains data for only one column.
 */
public class SimpleRowValue implements SearchRow {

    private long key;
    private int version;
    private int index;
    private final int virtualColumnCount;
    private Value data;

    public SimpleRowValue(int columnCount) {
        this.virtualColumnCount = columnCount;
    }

    @Override
    public void setKeyAndVersion(SearchRow row) {
        key = row.getKey();
        version = row.getVersion();
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getColumnCount() {
        return virtualColumnCount;
    }

    @Override
    public long getKey() {
        return key;
    }

    @Override
    public void setKey(long key) {
        this.key = key;
    }

    @Override
    public Value getValue(int idx) {
        return idx == index ? data : null;
    }

    @Override
    public void setValue(int idx, Value v) {
        setValue(idx, v, null);
    }

    @Override
    public void setValue(int idx, Value v, Column c) {
        index = idx;
        data = v;
    }

    @Override
    public String toString() {
        return "( /* " + key + " */ " + (data == null ? "null" : data.getTraceSQL()) + " )";
    }

    @Override
    public int getMemory() {
        return Constants.MEMORY_OBJECT + (data == null ? 0 : data.getMemory());
    }
}
