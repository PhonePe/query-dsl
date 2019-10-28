package com.phonepe.platform.query.dsl;

import com.phonepe.platform.query.dsl.general.*;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.NotFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import com.phonepe.platform.query.dsl.numeric.*;
import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;

import java.util.stream.Stream;

/**
 * @author tushar.naik
 * @version 1.0  19/09/18 - 3:27 PM
 */
public class FilterFieldIdentifier implements FilterVisitor<Stream<String>> {
    @Override
    public Stream<String> visit(ContainsFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(LessThanFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(LessEqualFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(GreaterThanFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(BetweenFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(GreaterEqualFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(NotInFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(NotEqualsFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(MissingFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(InFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(ExistsFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(EqualsFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(AnyFilter filter) {
        return Stream.of(filter.getField());
    }

    @Override
    public Stream<String> visit(AndFilter andFilter) {
        return andFilter.getFilters()
                        .stream()
                        .map(k -> k.accept(this))
                        .reduce(Stream::concat)
                        .orElse(Stream.empty());
    }

    @Override
    public Stream<String> visit(OrFilter orFilter) {
        return orFilter.getFilters()
                       .stream()
                       .map(k -> k.accept(this))
                       .reduce(Stream::concat)
                       .orElse(Stream.empty());
    }

    @Override
    public Stream<String> visit(NotFilter notFilter) {
        return notFilter.getFilter().accept(this);
    }

    @Override
    public Stream<String> visit(StringStartsWithFilter stringStartsWithFilter) {
        return Stream.of(stringStartsWithFilter.getField());
    }

    @Override
    public Stream<String> visit(StringEndsWithFilter stringEndsWithFilter) {
        return Stream.of(stringEndsWithFilter.getField());
    }

    @Override
    public Stream<String> visit(StringRegexMatchFilter stringRegexMatchFilter) {
        return Stream.of(stringRegexMatchFilter.getField());
    }

    @Override
    public Stream<String> visit(GenericFilter genericFilter) {
        return Stream.of(genericFilter.getField());
    }
}
