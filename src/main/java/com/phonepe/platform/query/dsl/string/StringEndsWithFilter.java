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
public class StringEndsWithFilter extends Filter {
    private String value;

    public StringEndsWithFilter() {
        super(FilterOperator.STR_ENDS_WITH);
    }

    @Builder
    public StringEndsWithFilter(String field, String value) {
        super(FilterOperator.STR_ENDS_WITH, field);
        this.value = value;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
