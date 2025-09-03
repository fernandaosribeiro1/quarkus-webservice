package org.senac;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.Set;

@Path("/Cars")
public class CarroResource {

    @GET
    public Response getAll() {
        return Response.ok(Carro.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id) {
        Carro entity = Carro.findById(id);
        if (entity == null)
            return Response.status(404).build();
        return Response.ok(entity).build();
    }

    @GET
    @Path("/search")
    public Response search(@QueryParam("q") String q,
                           @QueryParam("sort") @DefaultValue("id") String sort,
                           @QueryParam("direction") @DefaultValue("asc") String direction,
                           @QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size) {

        Set<String> allowed = Set.of("id", "marca", "modelo", "anoFabricacao", "tipoCombustivel", "preco");

        if (!allowed.contains(sort))
            sort = "id";

        Sort sortObj = Sort.by(
                sort,
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.Descending : Sort.Direction.Ascending
        );

        int effectivePage = page <= 1 ? 0 : page - 1;

        var query = (q == null || q.isBlank())
                ? Carro.findAll(sortObj)
                : Carro.find("lower(marca) like ?1 or lower(modelo) like ?1",
                sortObj,
                "%" + q.toLowerCase() + "%");

        var carros = query.page(effectivePage, size).list();
        return Response.ok(carros).build();
    }

    @POST
    @Transactional
    public Response insert(Carro carro) {
        Carro.persist(carro);
        return Response.status(201).entity(carro).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        Carro.deleteById(id);
        return Response.noContent().build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") long id, Carro newCarro) {
        Carro entity = Carro.findById(id);
        if (entity == null)
            return Response.status(404).build();

        entity.marca = newCarro.marca;
        entity.modelo = newCarro.modelo;
        entity.anoFabricacao = newCarro.anoFabricacao;
        entity.tipoCombustivel = newCarro.tipoCombustivel;
        entity.preco = newCarro.preco;

        return Response.status(200).entity(entity).build();
    }
}
