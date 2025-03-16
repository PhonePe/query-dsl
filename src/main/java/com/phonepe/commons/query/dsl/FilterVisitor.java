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

/**
 * A visitor on various types of {@link Filter}s
 */
public interface FilterVisitor<T> {

    T visit(ContainsFilter filter);

    T visit(LessThanFilter filter);

    T visit(LessEqualFilter filter);

    T visit(GreaterThanFilter filter);

    T visit(BetweenFilter filter);

    T visit(GreaterEqualFilter filter);

    T visit(NotInFilter filter);

    T visit(NotEqualsFilter filter);

    T visit(MissingFilter filter);

    T visit(InFilter filter);

    T visit(ExistsFilter filter);

    T visit(EqualsFilter filter);

    T visit(AnyFilter filter);

    T visit(AndFilter andFilter);

    T visit(OrFilter orFilter);

    T visit(NotFilter notFilter);

    T visit(StringStartsWithFilter stringStartsWithFilter);

    T visit(StringEndsWithFilter stringEndsWithFilter);

    T visit(StringRegexMatchFilter stringRegexMatchFilter);

    T visit(GenericFilter genericFilter);
}
