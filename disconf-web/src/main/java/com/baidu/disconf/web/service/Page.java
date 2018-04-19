package com.baidu.disconf.web.service;

import com.github.pagehelper.PageRowBounds;

import java.util.List;
import java.util.Optional;

/**
 * @author QingDengYue
 */
public class Page<T> extends PageRowBounds {
    Optional<List<T>> result = Optional.empty();

    public Page(int offset, int limit) {
        super(offset, limit);
    }

    public Optional<List<T>> getResult() {
        return result;
    }

    public void setResult(Optional<List<T>> result) {
        this.result = result;
    }

}
