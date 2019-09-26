package com.phonepe.platform.query.dsl.logical;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author tushar.naik
 * @version 1.0  26/09/19 - 11:29 PM
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotFilter extends Filter {
    @Getter
    private Filter filter;

    public NotFilter() {
        super(FilterOperator.NOT);
    }

    @Builder
    public NotFilter(Filter filter) {
        super(FilterOperator.NOT);
        this.filter = filter;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean validate() {
        return filter.validate();
    }
}
