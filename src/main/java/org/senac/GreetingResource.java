package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyEntity hello() {
        return new MyEntity("Hello from Quarkus REST");
    }

    @GET
    @Path("/GetAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MyEntity> getAll() {
        return MyEntity.listAll();
    }


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertEntity(MyEntity entity) {
        MyEntity.persist(entity);
    }

    @DELETE
    @Path ("{id}")
    @Transactional
    @Consumes
    public void delete (@PathParam("id")int id){
        MyEntity.deleteById(id);
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void update(@PathParam("id") Long id, MyEntity entity){
        entity.id = id;
        entity.persist();
    }
}

