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
package funzy.variables;

import static com.google.common.base.Objects.nonNull;
import static com.google.common.collect.Maps.newHashMap;
import static funzy.HashMapOfMap.newHashMapOfMap;

import java.util.Map;
import java.util.Map.Entry;

import funzy.MapOfMap;

/**
 * Implementation of the variable set.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class VariableSet<T extends Object> {
    private Map<String, Variable> variables;

    private VariableSet() {
        variables = newHashMap();
    }

    public VariableSet add(String name, Variable var) {
        variables.put(name, var);
        return this;
    }

    public VariableSet add(Variable var) {
        return add(var.name(), var);
    }

    public Variable get(String name) {
        return nonNull(variables.get(name), "Variable " + name + " is unknown");
    }

    public MapOfMap<String, T, Double> fuzzy(Map<String, Double> input) {
        MapOfMap<String, T, Double> output = newHashMapOfMap();
        for (Entry<String, Double> val : input.entrySet()) {
            output.put(val.getKey(), get(val.getKey()).fuzzy(val.getValue()));
        }
        return output;
    }

    public Map<String, Double> unfuzzy(MapOfMap<String, T, Double> input) {
        Map<String, Double> output = newHashMap();
        for (Entry<String, Map<T, Double>> val : input.entrySet())
            output.put(val.getKey(), get(val.getKey()).unfuzzy(val.getValue()));
        return output;
    }

    public static final VariableSet<String> newVariableSet() {
        return new VariableSet<String>();
    }
}
