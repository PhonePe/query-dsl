package com.phonepe.platform.query.dsl.numeric;

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
public class LessEqualFilter extends NumericBinaryFilter {

    public LessEqualFilter() {
        super(FilterOperator.LESS_EQUAL);
    }

    @Builder
    public LessEqualFilter(String field, Number value) {
        super(FilterOperator.LESS_EQUAL, field, value);
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
