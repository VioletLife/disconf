package com.baidu.disconf.client.addons.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Overrides all methods of java.util.Properties using delegation.
 * Would implement instead of extend java.util.Properties if it was an interface.
 */
public abstract class DelegatingProperties extends Properties {
    protected abstract Properties getDelegate();

    @Override
    public void load(InputStream inStream) throws IOException {
        getDelegate().load(inStream);
    }

    @Override
    public void list(PrintStream out) {
        getDelegate().list(out);
    }

    @Override
    public void list(PrintWriter out) {
        getDelegate().list(out);
    }

    @Override
    public Enumeration propertyNames() {
        return getDelegate().propertyNames();
    }

    @Override
    public void store(OutputStream out, String header) throws IOException {
        getDelegate().store(out, header);
    }

    @Override
    public String getProperty(String key) {
        return getDelegate().getProperty(key);
    }

    @Override
    public Object setProperty(String key, String value) {
        return getDelegate().setProperty(key, value);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return getDelegate().getProperty(key, defaultValue);
    }

    @Override
    public int hashCode() {
        return getDelegate().hashCode();
    }

    @Override
    public int size() {
        return getDelegate().size();
    }

    @Override
    public void clear() {
        getDelegate().clear();
    }

    @Override
    public boolean isEmpty() {
        return getDelegate().isEmpty();
    }

    @Override
    public Object clone() {
        return getDelegate().clone();
    }

    @Override
    public boolean contains(Object value) {
        return getDelegate().contains(value);
    }

    @Override
    public boolean containsKey(Object key) {
        return getDelegate().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getDelegate().containsValue(value);
    }

    @Override
    public boolean equals(Object o) {
        return getDelegate().equals(o);
    }

    @Override
    public String toString() {
        return getDelegate().toString();
    }

    @Override
    public Collection values() {
        return getDelegate().values();
    }

    @Override
    public Enumeration elements() {
        return getDelegate().elements();
    }

    @Override
    public Enumeration keys() {
        return getDelegate().keys();
    }

    @Override
    public void putAll(Map t) {
        getDelegate().putAll(t);
    }

    @Override
    public Set entrySet() {
        return getDelegate().entrySet();
    }

    @Override
    public Set keySet() {
        return getDelegate().keySet();
    }

    @Override
    public Object get(Object key) {
        return getDelegate().get(key);
    }

    @Override
    public Object remove(Object key) {
        return getDelegate().remove(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return getDelegate().put(key, value);
    }
}
