package com.phonepe.platform.query.dsl.general;

import com.phonepe.platform.query.dsl.Filter;
import com.phonepe.platform.query.dsl.FilterOperator;
import com.phonepe.platform.query.dsl.FilterVisitor;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author tushar.naik
 * @version 1.0  03/05/17 - 2:46 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotInFilter extends Filter {

    private List<Object> values;

    public NotInFilter() {
        super(FilterOperator.NOT_IN);
    }

    @Builder
    public NotInFilter(String field, List<Object> values) {
        super(FilterOperator.NOT_IN, field);
        this.values = values;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
