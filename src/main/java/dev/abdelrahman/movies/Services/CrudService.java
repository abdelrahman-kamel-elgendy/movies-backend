package dev.abdelrahman.movies.Services;

import java.util.List;

import org.bson.types.ObjectId;

public interface CrudService<Entity, RetrieveDTO, CreateDTO, UpdateDTO> {
    public List<Entity> all();
    public List<Entity> allValid();
    public Entity active(ObjectId id);
    public Entity findById(ObjectId id);
    public Entity delete(ObjectId id);
    public Entity smootheDelete(ObjectId id);
    public RetrieveDTO create(CreateDTO createDTO);
    public RetrieveDTO update(UpdateDTO updateDTO, ObjectId id);
}