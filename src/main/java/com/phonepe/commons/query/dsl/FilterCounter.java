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

public class FilterCounter implements FilterVisitor<Integer> {

    @Override
    public Integer visit(ContainsFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(LessThanFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(LessEqualFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(GreaterThanFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(BetweenFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(GreaterEqualFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(NotInFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(NotEqualsFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(MissingFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(InFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(ExistsFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(EqualsFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(AnyFilter filter) {
        return 1;
    }

    @Override
    public Integer visit(AndFilter andFilter) {
        return andFilter.getFilters()
                .stream()
                .mapToInt(k -> k.accept(this))
                .sum();
    }

    @Override
    public Integer visit(OrFilter orFilter) {
        return orFilter.getFilters()
                .stream()
                .mapToInt(k -> k.accept(this))
                .sum();
    }

    @Override
    public Integer visit(NotFilter notFilter) {
        return notFilter.getFilter().accept(this);
    }

    @Override
    public Integer visit(StringStartsWithFilter stringStartsWithFilter) {
        return 1;
    }

    @Override
    public Integer visit(StringEndsWithFilter stringEndsWithFilter) {
        return 1;
    }

    @Override
    public Integer visit(StringRegexMatchFilter stringRegexMatchFilter) {
        return 1;
    }

    @Override
    public Integer visit(GenericFilter genericFilter) {
        return 1;
    }

}
