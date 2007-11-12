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
package funzy.operators;

import static com.google.common.base.Objects.nonNull;

import java.util.Map;

import com.google.common.base.Supplier;

/**
 * Implementation of a fuzzy literal extractor.
 * 
 * @author <a href="romain.rouvoy+funzy@gmail.com">Romain Rouvoy</a>
 * @version $Revision$
 */
public class FuzzyExtractor <K, V, N extends Number> implements Supplier<N>{
	private Map<K, Map<V,N>> values;
	private K key;
	private V value;
	
	private FuzzyExtractor(K variable, V literal, Map<K, Map<V,N>> provider) {
		key = variable;
		value = literal;
		values = provider;
	}
	
	public N get() {
		return nonNull(nonNull(values.get(key)).get(value));
	}
	
	public static final <K, V, N extends Number> Supplier<N> newExtractor(K variable, V literal, Map<K, Map<V,N>> provider) {
		return new FuzzyExtractor<K,V,N>(variable, literal, provider);
	}
}
