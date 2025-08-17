package dev.abdelrahman.movies.Services;

import java.util.List;


public interface CrudService<Entity, RetrieveDTO, CreateDTO, UpdateDTO> {
    public List<Entity> all();
    public List<Entity> allValid();
    public Entity active(String id);
    public Entity findById(String id);
    public Entity delete(String id);
    public Entity smootheDelete(String id);
    public RetrieveDTO create(CreateDTO createDTO);
    public RetrieveDTO update(UpdateDTO updateDTO, String id);
}