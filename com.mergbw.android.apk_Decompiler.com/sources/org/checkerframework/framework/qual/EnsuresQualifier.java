package org.checkerframework.framework.qual;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@InheritedAnnotation
@Repeatable(List.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnsuresQualifier {

    @Documented
    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    @InheritedAnnotation
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        EnsuresQualifier[] value();
    }

    String[] expression();

    Class<? extends Annotation> qualifier();
}
