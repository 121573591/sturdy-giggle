package okio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Deprecated(message = "This annotation is obsolete and should be removed.", level = DeprecationLevel.HIDDEN)
@Retention(AnnotationRetention.SOURCE)
@Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\f\n\002\030\002\n\002\020\033\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003¨\006\004"}, d2 = {"Lokio/ExperimentalFileSystem;", "", "<init>", "()V", "okio"})
public @interface ExperimentalFileSystem {}
