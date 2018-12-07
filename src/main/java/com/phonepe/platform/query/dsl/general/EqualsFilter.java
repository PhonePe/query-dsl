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
 * @version 1.0  03/05/17 - 2:34 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EqualsFilter extends Filter {

    private Object value;

    public EqualsFilter() {
        super(FilterOperator.EQUALS);
    }

    @Builder
    public EqualsFilter(String field, Object value) {
        super(FilterOperator.EQUALS, field);
        this.value = value;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
