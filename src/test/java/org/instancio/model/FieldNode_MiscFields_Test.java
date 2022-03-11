package org.instancio.model;

import org.instancio.pojo.generics.MiscFields;
import org.instancio.testsupport.tags.ModelTag;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import static org.instancio.testsupport.utils.TypeUtils.getTypeVar;

@ModelTag
class FieldNode_MiscFields_Test {

    private NodeContext nodeContext;

    @BeforeEach
    void setUp() {
        Map<TypeVariable<?>, Class<?>> typeMap = new HashMap<>();
        typeMap.put(getTypeVar(MiscFields.class, "A"), Long.class);
        typeMap.put(getTypeVar(MiscFields.class, "B"), String.class);
        typeMap.put(getTypeVar(MiscFields.class, "C"), Integer.class);

        nodeContext = new NodeContext(typeMap);
    }
//
//    @Test
//    void listOfFoo_ListOfBar_ListOfBaz_ListOfString() {
//        final FieldNode rootNode = new FieldNode(nodeContext, ReflectionUtils.getField(
//                MiscFields.class, "listOfFoo_ListOfBar_ListOfBaz_ListOfString"));
//
//        // List<Foo<List<Bar<List<Baz<List<String>>>>>>>
//
//        assertFieldNode(rootNode)
//                .hasActualFieldType(List.class)
//                .hasTypeMappedTo(getTypeVar(List.class, "E"), Foo.class)
//                .hasTypeMapWithSize(1)
//                .hasChildrenOfSize(1);
//
//        final ClassNode fooClassNode = (ClassNode) rootNode.getChildren().get(0);
//        assertClassNode(fooClassNode)
//                .hasParent(rootNode)
//                .hasKlass(Foo.class)
//                .hasGenericTypeName("org.instancio.pojo.generics.foobarbaz.itemcontainer." +
//                        "Foo<java.util." +
//                        "List<org.instancio.pojo.generics.foobarbaz.itemcontainer." +
//                        "Bar<java.util." +
//                        "List<org.instancio.pojo.generics.foobarbaz.itemcontainer." +
//                        "Baz<java.util.List<java.lang." +
//                        "String>>>>>>")
//                .hasChildrenOfSize(2);
//
//        final FieldNode fooValueFieldNode = (FieldNode) NodeUtil.getChildNode(fooClassNode, "fooValue");
//
//        assertFieldNode(fooValueFieldNode)
//                //.hasActualFieldType(Foo.class)
//                .hasParent(fooClassNode)
//                .hasTypeMappedTo(getTypeVar(Foo.class, "X"), List.class)
//                .hasTypeMapWithSize(1)
//                .hasChildrenOfSize(2);
//
//        fail("verify the rest");
//    }
//
//    @Test
//    void pairAPairIntegerString() {
//        final FieldNode node = new FieldNode(nodeContext, ReflectionUtils.getField(MiscFields.class, "pairAPairIntegerString"));
//
//        assertFieldNode(node)
//                .hasActualFieldType(Pair.class)
//                .hasTypeMappedTo(getTypeVar(Pair.class, "L"), getTypeVar(MiscFields.class, "A"))
//                .hasTypeMappedTo(getTypeVar(MiscFields.class, "A"), Long.class)
//                .hasTypeMappedTo(getTypeVar(Pair.class, "R"), Pair.class)
//                .hasTypeMapWithSize(3)
//                .hasChildrenOfSize(2);
//
//        assertFieldNode(node.getChildByTypeParameter("L"))
//                .hasActualFieldType(Long.class)
//                .hasEmptyTypeMap()
//                .hasNoChildren();
//
//        assertFieldNode(node.getChildByTypeParameter("R"))
//                .hasActualFieldType(Pair.class)
//                .hasTypeMappedTo(getTypeVar(Pair.class, "L"), Integer.class)
//                .hasTypeMappedTo(getTypeVar(Pair.class, "R"), String.class)
//                .hasChildrenOfSize(2);
//    }
//
//    @Test
//    void test_MiscFields_tripletA_FooBarBazString_ListOfC() {
//        final String rootField = "tripletA_FooBarBazString_ListOfC";
//        final FieldNode node = new FieldNode(nodeContext, ReflectionUtils.getField(MiscFields.class, rootField));
//
//        assertFieldNode(node)
//                .hasFieldName(rootField)
//                .hasActualFieldType(Triplet.class)
//                .hasTypeMappedTo(getTypeVar(Triplet.class, "M"), getTypeVar(MiscFields.class, "A"))
//                .hasTypeMappedTo(getTypeVar(Triplet.class, "N"), Foo.class)
//                .hasTypeMappedTo(getTypeVar(Triplet.class, "O"), List.class)
//                .hasTypeMappedTo(getTypeVar(MiscFields.class, "A"), Long.class)
//                .hasTypeMapWithSize(4)
//                .hasChildrenOfSize(3);
//
//        final FieldNode leftField = node.getChildByTypeParameter("M");
//        assertFieldNode(leftField)
//                .hasFieldName("left")
//                .hasActualFieldType(Long.class)
//                .hasEmptyTypeMap()
//                .hasNoChildren();
//
//        final FieldNode midField = node.getChildByTypeParameter("N");
//        assertFieldNode(midField)
//                .hasFieldName("mid")
//                .hasActualFieldType(Foo.class)
//                .hasChildrenOfSize(2)
//                .hasTypeMappedTo(getTypeVar(Foo.class, "X"), Bar.class)
//                .hasTypeMapWithSize(1);
//
//        final FieldNode rightField = node.getChildByTypeParameter("O");
//        assertFieldNode(rightField)
//                .hasFieldName("right")
//                .hasActualFieldType(List.class)
//                .hasNoChildren()
//                .hasTypeMappedTo(getTypeVar(List.class, "E"), getTypeVar(MiscFields.class, "C"))
//                .hasTypeMappedTo(getTypeVar(MiscFields.class, "C"), Integer.class)
//                .hasTypeMapWithSize(2);
//
//        final FieldNode fooValueFieldNode = midField.getChildByFieldName("fooValue");
//        assertFieldNode(fooValueFieldNode)
//                .hasFieldName("fooValue")
//                .hasActualFieldType(Bar.class)
//                .hasChildrenOfSize(2)
//                .hasTypeMappedTo(getTypeVar(Bar.class, "Y"), Baz.class)
//                .hasTypeMapWithSize(1);
//
//        final FieldNode otherFooValueFieldNode = midField.getChildByFieldName("otherFooValue");
//        assertFieldNode(otherFooValueFieldNode)
//                .hasFieldName("otherFooValue")
//                .hasActualFieldType(Object.class)
//                .hasEmptyTypeMap()
//                .hasNoChildren();
//
//        final FieldNode barValueFieldNode = fooValueFieldNode.getChildByFieldName("barValue");
//        assertFieldNode(barValueFieldNode)
//                .hasFieldName("barValue")
//                .hasActualFieldType(Baz.class)
//                .hasChildrenOfSize(1)
//                .hasTypeMappedTo(getTypeVar(Baz.class, "Z"), String.class)
//                .hasTypeMapWithSize(1);
//
//        final FieldNode bazValueFieldNode = barValueFieldNode.getChildByFieldName("bazValue");
//        assertFieldNode(bazValueFieldNode)
//                .hasFieldName("bazValue")
//                .hasActualFieldType(String.class)
//                .hasNoChildren()
//                .hasEmptyTypeMap();
//    }
}