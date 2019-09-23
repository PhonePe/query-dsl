package com.phonepe.platform.query.dsl.string;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tushar.naik
 * @version 1.0  23/09/19 - 12:40 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StringStartsWithFilter extends Filter {
    private String value;
    private int offset;

    public StringStartsWithFilter() {
        super(FilterOperator.STR_STARTS_WITH);
    }

    @Builder
    public StringStartsWithFilter(String field, String value, int offset) {
        super(FilterOperator.STR_STARTS_WITH, field);
        this.value = value;
        this.offset = offset;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
