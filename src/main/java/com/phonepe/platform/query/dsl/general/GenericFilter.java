package com.phonepe.platform.query.dsl.general;

import com.phonepe.platform.query.dsl.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author utsab.b on 17/10/19.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GenericFilter extends Filter {

    private Object value;

    public GenericFilter() {
        super(FilterOperator.GENERIC);
    }

    @Builder
    public GenericFilter(String field, Object value) {
        super(FilterOperator.GENERIC, field);
        this.value = value;
    }


    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
