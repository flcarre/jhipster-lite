package tech.jhipster.lite.generator.init.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.init.domain.InitModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.npm.domain.NpmVersions;

@Service
public class InitApplicationService {

  private final InitModuleFactory factory;

  public InitApplicationService(NpmVersions npmVersions) {
    factory = new InitModuleFactory(npmVersions);
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties);
  }
}
