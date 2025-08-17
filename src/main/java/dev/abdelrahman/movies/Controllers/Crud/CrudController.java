package dev.abdelrahman.movies.Controllers.Crud;

import dev.abdelrahman.movies.Controllers.Exceptions.ApiResponse;
import dev.abdelrahman.movies.Services.CrudService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<Service extends CrudService<Entity, RetrieveDTO, CreateDTO, UpdateDTO>, Entity, RetrieveDTO, CreateDTO, UpdateDTO>  
implements ICrudController<Entity, RetrieveDTO, CreateDTO, UpdateDTO> {

    @Autowired
    protected Service service;

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAllValid() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "All valid records retrieved successfully", service.allValid())
        );
    }
    
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAll() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "All records retrieved successfully", service.all())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable String id) {
        return  ResponseEntity.ok(new ApiResponse<>(true, "Record found", service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody CreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true, "Record created successfully", service.create(createDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable String id, @Valid @RequestBody UpdateDTO updateDTO) {
        return ResponseEntity.ok((new ApiResponse<>(true, "Record updated", service.update(updateDTO, id))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String id) {
        return ResponseEntity.ok((new ApiResponse<>(true, "Record updated", service.delete(id))));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> smootheDelete(@PathVariable String id) {
        return ResponseEntity.ok((new ApiResponse<>(true, "Record deeleted", service.smootheDelete(id))));
    } 

    @PutMapping("/active/{id}")
    public ResponseEntity<ApiResponse<?>> activeMovie(@PathVariable String id) {
        return ResponseEntity.ok((new ApiResponse<>(true, "Record activated", service.active(id))));
    } 

}
