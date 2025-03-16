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
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbstractFilterVisitor<T> implements FilterVisitor<T> {
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

    @Override
    public T visit(GenericFilter genericFilter) {
        return data;
    }
}
