package net.andy.android.ats.annotation;

/**
 * @author zhouyelin
 * 
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Test {
	String paramFile() default "";
	
}
