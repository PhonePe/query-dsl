package com.phonepe.platform.query.dsl;

import com.phonepe.platform.query.dsl.general.*;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.NotFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import com.phonepe.platform.query.dsl.numeric.*;
import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;
import lombok.AllArgsConstructor;

/**
 * @author tushar.naik
 * @version 1.0  23/09/19 - 4:21 PM
 */
@AllArgsConstructor
public class AbstractFilterVisitor<T> implements FilterVisitor<T>{
    private T data;

    @Override
    public T visit(ContainsFilter filter) {
        return data;
    }

    @Override
    public T visit(LessThanFilter filter) {
        return data;
    }

    @Override
    public T visit(LessEqualFilter filter) {
        return data;
    }

    @Override
    public T visit(GreaterThanFilter filter) {
        return data;
    }

    @Override
    public T visit(BetweenFilter filter) {
        return data;
    }

    @Override
    public T visit(GreaterEqualFilter filter) {
        return data;
    }

    @Override
    public T visit(NotInFilter filter) {
        return data;
    }

    @Override
    public T visit(NotEqualsFilter filter) {
        return data;
    }

    @Override
    public T visit(MissingFilter filter) {
        return data;
    }

    @Override
    public T visit(InFilter filter) {
        return data;
    }

    @Override
    public T visit(ExistsFilter filter) {
        return data;
    }

    @Override
    public T visit(EqualsFilter filter) {
        return data;
    }

    @Override
    public T visit(AnyFilter filter) {
        return data;
    }

    @Override
    public T visit(AndFilter andFilter) {
        return data;
    }

    @Override
    public T visit(OrFilter orFilter) {
        return data;
    }

    @Override
    public T visit(NotFilter notFilter) {
        return data;
    }

    @Override
    public T visit(StringStartsWithFilter stringStartsWithFilter) {
        return data;
    }

    @Override
    public T visit(StringEndsWithFilter stringEndsWithFilter) {
        return data;
    }

    @Override
    public T visit(StringRegexMatchFilter stringRegexMatchFilter) {
        return data;
    }
}
