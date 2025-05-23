/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.bidimap;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections4.OrderedBidiMap;

/**
 * Test class for AbstractOrderedBidiMapDecorator.
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 */
public abstract class AbstractOrderedBidiMapDecoratorTest<K, V>
        extends AbstractOrderedBidiMapTest<K, V> {

    /**
     * Simple class to actually test.
     */
    private static final class TestOrderedBidiMap<K, V> extends AbstractOrderedBidiMapDecorator<K, V> {

        private TestOrderedBidiMap<V, K> inverse;

        TestOrderedBidiMap() {
            super(new DualTreeBidiMap<>());
        }

        TestOrderedBidiMap(final OrderedBidiMap<K, V> map) {
            super(map);
        }

        @Override
        public OrderedBidiMap<V, K> inverseBidiMap() {
            if (inverse == null) {
                inverse = new TestOrderedBidiMap<>(decorated().inverseBidiMap());
                inverse.inverse = this;
            }
            return inverse;
        }
    }

    @Override
    public boolean isAllowNullKey() {
        return false;
    }

    @Override
    public boolean isAllowNullValueGet() {
        return false;
    }

    @Override
    public boolean isAllowNullValuePut() {
        return false;
    }

    @Override
    public boolean isSetValueSupported() {
        return true;
    }

    @Override
    public SortedMap<K, V> makeConfirmedMap() {
        return new TreeMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderedBidiMap<K, V> makeObject() {
        return new TestOrderedBidiMap<>();
    }
}
