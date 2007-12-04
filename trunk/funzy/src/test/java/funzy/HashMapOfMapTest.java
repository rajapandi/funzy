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

import static funzy.HashMapOfMap.newHashMapOfMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Test of the map of map.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class HashMapOfMapTest {
    private MapOfMap<String, String, Integer> map;

    @Before
    public void setup() {
        map = newHashMapOfMap();
    }

    @Test
    public void putValue() {
        assertEquals(map, map.put("row", "column", 1));
    }

    @Test
    public void getExistingValue() {
        map.put("row", "column", 1);
        assertEquals(1, map.get("row", "column"));
    }

    @Test
    public void getInvalidColumn() {
        assertNull("Invalid column should return null", map
                .get("row", "column"));
    }

    @Test
    public void getInvalidRow() {
        assertNull("Invalid row should return null", map.get("row"));
    }

    @Test
    public void lookupEmptyValue() {
        assertNotNull("Valid row lookup should not return null", map
                .lookup("row"));
    }
}
