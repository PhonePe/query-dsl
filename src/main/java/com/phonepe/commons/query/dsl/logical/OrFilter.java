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

package com.phonepe.commons.query.dsl.logical;

import com.phonepe.commons.query.dsl.Filter;
import com.phonepe.commons.query.dsl.FilterOperator;
import com.phonepe.commons.query.dsl.FilterVisitor;
import lombok.*;

import java.util.List;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrFilter extends Filter {

    @Singular
    @Getter
    private List<Filter> filters;

    protected OrFilter() {
        super(FilterOperator.OR);
    }

    @Builder
    public OrFilter(@Singular List<Filter> filters) {
        super(FilterOperator.OR);
        this.filters = filters;
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean validate(){
        return filters.stream().map(Filter::validate).reduce((x, y) -> x && y).orElse(false);
    }
}
