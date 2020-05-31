package com.dsl.clauses;

import com.dsl.AsString;
import com.dsl.StringUtils;
import com.dsl.clauses.creates.AfterCreate;
import com.dsl.clauses.creates.CreateClause;
import com.dsl.clauses.creates.CreatePath;
import com.dsl.clauses.delete.AfterDelete;
import com.dsl.clauses.delete.Delete;
import com.dsl.clauses.delete.DeleteClause;
import com.dsl.clauses.limit.AfterLimit;
import com.dsl.clauses.limit.LimitClause;
import com.dsl.clauses.match.AfterMatchGeneral;
import com.dsl.clauses.match.AfterMatchWhere;
import com.dsl.clauses.match.MatchClause;
import com.dsl.clauses.match.MatchPath;
import com.dsl.clauses.merge.AfterMerge;
import com.dsl.clauses.merge.MergeClause;
import com.dsl.clauses.merge.MergePath;
import com.dsl.clauses.order.AfterOrderBy;
import com.dsl.clauses.order.OrderByClause;
import com.dsl.clauses.returns.AfterReturns;
import com.dsl.clauses.returns.ReturnAlias;
import com.dsl.clauses.returns.ReturnClause;
import com.dsl.clauses.set.AfterSet;
import com.dsl.clauses.set.Set;
import com.dsl.clauses.set.SetClause;
import com.dsl.clauses.set.SetEquals;
import com.dsl.clauses.set.SetProp;
import com.dsl.clauses.skip.AfterSkip;
import com.dsl.clauses.skip.SkipClause;
import com.dsl.clauses.unwind.AfterUnwind;
import com.dsl.clauses.unwind.Unwind;
import com.dsl.clauses.unwind.UnwindAlias;
import com.dsl.clauses.unwind.UnwindClause;
import com.dsl.clauses.where.AfterWhere;
import com.dsl.clauses.where.Where;
import com.dsl.clauses.where.WhereClause;
import com.dsl.clauses.with.AfterWith;
import com.dsl.clauses.with.WithAlias;
import com.dsl.clauses.with.WithClause;
import com.dsl.clauses.with.WithSelect;
import com.dsl.expressions.Expression;
import com.dsl.expressions.bool.EqualityExpression;
import com.dsl.expressions.logical.LogicalExpression;
import com.dsl.expressions.logical.LogicalOperator;
import com.dsl.expressions.param.FinalExpression;
import com.dsl.expressions.param.Json;
import com.dsl.expressions.param.Property;
import com.dsl.expressions.param.Selector;
import com.dsl.expressions.param.SelectorExpression;
import com.dsl.expressions.param.Variable;
import com.dsl.expressions.path.PathExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClauseBuilder
    implements AsString, AfterWith, AfterReturns, AfterLimit, AfterSkip, AfterOrderBy,
    Unwind, AfterUnwind, AfterSet, SetEquals, Set, SetProp, WithSelect, Delete, AfterDelete {

    private final List<Clause> clauses = new ArrayList<>();
    private AfterCreateImpl afterCreateImpl;
    private AfterMatchImpl afterMatchImpl;
    private AfterMergeImpl afterMergeImpl;
    private UnwindAliasImpl unwindAlias;
    private WithAliasImpl withAlias;
    private ReturnAliasImpl returnAlias;

    public ClauseBuilder(final Clause clause) {
        init(clause);
    }

    public ClauseBuilder() {
    }

    public UnwindAliasImpl unwind(final UnwindClause clause) {
        init(clause);
        return unwindAlias;
    }

    public AfterMatchImpl match(final MatchClause clause) {
        init(clause);
        return afterMatchImpl;
    }

    public AfterMergeImpl merge(final MergeClause clause) {
        init(clause);
        return afterMergeImpl;
    }

    public AfterCreateImpl create(final CreateClause clause) {
        init(clause);
        return afterCreateImpl;
    }

    private void init(final Clause clause) {
        afterCreateImpl = new AfterCreateImpl().init(this);
        afterMatchImpl = new AfterMatchImpl().init(this);
        afterMergeImpl = new AfterMergeImpl().init(this);
        unwindAlias = new UnwindAliasImpl().init(this);
        withAlias = new WithAliasImpl().init(this);
        returnAlias = new ReturnAliasImpl().init(this);
        clauses.add(clause);
    }

    @Override
    public AfterMerge merge(PathExpression... e) {
        this.clauses.add(new MergeClause(e));
        return afterMergeImpl;
    }

    @Override
    public MergePath merge() {
        this.clauses.add(new MergeClause());
        return afterMergeImpl;
    }

    @Override
    public AfterCreate create(PathExpression... pathExpressions) {
        clauses.add(new CreateClause(pathExpressions));
        return afterCreateImpl;
    }

    @Override
    public CreatePath create() {
        clauses.add(new CreateClause());
        return afterCreateImpl;
    }

    @Override
    public WithAlias select(final FinalExpression finalExpression) {
        getLast(WithClause.class).addExpression(finalExpression);
        return withAlias;
    }

    @Override
    public WithAlias select(String expression) {
        getLast(WithClause.class).addExpression(new Selector(expression));
        return withAlias;
    }

    @Override
    public AfterMatchGeneral match(PathExpression... pathExpressions) {
        clauses.add(new MatchClause(pathExpressions));
        return afterMatchImpl;
    }

    @Override
    public AfterMatchGeneral optMatch(PathExpression... pathExpressions) {
        clauses.add(MatchClause.optMatch(pathExpressions));
        return afterMatchImpl;
    }

    @Override
    public MatchPath match() {
        clauses.add(new MatchClause());
        return afterMatchImpl;
    }

    @Override
    public MatchPath optMatch() {
        clauses.add(MatchClause.optMatch());
        return afterMatchImpl;
    }

    @Override
    public WithSelect with() {
        clauses.add(new WithClause());
        return this;
    }

    //@Override
    //public AfterWhere where(final LogicalExpression logicalExpression) {
    //    clauses.add(new WhereClause(logicalExpression));
    //    return this;
    //}
//
    //@Override
    //public AfterWhere where(final String expression) {
    //    clauses.add(new WhereClause(expression));
    //    return this;
    //}
//
    //@Override
    //public AfterWhere where(final PathExpression pathExpression) {
    //    clauses.add(new WhereClause(pathExpression));
    //    return this;
    //}

    //@Override
    //public AfterWhere and(String expression) {
    //    getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.AND);
    //    return this;
    //}
//
    //@Override
    //public AfterWhere and(LogicalExpression expression) {
    //    getLast(WhereClause.class).addExpression(expression, LogicalOperator.AND);
    //    return this;
    //}
//
    //@Override
    //public AfterWhere or(String expression) {
    //    getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.OR);
    //    return this;
    //}
//
    //@Override
    //public AfterWhere or(LogicalExpression expression) {
    //    getLast(WhereClause.class).addExpression(expression, LogicalOperator.OR);
    //    return this;
    //}
//
    //@Override
    //public AfterWhere xor(String expression) {
    //    getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.XOR);
    //    return this;
    //}
//
    //@Override
    //public AfterWhere xor(LogicalExpression expression) {
    //    getLast(WhereClause.class).addExpression(expression, LogicalOperator.XOR);
    //    return this;
    //}

    @Override
    public ReturnAlias returns(Expression... e) {
        clauses.add(new ReturnClause(e));
        return returnAlias;
    }

    @Override
    public ReturnAlias returns(String... e) {
        Selector[] ls = new Selector[e.length];
        for (int i = 0; i < e.length; i++) {
            ls[i] = new Selector(e[i]);
        }
        this.clauses.add(new ReturnClause(ls));
        return returnAlias;
    }

    @Override
    public ReturnAlias returns(Object... e) {
        Expression[] ls = new Expression[e.length];
        for (int i = 0; i < e.length; i++) {
            if (e[i] instanceof String) {
                ls[i] = new Selector((String) e[i]);
            } else if (e[i] instanceof Expression) {
                ls[i] = (Expression) e[i];
            } else {
                throw new UnsupportedOperationException();
            }
        }
        this.clauses.add(new ReturnClause(ls));
        return returnAlias;
    }

    @Override
    public AfterLimit limit(int numElements) {
        clauses.add(new LimitClause(numElements));
        return this;
    }

    @Override
    public AfterLimit limit(Variable variable) {
        clauses.add(new LimitClause(variable));
        return this;
    }

    @Override
    public AfterLimit limit(Expression expression) {
        clauses.add(new LimitClause(expression));
        return this;
    }

    @Override
    public AfterOrderBy orderBy(String... properties) {
        clauses.add(new OrderByClause(properties));
        return this;
    }

    @Override
    public AfterOrderBy orderBy(Property... properties) {
        clauses.add(new OrderByClause(properties));
        return this;
    }

    @Override
    public AfterSkip skip(int numElements) {
        clauses.add(new SkipClause(numElements));
        return this;
    }

    @Override
    public AfterSkip skip(Variable variable) {
        clauses.add(new SkipClause(variable));
        return this;
    }

    @Override
    public AfterSkip skip(Expression expression) {
        clauses.add(new SkipClause(expression));
        return this;
    }

    @Override
    public UnwindAlias unwind(Collection<?> a) {
        clauses.add(UnwindClause.collectionUnwind(a));
        return unwindAlias;
    }

    @Override
    public UnwindAlias unwind(Variable var) {
        clauses.add(UnwindClause.varUnwind(var));
        return unwindAlias;
    }

    @Override
    public AfterSet setEq(Object o) {
        getLast(SetClause.class).setValue(o);
        return this;
    }

    @Override
    public AfterSet setEq(Expression e) {
        getLast(SetClause.class).setValue(e.asString());
        return this;
    }

    @Override
    public AfterSet mut(Object... o) {
        getLast(SetClause.class).setValue(new Json(o).asString()).setOperator("+=");
        return this;
    }

    @Override
    public SetEquals prop(String o) {
        getLast(SetClause.class).addClause(new Selector(o));
        return this;
    }

    @Override
    public SetEquals prop(Selector o) {
        getLast(SetClause.class).addClause(o);
        return this;
    }

    @Override
    public SetEquals prop(SelectorExpression e) {
        getLast(SetClause.class).addClause(e);
        return this;
    }

    @Override
    public AfterDelete delete(Selector selector) {
        this.clauses.add(new DeleteClause(selector));
        return this;
    }

    @Override
    public AfterDelete delete(String s) {
        this.clauses.add(new DeleteClause(s));
        return this;
    }

    @Override
    public AsString detach() {
        getLast(DeleteClause.class).setDetach();
        return this;
    }

    @Override
    public AfterSet remove(Selector o) {
        getLast(SetClause.class).addClause(o).setValue("NULL");
        return this;
    }

    @Override
    public AfterSet remove(String o) {
        getLast(SetClause.class).addClause(new Selector(o)).setValue("NULL");
        return this;
    }

    @Override
    public SetProp set() {
        this.clauses.add(new SetClause());
        return this;
    }

    private <T> T getLast(final Class<T> clazz) {
        Clause exp = clauses.get(clauses.size() - 1);
        return (T) exp;
    }

    @Override
    public String asString() {
        return String.join(" ", StringUtils.asString(clauses));
    }

    private static class ReturnAliasImpl extends AliasExpressionResolver<AfterReturns, ReturnClause>
        implements ClauseImpl, ReturnAlias {

        private ReturnAliasImpl init(ClauseBuilder clauseBuilder) {
            start(this, ReturnClause.class, clauseBuilder);
            return this;
        }
    }

    private static class WithAliasImpl extends AliasExpressionResolver<AfterWith, WithClause> implements ClauseImpl, WithAlias {

        private WithAliasImpl init(ClauseBuilder clauseBuilder) {
            start(this, WithClause.class, clauseBuilder);
            return this;
        }
    }

    public static class UnwindAliasImpl extends AliasExpressionResolver<AfterUnwind, UnwindClause>
        implements ClauseImpl, UnwindAlias, AfterUnwind {

        private UnwindAliasImpl init(ClauseBuilder clauseBuilder) {
            start(this, UnwindClause.class, clauseBuilder);
            return this;
        }
    }

    private static class AfterCreateImpl
        extends PathExpressionAppender<AfterCreate, CreateClause> implements ClauseImpl, AfterCreate, CreatePath {

        private AfterCreateImpl init(ClauseBuilder clauseBuilder) {
            start(this, CreateClause.class, clauseBuilder);
            return this;
        }

        @Override
        public AfterCreate path(PathExpression pathExpression) {
            getClauseBuilder().getLast(CreateClause.class).addExpression(pathExpression);
            return this;
        }

        @Override
        public AfterCreate path(String pathExpression) {
            getClauseBuilder().getLast(CreateClause.class).addExpression(pathExpression);
            return this;
        }
    }

    private static class AfterMatchImpl
        extends PathExpressionAppender<AfterMatchGeneral, MatchClause>
        implements AfterMatchGeneral, ClauseImpl, MatchPath, AfterMatchWhere, FilterableClause<AfterMatchWhere> {

        @Override
        public AfterMatchWhere getClause() {
            return this;
        }

        private AfterMatchImpl init(ClauseBuilder clauseBuilder) {
            start(this, MatchClause.class, clauseBuilder);
            return this;
        }
    }

    private interface FilterableClause<T> extends AfterWhere<T>, Where<T> {
        ClauseBuilder getClauseBuilder();
        T getClause();

        @Override
        default T where(LogicalExpression logicalExpression) {
            getClauseBuilder().clauses.add(new WhereClause(logicalExpression));
            return getClause();
        }

        @Override
        default T where(String expression) {
            getClauseBuilder().clauses.add(new WhereClause(expression));
            return getClause();
        }

        @Override
        default T where(PathExpression pathExpression) {
            getClauseBuilder().clauses.add(new WhereClause(pathExpression));
            return getClause();
        }

        default T xor(LogicalExpression expression) {
            getClauseBuilder().getLast(WhereClause.class).addExpression(expression, LogicalOperator.XOR);
            return getClause();
        }

        default T and(String expression) {
            getClauseBuilder().getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.AND);
            return getClause();
        }

        default T and(LogicalExpression expression) {
            getClauseBuilder().getLast(WhereClause.class).addExpression(expression, LogicalOperator.AND);
            return getClause();
        }

        default T or(String expression) {
            getClauseBuilder().getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.OR);
            return getClause();
        }

        default T or(LogicalExpression expression) {
            getClauseBuilder().getLast(WhereClause.class).addExpression(expression, LogicalOperator.OR);
            return getClause();
        }

        default T xor(String expression) {
            getClauseBuilder().getLast(WhereClause.class).addExpression(new Selector(expression), LogicalOperator.XOR);
            return getClause();
        }
    }

    private static class AfterMergeImpl
        extends PathExpressionAppender<AfterMerge, MergeClause> implements AfterMerge, ClauseImpl, MergePath {

        private AfterMergeImpl init(ClauseBuilder clauseBuilder) {
            start(this, MergeClause.class, clauseBuilder);
            return this;
        }

        public AfterMerge onMatch(EqualityExpression... equalityExpressions) {
            getClauseBuilder().getLast(MergeClause.class).addOnMatch(equalityExpressions);
            return this;
        }

        public AfterMerge onCreate(EqualityExpression... equalityExpressions) {
            getClauseBuilder().getLast(MergeClause.class).addOnCreate(equalityExpressions);
            return this;
        }

        @Override
        public AfterMerge onMatch(String equalityExpressions) {
            getClauseBuilder().getLast(MergeClause.class).addOnMatch(equalityExpressions);
            return this;
        }

        @Override
        public AfterMerge onCreate(String equalityExpressions) {
            getClauseBuilder().getLast(MergeClause.class).addOnCreate(equalityExpressions);
            return this;
        }
    }

    private abstract static class AliasExpressionResolver<T, U extends Alias> {
        protected ClauseBuilder clauseBuilder;
        private Class<U> uClass;
        private T t;

        public void start(T t, Class<U> uClass, ClauseBuilder clauseBuilder) {
            this.t = t;
            this.uClass = uClass;
            this.clauseBuilder = clauseBuilder;
        }

        public ClauseBuilder clauseBuilder() {
            return clauseBuilder;
        }

        public T as(String as) {
            clauseBuilder.getLast(uClass).setAs(as);
            return t;
        }

        public String asString() {
            return clauseBuilder.asString();
        }
    }

    private abstract static class PathExpressionAppender<T, U extends PathExpressionClause> {

        private ClauseBuilder clauseBuilder;
        private Class<U> uClass;
        private T t;

        public ClauseBuilder getClauseBuilder() {
            return clauseBuilder;
        }

        public void start(T t, Class<U> uClass, ClauseBuilder clauseBuilder) {
            this.clauseBuilder = clauseBuilder;
            this.t = t;
            this.uClass = uClass;
        }

        public ClauseBuilder clauseBuilder() {
            return clauseBuilder;
        }

        public T path(PathExpression pathExpression) {
            clauseBuilder.getLast(uClass).addExpression(pathExpression);
            return t;
        }

        public T path(String pathExpression) {
            clauseBuilder.getLast(uClass).addExpression(pathExpression);
            return t;
        }

        public String asString() {
            return clauseBuilder.asString();
        }
    }
}
