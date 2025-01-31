package tech.jhipster.lite.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.lite.module.application.JHipsterModulesApplicationService;
import tech.jhipster.lite.projectfolder.domain.ProjectFolder;

@RestController
@Tag(name = "Modules")
@RequestMapping("/api")
class ModulesResource {

  private final JHipsterModulesApplicationService modules;
  private final ProjectFolder projectFolder;

  private final RestJHipsterModules modulesList;
  private final RestJHipsterLandscape modulesLandscape;

  public ModulesResource(JHipsterModulesApplicationService modules, ProjectFolder projectFolder) {
    this.modules = modules;
    this.projectFolder = projectFolder;

    modulesList = RestJHipsterModules.from(modules.resources());
    modulesLandscape = RestJHipsterLandscape.from(modules.landscape());
  }

  @GetMapping("/modules")
  @Operation(summary = "List available modules")
  public ResponseEntity<RestJHipsterModules> listModules() {
    return ResponseEntity.ok(modulesList);
  }

  @GetMapping("modules-landscape")
  @Operation(summary = "Get a view of the current modules landscape")
  public ResponseEntity<RestJHipsterLandscape> modulesLandscape() {
    return ResponseEntity.ok(modulesLandscape);
  }

  @PostMapping("apply-patches")
  @Operation(summary = "Apply multiple modules patches")
  public void applyPatches(@RequestBody @Validated RestJHipsterModulesToApply modulesToApply) {
    modules.apply(modulesToApply.toDomain(projectFolder));
  }
}
