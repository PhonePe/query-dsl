package com.phonepe.platform.query.dsl;

import com.phonepe.platform.query.dsl.string.StringEndsWithFilter;
import com.phonepe.platform.query.dsl.string.StringRegexMatchFilter;
import com.phonepe.platform.query.dsl.string.StringStartsWithFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tushar.naik
 * @version 1.0  23/09/19 - 4:18 PM
 */
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
        Assert.assertTrue(filter.accept(filterVisitor));

        StringStartsWithFilter filter2
                = StringStartsWithFilter.builder().field("Samsung S3")
                                        .value("ams")
                                        .offset(1)
                                        .build();
        Assert.assertTrue(filter2.accept(filterVisitor));

        StringEndsWithFilter filter3
                = StringEndsWithFilter.builder().field("Samsung S3")
                                      .value("S3")
                                      .build();
        Assert.assertTrue(filter3.accept(filterVisitor));

        StringRegexMatchFilter filter4
                = StringRegexMatchFilter.builder().field("Samsung S3")
                                      .value(".*")
                                      .build();
        Assert.assertTrue(filter4.accept(filterVisitor));
    }
}
