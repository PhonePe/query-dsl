package com.phonepe.commons.query.dsl.general;

import com.phonepe.commons.query.dsl.Filter;
import com.phonepe.commons.query.dsl.FilterOperator;
import com.phonepe.commons.query.dsl.FilterVisitor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HopeFilter extends Filter {

    private String value;

    public HopeFilter() {
        super(FilterOperator.HOPE);
    }

    @Builder
    public HopeFilter(final String field,
                      final String value) {
        super(FilterOperator.HOPE, field);
        this.value = value;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
