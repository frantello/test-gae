package com.test.rest;

import com.test.dao.BookBeanDAO;
import com.test.data.BookBean;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/book")
@Produces("application/json;charset=utf-8")
@Api(value = "book", description = "Book service")
public class BookResource {

    private BookBeanDAO bookBeanDAO;

    public BookResource() {
        this.bookBeanDAO = new BookBeanDAO();
    }

    @GET
    @ApiOperation("list book objects")
    public Response list() {
        return Response.ok(this.bookBeanDAO.list()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation("get book object")
    public Response get(@ApiParam("book ID") @PathParam("id") Long id) {
        BookBean bean = this.bookBeanDAO.get(id);
        if (bean == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(bean).build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @ApiOperation("save book object")
    public Response save(@ApiParam(value = "book object", required = true) BookBean bean) {
        this.bookBeanDAO.save(bean);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("delete book object")
    public Response delete(@ApiParam("book ID") @PathParam("id") Long id) {
        BookBean bean = this.bookBeanDAO.get(id);
        if (bean == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.bookBeanDAO.delete(bean);
        return Response.ok().build();
    }

    @GET
    @Path("/search/{text}")
    @ApiOperation("search book object")
    public Response search(@ApiParam("text search") @PathParam("text") String text) {
        return Response.ok(this.bookBeanDAO.search(text)).build();
    }
}
