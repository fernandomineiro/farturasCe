package moduloFaturamento.util.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Fabio Bentes - 27/08/2019
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateTimeFormatCesan {

   String formatString();
   String datatype() default "DD/MM/YYYY";
}
