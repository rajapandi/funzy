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

import java.util.Map;

import funzy.Pull;

/**
 * Implementation of a literal input variable in fuzzy logic.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class InputVariable<L> implements Pull<Map<L, Double>> {

    private final Variable<L> variable;
    private final Pull<Double> input;

    private InputVariable(Variable<L> fuzzyfier, Pull<Double> provider) {
        variable = nonNull(fuzzyfier, "Variable reference is required");
        input = nonNull(provider, "Provider reference is required");
    }

    public Map<L, Double> pull() {
        return variable.fuzzy(input.pull());
    }

    public String name() {
        return variable.name();
    }
    
    // Factory methods

    public static final <L extends Enum<L>> InputVariable newInputVariable(
            Variable<L> fuzzyfier, Pull<Double> provider) {
        return new InputVariable<L>(fuzzyfier, provider);
    }
}