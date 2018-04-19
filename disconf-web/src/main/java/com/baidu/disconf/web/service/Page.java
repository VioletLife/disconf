package com.baidu.disconf.web.service;

import com.github.pagehelper.PageRowBounds;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author QingDengYue
 */
public class Page<T> extends PageRowBounds {
    Optional<List<T>> result = Optional.empty();
    List<T> records = Collections.emptyList();

    public Page(int offset, int limit) {
        super(offset, limit);
    }


    public void setResult(Optional<List<T>> result) {
        if (result != null) {
            result.ifPresent((allRecords) -> this.records = allRecords);
        }
        this.result = result;
    }

    public List<T> getRecords() {
        return records;
    }
}
