/*
 *  Copyright (c) 2025 Original Author(s), PhonePe India Pvt. Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.phonepe.commons.query.dsl;

import com.phonepe.commons.query.dsl.general.AnyFilter;
import com.phonepe.commons.query.dsl.general.ContainsFilter;
import com.phonepe.commons.query.dsl.general.EqualsFilter;
import com.phonepe.commons.query.dsl.general.ExistsFilter;
import com.phonepe.commons.query.dsl.general.GenericFilter;
import com.phonepe.commons.query.dsl.general.InFilter;
import com.phonepe.commons.query.dsl.general.MissingFilter;
import com.phonepe.commons.query.dsl.general.NotEqualsFilter;
import com.phonepe.commons.query.dsl.general.NotInFilter;
import com.phonepe.commons.query.dsl.logical.AndFilter;
import com.phonepe.commons.query.dsl.logical.NotFilter;
import com.phonepe.commons.query.dsl.logical.OrFilter;
import com.phonepe.commons.query.dsl.numeric.BetweenFilter;
import com.phonepe.commons.query.dsl.numeric.GreaterEqualFilter;
import com.phonepe.commons.query.dsl.numeric.GreaterThanFilter;
import com.phonepe.commons.query.dsl.numeric.LessEqualFilter;
import com.phonepe.commons.query.dsl.numeric.LessThanFilter;
import com.phonepe.commons.query.dsl.string.StringEndsWithFilter;
import com.phonepe.commons.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.commons.query.dsl.string.StringStartsWithFilter;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class FilterCounterTest {

    @Test
    public void testFilterCounter() {
        final var build = AndFilter.builder().filter(EqualsFilter.builder().build())
                .filter(InFilter.builder().build())
                .filter(new AnyFilter())
                .filter(OrFilter.builder().filter(new AnyFilter())
                                .filter(EqualsFilter.builder().build())
                                .build())
                .build();
        assertEquals(5, build.accept(new FilterCounter()).intValue());
    }
    @Test
    void testFilterCounter_AllFilterTypes() {
        // Test each individual filter type returns 1
        assertEquals(1, new AnyFilter().accept(new FilterCounter()));
        assertEquals(1, EqualsFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, NotEqualsFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, InFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, NotInFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, ExistsFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, MissingFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, new ContainsFilter().accept(new FilterCounter()));
        assertEquals(1, BetweenFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, GreaterEqualFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, GreaterThanFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, LessEqualFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, LessThanFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, StringEndsWithFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, StringStartsWithFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, StringRegexMatchFilter.builder().build().accept(new FilterCounter()));
        assertEquals(1, GenericFilter.builder().build().accept(new FilterCounter()));
    }

    @Test
    void testAndFilter() {
        // Empty AND should count as 0
        assertEquals(0, AndFilter.builder().filters(Collections.emptyList()).build().accept(new FilterCounter()));

        // AND with 3 filters should return 3
        AndFilter andFilter = AndFilter.builder()
                .filter(new AnyFilter())
                .filter(EqualsFilter.builder().build())
                .filter(InFilter.builder().build())
                .build();
        assertEquals(3, andFilter.accept(new FilterCounter()));
    }

    @Test
    void testOrFilter() {
        // Empty OR should count as 0
        assertEquals(0, OrFilter.builder().filters(Collections.emptyList()).build().accept(new FilterCounter()));

        // OR with 3 filters should return 3
        OrFilter orFilter = OrFilter.builder()
                .filter(new AnyFilter())
                .filter(EqualsFilter.builder().build())
                .filter(InFilter.builder().build())
                .build();
        assertEquals(3, orFilter.accept(new FilterCounter()));
    }

    @Test
    void testNotFilter() {
        // NOT filter should return count of its child filter
        assertEquals(1, NotFilter.builder().filter(new AnyFilter()).build().accept(new FilterCounter()));

        // NOT with AND containing 2 filters should return 2
        NotFilter notFilter = NotFilter.builder()
                .filter(AndFilter.builder()
                                .filter(new AnyFilter())
                                .filter(EqualsFilter.builder().build())
                                .build())
                .build();
        assertEquals(2, notFilter.accept(new FilterCounter()));
    }

    @Test
    void testNestedFilters() {
        // Complex nested filters
        AndFilter complexFilter = AndFilter.builder()
                .filter(EqualsFilter.builder().build())
                .filter(OrFilter.builder()
                                .filter(new AnyFilter())
                                .filter(NotFilter.builder()
                                                .filter(OrFilter.builder()
                                                                .filter(InFilter.builder().build())
                                                                .filter(BetweenFilter.builder().build())
                                                                .build())
                                                .build())
                                .build())
                .build();

        // Count should be: 1 (EqualsFilter) + 1 (AnyFilter) + 2 (InFilter + BetweenFilter) = 4
        assertEquals(4, complexFilter.accept(new FilterCounter()));
    }

    @Test
    void testOriginalTestCase() {
        // The existing test case should still pass
        final var build = AndFilter.builder()
                .filter(EqualsFilter.builder().build())
                .filter(InFilter.builder().build())
                .filter(new AnyFilter())
                .filter(OrFilter.builder()
                                .filter(new AnyFilter())
                                .filter(EqualsFilter.builder().build())
                                .build())
                .build();
        assertEquals(5, build.accept(new FilterCounter()).intValue());
    }

    @Test
    void testAbstractFilterVisitor() {
        // Test that AbstractFilterVisitor returns the provided default value
        Object defaultValue = new Object();
        AbstractFilterVisitor<Object> visitor = new AbstractFilterVisitor<>(defaultValue);

        assertSame(defaultValue, visitor.visit(new AnyFilter()));
        assertSame(defaultValue, visitor.visit(EqualsFilter.builder().build()));
        assertSame(defaultValue, visitor.visit(AndFilter.builder().build()));
        assertSame(defaultValue, visitor.visit(OrFilter.builder().build()));
        assertSame(defaultValue, visitor.visit(NotFilter.builder().filter(new AnyFilter()).build()));
        assertSame(defaultValue, visitor.visit(new ContainsFilter()));
        // Add other filter types as needed
    }
}