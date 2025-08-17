package dev.abdelrahman.movies.Controllers.Crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import dev.abdelrahman.movies.Controllers.Exceptions.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

public interface ICrudController<Entity, RetrieveDTO, CreateDTO, UpdateDTO> {
    public ResponseEntity<ApiResponse<?>> getAllValid();
    public ResponseEntity<ApiResponse<?>> getAll();
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable String id);
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody CreateDTO createDTO);
    public ResponseEntity<ApiResponse<?>> update(@PathVariable String id, @Valid @RequestBody UpdateDTO updateDTO);
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String id);
    public ResponseEntity<ApiResponse<?>> smootheDelete(@PathVariable String id);
    public ResponseEntity<ApiResponse<?>> activeMovie(@PathVariable String id);

}
