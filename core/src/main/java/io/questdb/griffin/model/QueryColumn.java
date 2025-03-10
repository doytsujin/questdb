/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2022 QuestDB
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
 *
 ******************************************************************************/

package io.questdb.griffin.model;

import io.questdb.std.Mutable;
import io.questdb.std.ObjectFactory;

import java.util.Objects;

public class QueryColumn implements Mutable {
    public final static ObjectFactory<QueryColumn> FACTORY = QueryColumn::new;
    private CharSequence alias;
    private ExpressionNode ast;
    private boolean includeIntoWildcard = true;

    protected QueryColumn() {
    }

    @Override
    public void clear() {
        alias = null;
        ast = null;
        includeIntoWildcard = true;
    }

    public CharSequence getAlias() {
        return alias;
    }

    public void setAlias(CharSequence alias) {
        this.alias = alias;
    }

    public ExpressionNode getAst() {
        return ast;
    }

    public CharSequence getName() {
        return alias != null ? alias : ast.token;
    }

    public boolean isIncludeIntoWildcard() {
        return includeIntoWildcard;
    }

    public QueryColumn of(CharSequence alias, ExpressionNode ast) {
        return of(alias, ast, true);
    }

    public QueryColumn of(CharSequence alias, ExpressionNode ast, boolean includeIntoWildcard) {
        this.alias = alias;
        this.ast = ast;
        this.includeIntoWildcard = includeIntoWildcard;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryColumn that = (QueryColumn) o;
        return includeIntoWildcard == that.includeIntoWildcard && Objects.equals(alias, that.alias) && Objects.equals(ast, that.ast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, ast, includeIntoWildcard);
    }
}
