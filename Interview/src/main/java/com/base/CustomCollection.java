/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/** 
 * 自定义集合 collection 类<p>
 * @author LingMin 
 * @date 2016-9-11<br>
 * @version 1.0<br>
 */
public class CustomCollection <T> {
    private ArrayList<T> bucket;
 
    public CustomCollection(){
        bucket = new ArrayList();
    }
 
    public int size() {
        return bucket.size();
    }
 
    public boolean isEmpty() {
        return bucket.isEmpty();
    }
 
    public boolean contains(T o) {
        return bucket.contains(o);
    }
 
    public boolean add(T e) {
        return bucket.add(e);
    }
 
    public boolean remove(T o) {
        return bucket.remove(o);
    }

 
}