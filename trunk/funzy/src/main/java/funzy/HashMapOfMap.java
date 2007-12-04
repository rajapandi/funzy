// The MIT License - Copyright (c) 2007 Universitetet i Oslo

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE. 
package funzy;

import static com.google.common.base.Objects.nonNull;
import static com.google.common.collect.Maps.newHashMap;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;

/**
 * Hierarchical structure used to store a matrix of values.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class HashMapOfMap<K, L, V> extends HashMap<K, Map<L, V>> implements
        MapOfMap<K, L, V> {
    private final Supplier<Map<L, V>> supplier;

    private HashMapOfMap(Supplier<Map<L, V>> mapSupplier) {
        supplier = mapSupplier;
    }

    public Map<L, V> lookup(K key) {
        if (get(key) == null)
            put(key, supplier.get());
        return get(key);
    }

    public MapOfMap<K, L, V> put(K key, L literal, V value) {
        nonNull(lookup(key)).put(literal, value);
        return this;
    }

    public V get(K key, L literal) {
        return nonNull(lookup(key)).get(literal);
    }

    
    public static final <K, L, V> MapOfMap<K, L, V> newHashMapOfMap(
            Supplier<Map<L, V>> mapSupplier) {
        return new HashMapOfMap<K, L, V>(mapSupplier);
    }

    public static final <K, L, V> MapOfMap<K, L, V> newHashMapOfMap() {
        return newHashMapOfMap(new Supplier<Map<L, V>>() {
            public Map<L, V> get() {
                return newHashMap();
            }
        });
    }
}