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

package com.phonepe.platform.query.dsl;

import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterEvalTest {
    private FilterVisitor<Boolean> filterVisitor = new AbstractFilterVisitor<Boolean>(false) {
        @Override
        public Boolean visit(StringStartsWithFilter stringStartsWithFilter) {
            return stringStartsWithFilter.getField()
                    .startsWith(stringStartsWithFilter.getValue(), stringStartsWithFilter.getOffset());
        }

        @Override
        public Boolean visit(StringEndsWithFilter stringEndsWithFilter) {
            return stringEndsWithFilter.getField().endsWith(stringEndsWithFilter.getValue());
        }

        @Override
        public Boolean visit(StringRegexMatchFilter stringRegexMatchFilter) {
            return stringRegexMatchFilter.getField().matches(stringRegexMatchFilter.getValue());
        }
    };

    @Test
    public void testEquals() {
        StringStartsWithFilter filter
                = StringStartsWithFilter.builder().field("Samsung S3")
                .value("Samsu")
                .build();
        assertTrue(filter.accept(filterVisitor));

        StringStartsWithFilter filter2
                = StringStartsWithFilter.builder().field("Samsung S3")
                .value("ams")
                .offset(1)
                .build();
        assertTrue(filter2.accept(filterVisitor));

        StringEndsWithFilter filter3
                = StringEndsWithFilter.builder().field("Samsung S3")
                .value("S3")
                .build();
        assertTrue(filter3.accept(filterVisitor));

        StringRegexMatchFilter filter4
                = StringRegexMatchFilter.builder().field("Samsung S3")
                .value(".*")
                .build();
        assertTrue(filter4.accept(filterVisitor));
    }
}
