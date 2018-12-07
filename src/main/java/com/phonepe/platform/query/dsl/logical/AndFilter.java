package com.phonepe.platform.query.dsl.logical;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import java.util.List;
import lombok.*;

/**
 * @author tushar.naik
 * @version 1.0  08/05/17 - 2:22 PM
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AndFilter extends Filter {

    @Singular
    @Getter
    List<Filter> filters;

    protected AndFilter() {
        super(FilterOperator.AND);
    }

    @Builder
    public AndFilter(@Singular List<Filter> filters) {
        super(FilterOperator.AND);
        this.filters = filters;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean validate() {
        /* validate all the list of filters, and all need to be valid */
        return filters.stream().map(Filter::validate).reduce((x, y) -> x && y).orElse(false);
    }
}
