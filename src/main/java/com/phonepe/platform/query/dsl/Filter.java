package com.phonepe.platform.query.dsl;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.phonepe.platform.query.dsl.general.*;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import com.phonepe.platform.query.dsl.numeric.*;
import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author tushar.naik
 * @version 1.0  02/05/17 - 5:46 PM
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "operator")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GreaterEqualFilter.class, name = FilterOperator.GREATER_EQUAL),
        @JsonSubTypes.Type(value = GreaterThanFilter.class, name = FilterOperator.GREATER_THAN),
        @JsonSubTypes.Type(value = LessEqualFilter.class, name = FilterOperator.LESS_EQUAL),
        @JsonSubTypes.Type(value = LessThanFilter.class, name = FilterOperator.LESS_THAN),
        @JsonSubTypes.Type(value = BetweenFilter.class, name = FilterOperator.BETWEEN),
        @JsonSubTypes.Type(value = EqualsFilter.class, name = FilterOperator.EQUALS),
        @JsonSubTypes.Type(value = InFilter.class, name = FilterOperator.IN),
        @JsonSubTypes.Type(value = NotInFilter.class, name = FilterOperator.NOT_IN),
        @JsonSubTypes.Type(value = NotEqualsFilter.class, name = FilterOperator.NOT_EQUALS),
        @JsonSubTypes.Type(value = AnyFilter.class, name = FilterOperator.ANY),
        @JsonSubTypes.Type(value = ExistsFilter.class, name = FilterOperator.EXISTS),
        @JsonSubTypes.Type(value = MissingFilter.class, name = FilterOperator.MISSING),
        @JsonSubTypes.Type(value = ContainsFilter.class, name = FilterOperator.CONTAINS),
        @JsonSubTypes.Type(value = AndFilter.class, name = FilterOperator.AND),
        @JsonSubTypes.Type(value = OrFilter.class, name = FilterOperator.OR),
        @JsonSubTypes.Type(value = StringEndsWithFilter.class, name = FilterOperator.STR_ENDS_WITH),
        @JsonSubTypes.Type(value = StringStartsWithFilter.class, name = FilterOperator.STR_STARTS_WITH),
        @JsonSubTypes.Type(value = StringRegexMatchFilter.class, name = FilterOperator.STR_REGEX_MATCH)
})
@Data
public abstract class Filter {

    /* one of the above operators impls */
    private final String operator;

    private String field;

    protected Filter(String operator) {
        this.operator = operator;
    }

    protected Filter(String operator, String field) {
        this.operator = operator;
        this.field = field;
    }

    /**
     * check the validity of filter
     *
     * @return true if valid, else error
     */
    @SneakyThrows
    public boolean validate() {
        if (field == null || field.isEmpty()) {
            throw new RuntimeException("INVALID_FILTER field cannot be empty");
        }
        return true;
    }

    /**
     * @param visitor visitor impl
     * @param <V>     type fo return
     * @return act on the visitor with itself (the subclass), and return what he returns
     */
    public abstract <V> V accept(FilterVisitor<V> visitor);
}
