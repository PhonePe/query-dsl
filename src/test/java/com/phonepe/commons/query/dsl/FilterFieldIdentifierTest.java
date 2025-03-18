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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FilterFieldIdentifierTest {

    private final FilterFieldIdentifier fieldIdentifier = new FilterFieldIdentifier();

    @Test
    void testSingleFieldFilters() {
        // Testing all individual filter types
        assertEquals(List.of("field1"), getFields(new ContainsFilter("field1", "test", false)));
        assertEquals(List.of("field2"), getFields(LessThanFilter.builder().field("field2").build()));
        assertEquals(List.of("field3"), getFields(LessEqualFilter.builder().field("field3").build()));
        assertEquals(List.of("field4"), getFields(GreaterThanFilter.builder().field("field4").build()));
        assertEquals(List.of("field5"), getFields(GreaterEqualFilter.builder().field("field5").build()));
        assertEquals(List.of("field6"), getFields(BetweenFilter.builder().field("field6").build()));
        assertEquals(List.of("field7"), getFields(NotInFilter.builder().field("field7").build()));
        assertEquals(List.of("field8"), getFields(NotEqualsFilter.builder().field("field8").build()));
        assertEquals(List.of("field9"), getFields(MissingFilter.builder().field("field9").build()));
        assertEquals(List.of("field10"), getFields(InFilter.builder().field("field10").build()));
        assertEquals(List.of("field11"), getFields(ExistsFilter.builder().field("field11").build()));
        assertEquals(List.of("field12"), getFields(EqualsFilter.builder().field("field12").build()));
        assertEquals(List.of("dummy"), getFields(new AnyFilter()));
        assertEquals(List.of("field14"), getFields(StringStartsWithFilter.builder().field("field14").build()));
        assertEquals(List.of("field15"), getFields(StringEndsWithFilter.builder().field("field15").build()));
        assertEquals(List.of("field16"), getFields(StringRegexMatchFilter.builder().field("field16").build()));
        assertEquals(List.of("field17"), getFields(GenericFilter.builder().field("field17").build()));
    }

    @Test
    void testAndFilter() {
        // Empty AND should return empty stream
        assertTrue(getFields(AndFilter.builder().filters(Collections.emptyList()).build()).isEmpty());

        // AND with multiple filters should return all fields
        AndFilter andFilter = AndFilter.builder()
                .filter(EqualsFilter.builder().field("field1").build())
                .filter(InFilter.builder().field("field2").build())
                .filter(MissingFilter.builder().field("field3").build())
                .build();

        List<String> fields = getFields(andFilter);
        assertEquals(3, fields.size());
        assertTrue(fields.containsAll(Arrays.asList("field1", "field2", "field3")));
    }

    @Test
    void testOrFilter() {
        // Empty OR should return empty stream
        assertTrue(getFields(OrFilter.builder().filters(Collections.emptyList()).build()).isEmpty());

        // OR with multiple filters should return all fields
        OrFilter orFilter = OrFilter.builder()
                .filter(ExistsFilter.builder().field("fieldA").build())
                .filter(LessThanFilter.builder().field("fieldB").build())
                .filter(GreaterEqualFilter.builder().field("fieldC").build())
                .build();

        List<String> fields = getFields(orFilter);
        assertEquals(3, fields.size());
        assertTrue(fields.containsAll(Arrays.asList("fieldA", "fieldB", "fieldC")));
    }

    @Test
    void testNotFilter() {
        // NOT filter should return field from its child filter
        assertEquals(List.of("notField"),
                     getFields(NotFilter.builder().filter(EqualsFilter.builder().field("notField").build()).build()));
    }

    @Test
    void testNestedFilters() {
        // Complex nested filters
        AndFilter complexFilter = AndFilter.builder()
                .filter(EqualsFilter.builder().field("root1").build())
                .filter(OrFilter.builder()
                                .filter(ExistsFilter.builder().field("nested1").build())
                                .filter(NotFilter.builder()
                                                .filter(OrFilter.builder()
                                                                .filter(InFilter.builder().field("deep1").build())
                                                                .filter(BetweenFilter.builder().field("deep2").build())
                                                                .build())
                                                .build())
                                .build())
                .build();

        List<String> fields = getFields(complexFilter);
        assertEquals(4, fields.size());
        assertTrue(fields.containsAll(Arrays.asList("root1", "nested1", "deep1", "deep2")));
    }

    @Test
    void testDuplicateFields() {
        // Test with duplicate field names
        AndFilter filterWithDuplicates = AndFilter.builder()
                .filter(EqualsFilter.builder().field("same").build())
                .filter(InFilter.builder().field("same").build())
                .filter(ExistsFilter.builder().field("different").build())
                .build();

        List<String> fields = getFields(filterWithDuplicates);
        assertEquals(3, fields.size()); // Stream doesn't deduplicate by default
        assertEquals(2, fields.stream().distinct().count()); // Only 2 unique fields
    }

    @Test
    void testComplexHierarchicalStructure() {
        // Create a complex filter structure with multiple levels
        Filter complexFilter = AndFilter.builder()
                .filter(EqualsFilter.builder().field("level1Field1").build())
                .filter(OrFilter.builder()
                                .filter(new ContainsFilter("level2Field1", "test", false))
                                .filter(AndFilter.builder()
                                                .filter(GreaterThanFilter.builder().field("level3Field1").build())
                                                .filter(NotFilter.builder()
                                                                .filter(BetweenFilter.builder().field("level4Field1").build())
                                                                .build())
                                                .build())
                                .build())
                .filter(NotInFilter.builder().field("level1Field2").build())
                .build();

        List<String> fields = getFields(complexFilter);
        assertEquals(5, fields.size());
        assertTrue(fields.containsAll(Arrays.asList(
                "level1Field1", "level2Field1", "level3Field1", "level4Field1", "level1Field2")));
    }

    // Helper method to convert stream to list for easier assertion
    private List<String> getFields(Filter filter) {
        return filter.accept(fieldIdentifier).collect(Collectors.toList());
    }

}