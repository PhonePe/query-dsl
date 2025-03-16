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

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FilterDeserializationTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String FILTERS_DIR = "src/test/resources/filters";

    static Stream<Path> provideJsonFilePaths() throws IOException {
        return Files.list(Paths.get(FILTERS_DIR))
                .filter(path -> path.toString().endsWith(".json"));
    }

    @ParameterizedTest(name = "Test deserialization of {0}")
    @MethodSource("provideJsonFilePaths")
    @DisplayName("Test that each filter JSON file can be deserialized to the correct Filter object")
    public void testFilterJsonDeserialization(Path jsonFile) throws IOException {
        String fileName = jsonFile.getFileName().toString();
        System.out.println("Testing deserialization of " + fileName);

        // Read the JSON file
        var json = Files.readAllBytes(jsonFile);

        // Deserialize to Filter object
        Filter filter = OBJECT_MAPPER.readValue(json, Filter.class);

        // Verify the filter is not null and has the correct operator
        assertNotNull(filter, "Deserialized filter should not be null for " + fileName);

        // Verify the filter type based on the filename
        verifyFilterType(fileName, filter);

        // Verify filter-specific properties
        verifyFilterProperties(filter);
    }

    private void verifyFilterType(String fileName, Filter filter) {
        switch (fileName) {
            case "equals_filter.json":
                assertInstanceOf(EqualsFilter.class, filter, "Should be an EqualsFilter");
                break;
            case "not_equals_filter.json":
                assertInstanceOf(NotEqualsFilter.class, filter, "Should be a NotEqualsFilter");
                break;
            case "in_filter.json":
                assertInstanceOf(InFilter.class, filter, "Should be an InFilter");
                break;
            case "not_in_filter.json":
                assertInstanceOf(NotInFilter.class, filter, "Should be a NotInFilter");
                break;
            case "exists_filter.json":
                assertInstanceOf(ExistsFilter.class, filter, "Should be an ExistsFilter");
                break;
            case "missing_filter.json":
                assertInstanceOf(MissingFilter.class, filter, "Should be a MissingFilter");
                break;
            case "contains_filter.json":
                assertInstanceOf(ContainsFilter.class, filter, "Should be a ContainsFilter");
                break;
            case "any_filter.json":
                assertInstanceOf(AnyFilter.class, filter, "Should be an AnyFilter");
                break;
            case "and_filter.json":
                assertInstanceOf(AndFilter.class, filter, "Should be an AndFilter");
                break;
            case "or_filter.json":
                assertInstanceOf(OrFilter.class, filter, "Should be an OrFilter");
                break;
            case "not_filter.json":
                assertInstanceOf(NotFilter.class, filter, "Should be a NotFilter");
                break;
            case "greater_than_filter.json":
                assertInstanceOf(GreaterThanFilter.class, filter, "Should be a GreaterThanFilter");
                break;
            case "greater_equal_filter.json":
                assertInstanceOf(GreaterEqualFilter.class, filter, "Should be a GreaterEqualFilter");
                break;
            case "less_than_filter.json":
                assertInstanceOf(LessThanFilter.class, filter, "Should be a LessThanFilter");
                break;
            case "less_equal_filter.json":
                assertInstanceOf(LessEqualFilter.class, filter, "Should be a LessEqualFilter");
                break;
            case "between_filter.json":
                assertInstanceOf(BetweenFilter.class, filter, "Should be a BetweenFilter");
                break;
            case "string_starts_with_filter.json":
                assertInstanceOf(StringStartsWithFilter.class, filter, "Should be a StringStartsWithFilter");
                break;
            case "string_ends_with_filter.json":
                assertInstanceOf(StringEndsWithFilter.class, filter, "Should be a StringEndsWithFilter");
                break;
            case "string_regex_match_filter.json":
                assertInstanceOf(StringRegexMatchFilter.class, filter, "Should be a StringRegexMatchFilter");
                break;
            case "generic_filter.json":
                assertInstanceOf(GenericFilter.class, filter, "Should be a GenericFilter");
                break;
            default:
                fail("Unknown filter type for file: " + fileName);
        }
    }

    private void verifyFilterProperties(Filter filter) {
        // Verify common properties
        assertNotNull(filter.getOperator(), "Operator should not be null");

        // Verify specific properties based on filter type
        if (filter instanceof EqualsFilter equalsFilter) {
            assertEquals("name", equalsFilter.getField());
            assertEquals("John Doe", equalsFilter.getValue());
        } else if (filter instanceof NotEqualsFilter notEqualsFilter) {
            assertEquals("status", notEqualsFilter.getField());
            assertEquals("inactive", notEqualsFilter.getValue());
        } else if (filter instanceof InFilter inFilter) {
            assertEquals("category", inFilter.getField());
            assertNotNull(inFilter.getValues());
            assertEquals(3, inFilter.getValues().size());
            assertTrue(inFilter.getValues().contains("electronics"));
        } else if (filter instanceof NotInFilter notInFilter) {
            assertEquals("status", notInFilter.getField());
            assertNotNull(notInFilter.getValues());
            assertEquals(3, notInFilter.getValues().size());
            assertTrue(notInFilter.getValues().contains("deleted"));
        } else if (filter instanceof ExistsFilter existsFilter) {
            assertEquals("email", existsFilter.getField());
        } else if (filter instanceof MissingFilter missingFilter) {
            assertEquals("phoneNumber", missingFilter.getField());
        } else if (filter instanceof ContainsFilter containsFilter) {
            assertEquals("tags", containsFilter.getField());
            assertEquals("premium", containsFilter.getValue());
        } else if (filter instanceof AnyFilter anyFilter) {
            assertEquals("status", anyFilter.getField());
        } else if (filter instanceof AndFilter andFilter) {
            assertNotNull(andFilter.getFilters());
            assertEquals(2, andFilter.getFilters().size());
        } else if (filter instanceof OrFilter orFilter) {
            assertNotNull(orFilter.getFilters());
            assertEquals(2, orFilter.getFilters().size());
        } else if (filter instanceof NotFilter notFilter) {
            assertNotNull(notFilter.getFilter());
            assertInstanceOf(EqualsFilter.class, notFilter.getFilter());
        } else if (filter instanceof GreaterThanFilter greaterThanFilter) {
            assertEquals("age", greaterThanFilter.getField());
            assertEquals(18, greaterThanFilter.getValue());
        } else if (filter instanceof GreaterEqualFilter greaterEqualFilter) {
            assertEquals("score", greaterEqualFilter.getField());
            assertEquals(75, greaterEqualFilter.getValue());
        } else if (filter instanceof LessThanFilter lessThanFilter) {
            assertEquals("price", lessThanFilter.getField());
            assertEquals(100, lessThanFilter.getValue());
        } else if (filter instanceof LessEqualFilter lessEqualFilter) {
            assertEquals("quantity", lessEqualFilter.getField());
            assertEquals(10, lessEqualFilter.getValue());
        } else if (filter instanceof BetweenFilter betweenFilter) {
            assertEquals("age", betweenFilter.getField());
            assertEquals(18, betweenFilter.getFrom());
            assertEquals(65, betweenFilter.getTo());
        } else if (filter instanceof StringStartsWithFilter stringStartsWithFilter) {
            assertEquals("name", stringStartsWithFilter.getField());
            assertEquals("John", stringStartsWithFilter.getValue());
            assertEquals(0, stringStartsWithFilter.getOffset());
        } else if (filter instanceof StringEndsWithFilter stringEndsWithFilter) {
            assertEquals("email", stringEndsWithFilter.getField());
            assertEquals("@example.com", stringEndsWithFilter.getValue());
        } else if (filter instanceof StringRegexMatchFilter stringRegexMatchFilter) {
            assertEquals("phoneNumber", stringRegexMatchFilter.getField());
            assertEquals("\\d{3}-\\d{3}-\\d{4}", stringRegexMatchFilter.getValue());
        } else if (filter instanceof GenericFilter genericFilter) {
            assertEquals("customField", genericFilter.getField());
            assertNotNull(genericFilter.getValue());
        }
    }

    @Test
    @DisplayName("Test serialization and deserialization of Filter objects")
    public void testFilterSerializationDeserialization() throws IOException {
        // Create a complex filter structure
        AndFilter filter = AndFilter.builder()
                .filter(EqualsFilter.builder().field("status").value("active").build())
                .filter(OrFilter.builder()
                                .filter(GreaterEqualFilter.builder().field("age").value(18).build())
                                .filter(StringStartsWithFilter.builder().field("name").value("A").offset(0).build())
                                .build())
                .build();

        // Serialize to JSON
        String json = OBJECT_MAPPER.writeValueAsString(filter);

        // Deserialize back to Filter
        Filter deserializedFilter = OBJECT_MAPPER.readValue(json, Filter.class);

        // Verify the deserialized filter
        assertInstanceOf(AndFilter.class, deserializedFilter);
        AndFilter andFilter = (AndFilter) deserializedFilter;
        assertEquals(2, andFilter.getFilters().size());

        // First filter should be an EqualsFilter
        Filter firstFilter = andFilter.getFilters().get(0);
        assertInstanceOf(EqualsFilter.class, firstFilter);
        EqualsFilter equalsFilter = (EqualsFilter) firstFilter;
        assertEquals("status", equalsFilter.getField());
        assertEquals("active", equalsFilter.getValue());

        // Second filter should be an OrFilter
        Filter secondFilter = andFilter.getFilters().get(1);
        assertInstanceOf(OrFilter.class, secondFilter);
        OrFilter orFilter = (OrFilter) secondFilter;
        assertEquals(2, orFilter.getFilters().size());
    }
}
