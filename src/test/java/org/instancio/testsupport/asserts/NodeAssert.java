package org.instancio.testsupport.asserts;

import org.assertj.core.api.AbstractAssert;
import org.instancio.model.Node;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("UnusedReturnValue")
public class NodeAssert extends AbstractAssert<NodeAssert, Node> {

    private NodeAssert(Node actual) {
        super(actual, NodeAssert.class);
    }

    public static NodeAssert assertNode(Node actual) {
        return new NodeAssert(actual);
    }

    public NodeAssert hasKlass(Class<?> expected) {
        isNotNull();
        assertThat(actual.getKlass()).isEqualTo(expected);
        return this;
    }

    public NodeAssert hasEffectiveClass(Class<?> expected) {
        isNotNull();
        assertThat(actual.getEffectiveType().getRawType()).isEqualTo(expected);
        return this;
    }

    public NodeAssert hasEffectiveType(Type expected) {
        isNotNull();
        assertThat(actual.getEffectiveType().getGenericType()).isEqualTo(expected);
        return this;
    }

    public NodeAssert hasGenericTypeName(String expected) {
        isNotNull();
        assertThat(actual.getGenericType()).isNotNull();
        assertThat(actual.getGenericType().getTypeName()).isEqualTo(expected);
        return this;
    }

    public NodeAssert hasFieldName(String expected) {
        isNotNull();
        assertThat(actual.getField()).isNotNull();
        assertThat(actual.getField().getName()).isEqualTo(expected);
        return this;

    }

    public NodeAssert hasTypeMapWithSize(int expected) {
        isNotNull();
        assertThat(actual.getTypeMap())
                .as("Actual type map: %s", actual.getTypeMap())
                .hasSize(expected);
        return this;
    }

    public NodeAssert hasTypeMappedTo(TypeVariable<?> typeVariable, Type typeMapping) {
        isNotNull();
        final Map<TypeVariable<?>, Type> typeMap = actual.getTypeMap();
        assertThat(typeMap.get(typeVariable))
                .as("Actual type map: %s", typeMap)
                .isEqualTo(typeMapping);
        return this;
    }

    public NodeAssert hasEmptyTypeMap() {
        isNotNull();
        assertThat(actual.getTypeMap())
                .as("Actual type map: %s", actual.getTypeMap())
                .isEmpty();
        return this;
    }

    public NodeAssert hasParent(Node expected) {
        isNotNull();
        assertThat(actual.getParent()).isSameAs(expected);
        return this;
    }

    public NodeAssert hasChildrenOfSize(int expected) {
        isNotNull();
        assertThat(actual.getChildren()).hasSize(expected);
        return this;
    }

    public NodeAssert hasNoChildren() {
        isNotNull();
        assertThat(actual.getChildren()).isEmpty();
        return this;
    }
}