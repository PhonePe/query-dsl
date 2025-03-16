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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterModelTest {

    private final FilterVisitor<Boolean> mockVisitor = new AbstractFilterVisitor<Boolean>(false) {
        @Override
        public Boolean visit(EqualsFilter equalsFilter) {
            return true;
        }

        @Override
        public Boolean visit(NotEqualsFilter notEqualsFilter) {
            return true;
        }

        @Override
        public Boolean visit(InFilter inFilter) {
            return true;
        }

        @Override
        public Boolean visit(NotInFilter notInFilter) {
            return true;
        }

        @Override
        public Boolean visit(ExistsFilter existsFilter) {
            return true;
        }

        @Override
        public Boolean visit(MissingFilter missingFilter) {
            return true;
        }

        @Override
        public Boolean visit(ContainsFilter containsFilter) {
            return true;
        }

        @Override
        public Boolean visit(AnyFilter anyFilter) {
            return true;
        }

        @Override
        public Boolean visit(GenericFilter genericFilter) {
            return true;
        }

        @Override
        public Boolean visit(AndFilter andFilter) {
            return true;
        }

        @Override
        public Boolean visit(OrFilter orFilter) {
            return true;
        }

        @Override
        public Boolean visit(NotFilter notFilter) {
            return true;
        }

        @Override
        public Boolean visit(GreaterThanFilter greaterThanFilter) {
            return true;
        }

        @Override
        public Boolean visit(GreaterEqualFilter greaterEqualFilter) {
            return true;
        }

        @Override
        public Boolean visit(LessThanFilter lessThanFilter) {
            return true;
        }

        @Override
        public Boolean visit(LessEqualFilter lessEqualFilter) {
            return true;
        }

        @Override
        public Boolean visit(BetweenFilter betweenFilter) {
            return true;
        }

        @Override
        public Boolean visit(StringStartsWithFilter stringStartsWithFilter) {
            return true;
        }

        @Override
        public Boolean visit(StringEndsWithFilter stringEndsWithFilter) {
            return true;
        }

        @Override
        public Boolean visit(StringRegexMatchFilter stringRegexMatchFilter) {
            return true;
        }
    };

    @Nested
    @DisplayName("General Filters Tests")
    class GeneralFiltersTest {

        @Test
        @DisplayName("Test EqualsFilter")
        void testEqualsFilter() {
            EqualsFilter filter = EqualsFilter.builder()
                    .field("name")
                    .value("John Doe")
                    .build();

            assertEquals("name", filter.getField());
            assertEquals("John Doe", filter.getValue());
            assertEquals(FilterOperator.EQUALS, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            EqualsFilter emptyFilter = new EqualsFilter();
            assertEquals(FilterOperator.EQUALS, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test NotEqualsFilter")
        void testNotEqualsFilter() {
            NotEqualsFilter filter = NotEqualsFilter.builder()
                    .field("status")
                    .value("inactive")
                    .build();

            assertEquals("status", filter.getField());
            assertEquals("inactive", filter.getValue());
            assertEquals(FilterOperator.NOT_EQUALS, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            NotEqualsFilter emptyFilter = new NotEqualsFilter();
            assertEquals(FilterOperator.NOT_EQUALS, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test InFilter")
        void testInFilter() {
            InFilter filter = InFilter.builder()
                    .field("category")
                    .values(Arrays.asList("electronics", "gadgets", "accessories"))
                    .build();

            assertEquals("category", filter.getField());
            assertEquals(3, filter.getValues().size());
            assertTrue(filter.getValues().contains("electronics"));
            assertEquals(FilterOperator.IN, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            InFilter emptyFilter = new InFilter();
            assertEquals(FilterOperator.IN, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test NotInFilter")
        void testNotInFilter() {
            NotInFilter filter = NotInFilter.builder()
                    .field("status")
                    .values(Arrays.asList("deleted", "archived", "inactive"))
                    .build();

            assertEquals("status", filter.getField());
            assertEquals(3, filter.getValues().size());
            assertTrue(filter.getValues().contains("deleted"));
            assertEquals(FilterOperator.NOT_IN, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            NotInFilter emptyFilter = new NotInFilter();
            assertEquals(FilterOperator.NOT_IN, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test ExistsFilter")
        void testExistsFilter() {
            ExistsFilter filter = ExistsFilter.builder()
                    .field("email")
                    .build();

            assertEquals("email", filter.getField());
            assertEquals(FilterOperator.EXISTS, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            ExistsFilter emptyFilter = new ExistsFilter();
            assertEquals(FilterOperator.EXISTS, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test MissingFilter")
        void testMissingFilter() {
            MissingFilter filter = MissingFilter.builder()
                    .field("phoneNumber")
                    .build();

            assertEquals("phoneNumber", filter.getField());
            assertEquals(FilterOperator.MISSING, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            MissingFilter emptyFilter = new MissingFilter();
            assertEquals(FilterOperator.MISSING, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test ContainsFilter")
        void testContainsFilter() {
            ContainsFilter filter = ContainsFilter.builder()
                    .field("tags")
                    .value("premium")
                    .iterable(true)
                    .build();

            assertEquals("tags", filter.getField());
            assertEquals("premium", filter.getValue());
            assertEquals(FilterOperator.CONTAINS, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            ContainsFilter emptyFilter = new ContainsFilter();
            assertEquals(FilterOperator.CONTAINS, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test AnyFilter")
        void testAnyFilter() {
            AnyFilter filter = new AnyFilter();
            filter.setField("status");

            assertEquals("status", filter.getField());
            assertEquals(FilterOperator.ANY, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());
        }

        @Test
        @DisplayName("Test GenericFilter")
        void testGenericFilter() {
            Map<String, Object> value = new HashMap<>();
            value.put("customKey", "customValue");
            Map<String, String> nestedObject = new HashMap<>();
            nestedObject.put("nestedKey", "nestedValue");
            value.put("nestedObject", nestedObject);

            GenericFilter filter = GenericFilter.builder()
                    .field("customField")
                    .value(value)
                    .build();

            assertEquals("customField", filter.getField());
            assertNotNull(filter.getValue());
            assertEquals(FilterOperator.GENERIC, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            GenericFilter emptyFilter = new GenericFilter();
            assertEquals(FilterOperator.GENERIC, emptyFilter.getOperator());
        }
    }

    @Nested
    @DisplayName("Logical Filters Tests")
    class LogicalFiltersTest {

        @Test
        @DisplayName("Test AndFilter")
        void testAndFilter() {
            AndFilter filter = AndFilter.builder()
                    .filter(EqualsFilter.builder().field("status").value("active").build())
                    .filter(GreaterThanFilter.builder().field("age").value(18).build())
                    .build();

            assertNotNull(filter.getFilters());
            assertEquals(2, filter.getFilters().size());
            assertEquals(FilterOperator.AND, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // We can't test the empty constructor directly as it's protected
        }

        @Test
        @DisplayName("Test OrFilter")
        void testOrFilter() {
            OrFilter filter = OrFilter.builder()
                    .filter(EqualsFilter.builder().field("category").value("premium").build())
                    .filter(GreaterEqualFilter.builder().field("purchaseAmount").value(1000).build())
                    .build();

            assertNotNull(filter.getFilters());
            assertEquals(2, filter.getFilters().size());
            assertEquals(FilterOperator.OR, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // We can't test the empty constructor directly as it's protected
        }

        @Test
        @DisplayName("Test NotFilter")
        void testNotFilter() {
            NotFilter filter = NotFilter.builder()
                    .filter(EqualsFilter.builder().field("status").value("inactive").build())
                    .build();

            assertNotNull(filter.getFilter());
            assertTrue(filter.getFilter() instanceof EqualsFilter);
            assertEquals(FilterOperator.NOT, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            NotFilter emptyFilter = new NotFilter();
            assertEquals(FilterOperator.NOT, emptyFilter.getOperator());
        }
    }

    @Nested
    @DisplayName("Numeric Filters Tests")
    class NumericFiltersTest {

        @Test
        @DisplayName("Test GreaterThanFilter")
        void testGreaterThanFilter() {
            GreaterThanFilter filter = GreaterThanFilter.builder()
                    .field("age")
                    .value(18)
                    .build();

            assertEquals("age", filter.getField());
            assertEquals(18, filter.getValue());
            assertEquals(FilterOperator.GREATER_THAN, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            GreaterThanFilter emptyFilter = new GreaterThanFilter();
            assertEquals(FilterOperator.GREATER_THAN, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test GreaterEqualFilter")
        void testGreaterEqualFilter() {
            GreaterEqualFilter filter = GreaterEqualFilter.builder()
                    .field("score")
                    .value(75)
                    .build();

            assertEquals("score", filter.getField());
            assertEquals(75, filter.getValue());
            assertEquals(FilterOperator.GREATER_EQUAL, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            GreaterEqualFilter emptyFilter = new GreaterEqualFilter();
            assertEquals(FilterOperator.GREATER_EQUAL, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test LessThanFilter")
        void testLessThanFilter() {
            LessThanFilter filter = LessThanFilter.builder()
                    .field("price")
                    .value(100)
                    .build();

            assertEquals("price", filter.getField());
            assertEquals(100, filter.getValue());
            assertEquals(FilterOperator.LESS_THAN, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            LessThanFilter emptyFilter = new LessThanFilter();
            assertEquals(FilterOperator.LESS_THAN, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test LessEqualFilter")
        void testLessEqualFilter() {
            LessEqualFilter filter = LessEqualFilter.builder()
                    .field("quantity")
                    .value(10)
                    .build();

            assertEquals("quantity", filter.getField());
            assertEquals(10, filter.getValue());
            assertEquals(FilterOperator.LESS_EQUAL, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            LessEqualFilter emptyFilter = new LessEqualFilter();
            assertEquals(FilterOperator.LESS_EQUAL, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test BetweenFilter")
        void testBetweenFilter() {
            BetweenFilter filter = BetweenFilter.builder()
                    .field("age")
                    .from(18)
                    .to(65)
                    .build();

            assertEquals("age", filter.getField());
            assertEquals(18, filter.getFrom());
            assertEquals(65, filter.getTo());
            assertEquals(FilterOperator.BETWEEN, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            BetweenFilter emptyFilter = new BetweenFilter();
            assertEquals(FilterOperator.BETWEEN, emptyFilter.getOperator());
        }
    }

    @Nested
    @DisplayName("String Filters Tests")
    class StringFiltersTest {

        @Test
        @DisplayName("Test StringStartsWithFilter")
        void testStringStartsWithFilter() {
            StringStartsWithFilter filter = StringStartsWithFilter.builder()
                    .field("name")
                    .value("John")
                    .offset(0)
                    .build();

            assertEquals("name", filter.getField());
            assertEquals("John", filter.getValue());
            assertEquals(0, filter.getOffset());
            assertEquals(FilterOperator.STR_STARTS_WITH, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            StringStartsWithFilter emptyFilter = new StringStartsWithFilter();
            assertEquals(FilterOperator.STR_STARTS_WITH, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test StringEndsWithFilter")
        void testStringEndsWithFilter() {
            StringEndsWithFilter filter = StringEndsWithFilter.builder()
                    .field("email")
                    .value("@example.com")
                    .build();

            assertEquals("email", filter.getField());
            assertEquals("@example.com", filter.getValue());
            assertEquals(FilterOperator.STR_ENDS_WITH, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            StringEndsWithFilter emptyFilter = new StringEndsWithFilter();
            assertEquals(FilterOperator.STR_ENDS_WITH, emptyFilter.getOperator());
        }

        @Test
        @DisplayName("Test StringRegexMatchFilter")
        void testStringRegexMatchFilter() {
            StringRegexMatchFilter filter = StringRegexMatchFilter.builder()
                    .field("phoneNumber")
                    .value("\\d{3}-\\d{3}-\\d{4}")
                    .build();

            assertEquals("phoneNumber", filter.getField());
            assertEquals("\\d{3}-\\d{3}-\\d{4}", filter.getValue());
            assertEquals(FilterOperator.STR_REGEX_MATCH, filter.getOperator());
            assertTrue(filter.accept(mockVisitor));
            assertTrue(filter.validate());

            // Test empty constructor
            StringRegexMatchFilter emptyFilter = new StringRegexMatchFilter();
            assertEquals(FilterOperator.STR_REGEX_MATCH, emptyFilter.getOperator());
        }
    }

    @Test
    @DisplayName("Test Filter validation")
    void testFilterValidation() {
        // Valid filter
        Filter validFilter = EqualsFilter.builder()
                .field("name")
                .value("John")
                .build();
        assertTrue(validFilter.validate());

        // Invalid filter (empty field)
        Filter invalidFilter = EqualsFilter.builder()
                .field("")
                .value("John")
                .build();
        assertThrows(RuntimeException.class, invalidFilter::validate);

        // Invalid filter (null field)
        Filter nullFieldFilter = EqualsFilter.builder()
                .value("John")
                .build();
        assertThrows(RuntimeException.class, nullFieldFilter::validate);
    }
}
