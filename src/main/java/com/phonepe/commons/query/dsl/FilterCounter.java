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

import com.phonepe.commons.query.dsl.logical.AndFilter;
import com.phonepe.commons.query.dsl.logical.NotFilter;
import com.phonepe.commons.query.dsl.logical.OrFilter;

/**
 * A visitor to count the number of filters in a given filter.
 */
public class FilterCounter extends AbstractFilterVisitor<Integer> {

    public FilterCounter() {
        super(1);
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

}
