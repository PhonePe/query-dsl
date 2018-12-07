package com.phonepe.platform.query.dsl.general;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author tushar.naik
 * @version 1.0  03/05/17 - 2:32 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InFilter extends Filter {

    private List<Object> values;

    public InFilter() {
        super(FilterOperator.IN);
    }

    @Builder
    public InFilter(String field, List<Object> values) {
        super(FilterOperator.IN, field);
        this.values = values;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
