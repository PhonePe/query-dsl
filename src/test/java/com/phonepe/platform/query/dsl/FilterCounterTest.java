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

import com.phonepe.platform.query.dsl.general.AnyFilter;
import com.phonepe.platform.query.dsl.general.EqualsFilter;
import com.phonepe.platform.query.dsl.general.InFilter;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterCounterTest {

    @Test
    public void testFilterCounter() {
        final var build = AndFilter.builder().filter(EqualsFilter.builder().build())
                .filter(InFilter.builder().build())
                .filter(new AnyFilter())
                .filter(OrFilter.builder().filter(new AnyFilter())
                                .filter(EqualsFilter.builder().build())
                                .build())
                .build();
        assertEquals(5, build.accept(new FilterCounter()).intValue());
    }
}