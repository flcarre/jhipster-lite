package tech.jhipster.lite.module.domain.properties;

import java.util.Map;
import tech.jhipster.lite.common.domain.JHipsterCollections;
import tech.jhipster.lite.error.domain.Assert;

record JHipsterModuleParameters(Map<String, Object> parameters) {
  public JHipsterModuleParameters(Map<String, Object> parameters) {
    this.parameters = JHipsterCollections.immutable(parameters);
  }

  <T> T getOrDefault(String key, T defaultValue, Class<T> clazz) {
    Assert.notBlank("key", key);

    if (!parameters.containsKey(key)) {
      return defaultValue;
    }

    return get(key, clazz);
  }

  <T> T get(String key, Class<T> clazz) {
    Assert.notBlank("key", key);

    Object property = parameters.get(key);

    if (property == null) {
      throw new UnknownPropertyException(key);
    }

    if (clazz.isInstance(property)) {
      return clazz.cast(property);
    }

    throw InvalidPropertyTypeException.builder().key(key).expectedType(clazz).actualType(property.getClass());
  }

  public Map<String, Object> get() {
    return parameters();
  }
}
