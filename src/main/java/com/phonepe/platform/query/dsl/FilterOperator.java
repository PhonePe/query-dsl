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

public class FilterOperator {

    /* numeric */
    public static final String GREATER_EQUAL = "GREATER_EQUAL";
    public static final String GREATER_THAN = "GREATER_THAN";
    public static final String LESS_EQUAL = "LESS_EQUAL";
    public static final String LESS_THAN = "LESS_THAN";
    public static final String BETWEEN = "BETWEEN";

    /* general */
    public static final String EQUALS = "EQUALS";
    public static final String IN = "IN";
    public static final String NOT_IN = "NOT_IN";
    public static final String NOT_EQUALS = "NOT_EQUALS";
    public static final String ANY = "ANY";
    public static final String EXISTS = "EXISTS";
    public static final String MISSING = "MISSING";
    public static final String CONTAINS = "CONTAINS";
    public static final String GENERIC = "GENERIC";

    /* string */
    public static final String STR_ENDS_WITH = "STRING_ENDS_WITH";
    public static final String STR_STARTS_WITH = "STRING_STARTS_WITH";
    public static final String STR_REGEX_MATCH = "STRING_REGEX_MATCH";

    /* logical filters */
    public static final String AND = "AND";
    public static final String NOT = "NOT";
    public static final String OR = "OR";
}
