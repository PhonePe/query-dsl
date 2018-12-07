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
 * @version 1.0  03/05/17 - 2:42 PM
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MissingFilter extends Filter {

    public MissingFilter() {
        super(FilterOperator.MISSING);
    }

    @Builder
    public MissingFilter(String field) {
        super(FilterOperator.MISSING, field);
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }

}
