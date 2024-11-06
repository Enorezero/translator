package org.example;

import java.util.HashMap;
import java.util.Map;

public class JavaToKotlinMapper {

    private static final Map<String, String> STANDARD_LIBRARY_MAPPING = new HashMap<>() {{
        put("System.out.println", "println");
        put("List", "MutableList");
        put("ArrayList", "ArrayList");
        put("HashMap", "HashMap");
        put("HashSet", "HashSet");
        put("String", "String");
        put("Integer", "Int");
        put("Double", "Double");
        put("Boolean", "Boolean");
        put("Math.max", "kotlin.math.max");
        put("Math.min", "kotlin.math.min");
        put("Collections.sort", "sorted");
        put("Optional", "Optional");
        put("Stream", "asSequence");
    }};

    private static final Map<String, String> METHOD_MAPPING = new HashMap<>() {{
        put("add", "add");
        put("remove", "remove");
        put("get", "get");
        put("size", "size");
        put("clear", "clear");
        put("contains", "contains");
        put("indexOf", "indexOf");
        put("toString", "toString");
        put("stream", "asSequence");
        put("map", "map");
        put("filter", "filter");
    }};

    private static final Map<String, String> ANNOTATION_MAPPING = new HashMap<>() {{
        put("@Override", "@Override");
        put("@Deprecated", "@Deprecated");
    }};

    private static final Map<String, String> CONSTRUCTIONS_MAPPING = new HashMap<>() {{
        put("try-with-resources", "use");
    }};

    public static String mapStandardLibrary(String javaFunction) {
        return STANDARD_LIBRARY_MAPPING.getOrDefault(javaFunction, javaFunction);
    }

    public static String mapMethod(String javaMethod) {
        return METHOD_MAPPING.getOrDefault(javaMethod, javaMethod);
    }

    public static String mapAnnotation(String javaAnnotation) {
        return ANNOTATION_MAPPING.getOrDefault(javaAnnotation, javaAnnotation);
    }

    public static String mapConstruction(String javaContextManager) {
        return CONSTRUCTIONS_MAPPING.getOrDefault(javaContextManager, javaContextManager);
    }
}