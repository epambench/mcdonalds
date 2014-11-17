package com.bench.mac.config.guice.logger;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * usage:
 * <p>
 *     $ @InjectLogger private Logger logger;
 * </p>
 * it will inject slfj Logger with proper naming.
 * It is working on any Guice injected class.
 * Otherwise please use the standard boilerplate solution:
 * <p>
 *     private Logger logger = LoggerFactory.getLogger(<T>.class);
 * </p>
 */
@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface InjectLogger {
}
