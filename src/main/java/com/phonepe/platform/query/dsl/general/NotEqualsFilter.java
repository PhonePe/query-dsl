package com.phonepe.platform.query.dsl.general;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tushar.naik
 * @version 1.0  03/05/17 - 2:47 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotEqualsFilter extends Filter {

    private Object value;

    public NotEqualsFilter() {
        super(FilterOperator.NOT_EQUALS);
    }

    @Builder
    public NotEqualsFilter(String field, Object value) {
        super(FilterOperator.NOT_EQUALS, field);
        this.value = value;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
