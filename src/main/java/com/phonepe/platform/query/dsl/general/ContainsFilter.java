package com.phonepe.platform.query.dsl.general;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tushar.naik
 * @version 1.0  03/05/17 - 2:43 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContainsFilter extends Filter {

    private String value;

    public ContainsFilter() {
        super(FilterOperator.CONTAINS);
    }

    public ContainsFilter(String field, String value) {
        super(FilterOperator.CONTAINS, field);
        this.value = value;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
