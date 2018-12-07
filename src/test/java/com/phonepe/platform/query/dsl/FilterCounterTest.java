package com.phonepe.platform.query.dsl;

import com.phonepe.platform.query.dsl.general.AnyFilter;
import com.phonepe.platform.query.dsl.general.EqualsFilter;
import com.phonepe.platform.query.dsl.general.InFilter;
import com.phonepe.platform.query.dsl.logical.AndFilter;
import com.phonepe.platform.query.dsl.logical.OrFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tushar.naik
 * @version 1.0  2018-12-07 - 14:51
 */
public class FilterCounterTest {

    @Test
    public void testFilterCounter() {
        AndFilter build = AndFilter.builder().filter(EqualsFilter.builder().build())
                                   .filter(InFilter.builder().build())
                                   .filter(new AnyFilter())
                                   .filter(OrFilter.builder().filter(new AnyFilter())
                                                   .filter(EqualsFilter.builder().build())
                                                   .build())
                                   .build();
        Assert.assertEquals(5, build.accept(new FilterCounter()).intValue());
    }
}