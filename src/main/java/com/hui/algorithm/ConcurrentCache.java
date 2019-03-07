package com.hui.algorithm;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: shenhaizhilong
 * @date: 2018/6/20 18:32
 * Tomcat 中的 ConcurrentCache 就使用了 WeakHashMap 来实现缓存功能。
 * ConcurrentCache 采取的是分代缓存，经常使用的对象放入 eden 中，而不常用的对象放入 longterm。
 * eden 使用 ConcurrentHashMap 实现，longterm 使用 WeakHashMap，保证了不常使用的对象容易被回收。
 *
 */
public final class ConcurrentCache<K,V> {
    private final int size;
    private final Map<K,V> eden;
    private final Map<K,V> longterm;

    public ConcurrentCache(int size)
    {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longterm = new WeakHashMap<>(size);
    }

    public V get(K k)
    {
        V v = this.eden.get(k);
        if(v == null)
        {
            v = this.longterm.get(k);
            if(v != null)
            {
                this.eden.put(k, v);
            }
        }

        return v;
    }

    public void put(K k, V v)
    {
        if(this.eden.size() >= size)
        {
            this.longterm.putAll(this.eden);
            this.eden.clear();
        }

        this.eden.put(k, v);
        System.out.println("eden: " + this.eden.values());
    }

    public int getEdenSize()
    {
        System.out.println(this.eden.values());
        return this.eden.size();
    }

    public int getLongtermSize()
    {
        System.out.println(this.longterm.values());
        return this.longterm.size();
    }

}
