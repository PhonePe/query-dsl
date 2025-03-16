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

package com.phonepe.platform.query.dsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonepe.platform.query.dsl.general.AnyFilter;
import com.phonepe.platform.query.dsl.general.ContainsFilter;
import com.phonepe.platform.query.dsl.general.EqualsFilter;
import com.phonepe.platform.query.dsl.general.ExistsFilter;
import com.phonepe.platform.query.dsl.general.GenericFilter;
import com.phonepe.platform.query.dsl.general.InFilter;
import com.phonepe.platform.query.dsl.general.MissingFilter;
import com.phonepe.platform.query.dsl.general.NotEqualsFilter;
import com.phonepe.platform.query.dsl.general.NotInFilter;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.NotFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import com.phonepe.platform.query.dsl.numeric.BetweenFilter;
import com.phonepe.platform.query.dsl.numeric.GreaterEqualFilter;
import com.phonepe.platform.query.dsl.numeric.GreaterThanFilter;
import com.phonepe.platform.query.dsl.numeric.LessEqualFilter;
import com.phonepe.platform.query.dsl.numeric.LessThanFilter;
import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;
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
                assertTrue(filter instanceof EqualsFilter, "Should be an EqualsFilter");
                break;
            case "not_equals_filter.json":
                assertTrue(filter instanceof NotEqualsFilter, "Should be a NotEqualsFilter");
                break;
            case "in_filter.json":
                assertTrue(filter instanceof InFilter, "Should be an InFilter");
                break;
            case "not_in_filter.json":
                assertTrue(filter instanceof NotInFilter, "Should be a NotInFilter");
                break;
            case "exists_filter.json":
                assertTrue(filter instanceof ExistsFilter, "Should be an ExistsFilter");
                break;
            case "missing_filter.json":
                assertTrue(filter instanceof MissingFilter, "Should be a MissingFilter");
                break;
            case "contains_filter.json":
                assertTrue(filter instanceof ContainsFilter, "Should be a ContainsFilter");
                break;
            case "any_filter.json":
                assertTrue(filter instanceof AnyFilter, "Should be an AnyFilter");
                break;
            case "and_filter.json":
                assertTrue(filter instanceof AndFilter, "Should be an AndFilter");
                break;
            case "or_filter.json":
                assertTrue(filter instanceof OrFilter, "Should be an OrFilter");
                break;
            case "not_filter.json":
                assertTrue(filter instanceof NotFilter, "Should be a NotFilter");
                break;
            case "greater_than_filter.json":
                assertTrue(filter instanceof GreaterThanFilter, "Should be a GreaterThanFilter");
                break;
            case "greater_equal_filter.json":
                assertTrue(filter instanceof GreaterEqualFilter, "Should be a GreaterEqualFilter");
                break;
            case "less_than_filter.json":
                assertTrue(filter instanceof LessThanFilter, "Should be a LessThanFilter");
                break;
            case "less_equal_filter.json":
                assertTrue(filter instanceof LessEqualFilter, "Should be a LessEqualFilter");
                break;
            case "between_filter.json":
                assertTrue(filter instanceof BetweenFilter, "Should be a BetweenFilter");
                break;
            case "string_starts_with_filter.json":
                assertTrue(filter instanceof StringStartsWithFilter, "Should be a StringStartsWithFilter");
                break;
            case "string_ends_with_filter.json":
                assertTrue(filter instanceof StringEndsWithFilter, "Should be a StringEndsWithFilter");
                break;
            case "string_regex_match_filter.json":
                assertTrue(filter instanceof StringRegexMatchFilter, "Should be a StringRegexMatchFilter");
                break;
            case "generic_filter.json":
                assertTrue(filter instanceof GenericFilter, "Should be a GenericFilter");
                break;
            default:
                fail("Unknown filter type for file: " + fileName);
        }
    }

    private void verifyFilterProperties(Filter filter) {
        // Verify common properties
        assertNotNull(filter.getOperator(), "Operator should not be null");

        // Verify specific properties based on filter type
        if (filter instanceof EqualsFilter) {
            EqualsFilter equalsFilter = (EqualsFilter) filter;
            assertEquals("name", equalsFilter.getField());
            assertEquals("John Doe", equalsFilter.getValue());
        } else if (filter instanceof NotEqualsFilter) {
            NotEqualsFilter notEqualsFilter = (NotEqualsFilter) filter;
            assertEquals("status", notEqualsFilter.getField());
            assertEquals("inactive", notEqualsFilter.getValue());
        } else if (filter instanceof InFilter) {
            InFilter inFilter = (InFilter) filter;
            assertEquals("category", inFilter.getField());
            assertNotNull(inFilter.getValues());
            assertEquals(3, inFilter.getValues().size());
            assertTrue(inFilter.getValues().contains("electronics"));
        } else if (filter instanceof NotInFilter) {
            NotInFilter notInFilter = (NotInFilter) filter;
            assertEquals("status", notInFilter.getField());
            assertNotNull(notInFilter.getValues());
            assertEquals(3, notInFilter.getValues().size());
            assertTrue(notInFilter.getValues().contains("deleted"));
        } else if (filter instanceof ExistsFilter) {
            ExistsFilter existsFilter = (ExistsFilter) filter;
            assertEquals("email", existsFilter.getField());
        } else if (filter instanceof MissingFilter) {
            MissingFilter missingFilter = (MissingFilter) filter;
            assertEquals("phoneNumber", missingFilter.getField());
        } else if (filter instanceof ContainsFilter) {
            ContainsFilter containsFilter = (ContainsFilter) filter;
            assertEquals("tags", containsFilter.getField());
            assertEquals("premium", containsFilter.getValue());
        } else if (filter instanceof AnyFilter) {
            AnyFilter anyFilter = (AnyFilter) filter;
            assertEquals("status", anyFilter.getField());
        } else if (filter instanceof AndFilter) {
            AndFilter andFilter = (AndFilter) filter;
            assertNotNull(andFilter.getFilters());
            assertEquals(2, andFilter.getFilters().size());
        } else if (filter instanceof OrFilter) {
            OrFilter orFilter = (OrFilter) filter;
            assertNotNull(orFilter.getFilters());
            assertEquals(2, orFilter.getFilters().size());
        } else if (filter instanceof NotFilter) {
            NotFilter notFilter = (NotFilter) filter;
            assertNotNull(notFilter.getFilter());
            assertTrue(notFilter.getFilter() instanceof EqualsFilter);
        } else if (filter instanceof GreaterThanFilter) {
            GreaterThanFilter greaterThanFilter = (GreaterThanFilter) filter;
            assertEquals("age", greaterThanFilter.getField());
            assertEquals(18, greaterThanFilter.getValue());
        } else if (filter instanceof GreaterEqualFilter) {
            GreaterEqualFilter greaterEqualFilter = (GreaterEqualFilter) filter;
            assertEquals("score", greaterEqualFilter.getField());
            assertEquals(75, greaterEqualFilter.getValue());
        } else if (filter instanceof LessThanFilter) {
            LessThanFilter lessThanFilter = (LessThanFilter) filter;
            assertEquals("price", lessThanFilter.getField());
            assertEquals(100, lessThanFilter.getValue());
        } else if (filter instanceof LessEqualFilter) {
            LessEqualFilter lessEqualFilter = (LessEqualFilter) filter;
            assertEquals("quantity", lessEqualFilter.getField());
            assertEquals(10, lessEqualFilter.getValue());
        } else if (filter instanceof BetweenFilter) {
            BetweenFilter betweenFilter = (BetweenFilter) filter;
            assertEquals("age", betweenFilter.getField());
            assertEquals(18, betweenFilter.getFrom());
            assertEquals(65, betweenFilter.getTo());
        } else if (filter instanceof StringStartsWithFilter) {
            StringStartsWithFilter stringStartsWithFilter = (StringStartsWithFilter) filter;
            assertEquals("name", stringStartsWithFilter.getField());
            assertEquals("John", stringStartsWithFilter.getValue());
            assertEquals(0, stringStartsWithFilter.getOffset());
        } else if (filter instanceof StringEndsWithFilter) {
            StringEndsWithFilter stringEndsWithFilter = (StringEndsWithFilter) filter;
            assertEquals("email", stringEndsWithFilter.getField());
            assertEquals("@example.com", stringEndsWithFilter.getValue());
        } else if (filter instanceof StringRegexMatchFilter) {
            StringRegexMatchFilter stringRegexMatchFilter = (StringRegexMatchFilter) filter;
            assertEquals("phoneNumber", stringRegexMatchFilter.getField());
            assertEquals("\\d{3}-\\d{3}-\\d{4}", stringRegexMatchFilter.getValue());
        } else if (filter instanceof GenericFilter) {
            GenericFilter genericFilter = (GenericFilter) filter;
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
        assertTrue(deserializedFilter instanceof AndFilter);
        AndFilter andFilter = (AndFilter) deserializedFilter;
        assertEquals(2, andFilter.getFilters().size());

        // First filter should be an EqualsFilter
        Filter firstFilter = andFilter.getFilters().get(0);
        assertTrue(firstFilter instanceof EqualsFilter);
        EqualsFilter equalsFilter = (EqualsFilter) firstFilter;
        assertEquals("status", equalsFilter.getField());
        assertEquals("active", equalsFilter.getValue());

        // Second filter should be an OrFilter
        Filter secondFilter = andFilter.getFilters().get(1);
        assertTrue(secondFilter instanceof OrFilter);
        OrFilter orFilter = (OrFilter) secondFilter;
        assertEquals(2, orFilter.getFilters().size());
    }
}
