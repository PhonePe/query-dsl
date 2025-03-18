/*
 *  Copyright (c) 2025 Original Author(s), PhonePe India Pvt. Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.phonepe.commons.query.dsl;

import com.phonepe.commons.query.dsl.general.AnyFilter;
import com.phonepe.commons.query.dsl.general.ContainsFilter;
import com.phonepe.commons.query.dsl.general.EqualsFilter;
import com.phonepe.commons.query.dsl.general.ExistsFilter;
import com.phonepe.commons.query.dsl.general.GenericFilter;
import com.phonepe.commons.query.dsl.general.InFilter;
import com.phonepe.commons.query.dsl.general.MissingFilter;
import com.phonepe.commons.query.dsl.general.NotEqualsFilter;
import com.phonepe.commons.query.dsl.general.NotInFilter;
import com.phonepe.commons.query.dsl.logical.AndFilter;
import com.phonepe.commons.query.dsl.logical.NotFilter;
import com.phonepe.commons.query.dsl.logical.OrFilter;
import com.phonepe.commons.query.dsl.numeric.BetweenFilter;
import com.phonepe.commons.query.dsl.numeric.GreaterEqualFilter;
import com.phonepe.commons.query.dsl.numeric.GreaterThanFilter;
import com.phonepe.commons.query.dsl.numeric.LessEqualFilter;
import com.phonepe.commons.query.dsl.numeric.LessThanFilter;
import com.phonepe.commons.query.dsl.string.StringEndsWithFilter;
import com.phonepe.commons.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.commons.query.dsl.string.StringStartsWithFilter;

import java.util.stream.Stream;

/**
 * A visitor to identify the fields in a given filter.
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
