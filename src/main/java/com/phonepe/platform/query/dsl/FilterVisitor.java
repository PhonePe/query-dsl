package com.phonepe.platform.query.dsl;

import com.phonepe.platform.query.dsl.general.*;
import com.phonepe.platform.query.dsl.general.ContainsFilter;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import com.phonepe.platform.query.dsl.numeric.*;
import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;

/**
 * A visitor on various types of {@link Filter}s
 *
 * @author tushar.naik
 * @version 1.0  02/05/17 - 6:43 PM
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

    T visit(StringStartsWithFilter stringStartsWithFilter);

    T visit(StringEndsWithFilter stringEndsWithFilter);

    T visit(StringRegexMatchFilter stringRegexMatchFilter);
}
