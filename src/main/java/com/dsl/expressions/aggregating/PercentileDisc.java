package com.dsl.expressions.aggregating;

import com.dsl.expressions.param.SelectorExpression;

import java.text.DecimalFormat;

/**
 * Selector expression that collects expressions.
 */
public class PercentileDisc extends AggregatingExpression {
    public final String percentile;

    public PercentileDisc(Double percentile, SelectorExpression o) {
        super("percentileDisc", o);
        DecimalFormat df = new DecimalFormat("0.0");
        if (percentile > 1.0 || percentile < 0.0) {
            throw new UnsupportedOperationException("Percentile is a numeric value between 0.0 and 1.0");
        }
        this.percentile = df.format(percentile);
    }

    @Override
    public String asString() {
        return String.format("%s(%s, %s)", expression, e.asString(), percentile);
    }
}
