package com.phonepe.platform.query.dsl.numeric;


import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tushar.naik
 * @version 1.0  03/05/17 - 2:50 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LessThanFilter extends NumericBinaryFilter {

    public LessThanFilter() {
        super(FilterOperator.LESS_THAN);
    }

    public LessThanFilter(String field, Number value) {
        super(FilterOperator.LESS_THAN, field, value);
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
