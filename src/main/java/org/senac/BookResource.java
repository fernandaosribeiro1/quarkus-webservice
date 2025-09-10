package org.senac;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.Set;

@Path("/Books")
public class BookResource {

    @GET
    @Operation(
            summary = "retorna todos os livros",
            description = "Retorna uma lista de livros por padrão Json"
    )
    @APIResponse(
            responseCode = "200",
            description = "Retornar a lista Corretamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArrayList.class)
            )
    )


    public Response getAll(){
        return Response.ok(Book.listAll()).build();
    }
    @GET
    @Path("{id}")
    public Response getById(
            @Parameter(description = "Id do livro a ser pesquisado", required = true)
            @PathParam("id") int id) {
        Book entity = Book.findById(id);
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

        Set<String> allowed = Set.of("id", "titulo", "autor", "editora", "anoLancamento", "estaDisponivel");

        if (!allowed.contains(sort))
            sort = "id";

        Sort sortObj = Sort.by(
                sort,
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.Descending : Sort.Direction.Ascending
        );

        int effectivePage = page <= 1 ? 0 : page - 1;

        var query = (q == null || q.isBlank())
                ? Book.findAll(sortObj)
                : Book.find("lower(titulo) like ?1 or lower(autor) like ?1",
                sortObj,
                "%" + q.toLowerCase() + "%");

        var books = query.page(effectivePage, size).list();
        return Response.ok(books).build();
    }


    @POST
@RequestBody(
        required = true,
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Book.class)
        )
        @APIResponse(
            responseCode = "201",
            description = "Created",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Book.class)
)
    @APIResponse(
        responseCode = "(400)",
        description = "",
        content = 
        schema = 
    )


    @Transactional


    public Response insert(Book book) {
        Book.persist(book);
        return Response.status(201).entity(book).build();
    }



    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        Book.deleteById(id);
        return Response.noContent().build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") int id, Book newBook) {
        Book entity = Book.findById(id);
        if (entity == null)
            return Response.status(404).build();

        entity.titulo = newBook.titulo;
        entity.autor = newBook.autor;
        entity.editora = newBook.editora;
        entity.anoLancamento = newBook.anoLancamento;
        entity.estaDisponivel = newBook.estaDisponivel;

        return Response.status(200).entity(entity).build();
    }
}

