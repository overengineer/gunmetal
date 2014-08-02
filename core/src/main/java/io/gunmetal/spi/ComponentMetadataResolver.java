package io.gunmetal.spi;

import java.lang.reflect.Method;

/**
 * @author rees.byars
 */
public interface ComponentMetadataResolver {

    ComponentMetadata<Method> resolveMetadata(Method method, ModuleMetadata moduleMetadata, Errors errors);

    ComponentMetadata<Class<?>> resolveMetadata(Class<?> cls, ModuleMetadata moduleMetadata, Errors errors);

}
