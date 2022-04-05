/*
 *  Copyright 2022 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.instancio.internal.nodes;

import org.instancio.util.Verify;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ArrayNode extends Node {

    private final Node elementNode;

    public ArrayNode(final NodeContext nodeContext,
                     final Class<?> klass,
                     final Node elementNode,
                     @Nullable final Field field,
                     @Nullable final Type genericType,
                     @Nullable final Node parent) {

        super(nodeContext, klass, field, genericType, parent);

        Verify.isTrue(klass.isArray() || genericType instanceof GenericArrayType,
                "Either raw type(%s) or generic type (%s) must be an array", klass.getName(), genericType);

        elementNode.setParent(this);

        this.elementNode = elementNode;
    }

    @Override
    public void accept(final NodeVisitor visitor) {
        visitor.visitArrayNode(this);
    }

    /**
     * Returns an empty list; children come from the {@link #getElementNode()}.
     */
    @Override
    protected List<Node> collectChildren() {
        return Collections.emptyList();
    }

    public Node getElementNode() {
        return elementNode;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        ArrayNode other = (ArrayNode) o;
        return Objects.equals(this.getTargetClass(), other.getTargetClass())
                && Objects.equals(this.getElementNode(), other.getElementNode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTargetClass(), getElementNode());
    }
}