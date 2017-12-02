package com.test.dao;

import com.googlecode.objectify.ObjectifyService;
import com.test.data.BookBean;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Book bean DAO.
 */
public class BookBeanDAO {

    private static final Logger LOGGER = Logger.getLogger(BookBeanDAO.class.getName());

    private BookSearchService bookSearchService;

    public BookBeanDAO() {
        bookSearchService = new BookSearchService();
    }
    /**
     * @return list of book beans
     */
    public List<BookBean> list() {
        LOGGER.info("Retrieving list of beans");
        return ObjectifyService.ofy().load().type(BookBean.class).order(BookBean.FIELD_AUTHOR).list();
    }

    /**
     * @param id
     * @return book bean with given id
     */
    public BookBean get(Long id) {
        LOGGER.info("Retrieving bean " + id);
        return ObjectifyService.ofy().load().type(BookBean.class).id(id).now();
    }

    /**
     * Saves given bean
     * @param bean
     */
    public void save(BookBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("null book object");
        }
        LOGGER.info("Saving bean " + bean.getId());
        ObjectifyService.ofy().save().entity(bean).now();

        bookSearchService.index(bean);
    }

    /**
     * Deletes given bean
     * @param bean
     */
    public void delete(BookBean bean) {
        if (bean == null) {
            throw new IllegalArgumentException("null book object");
        }
        LOGGER.info("Deleting bean " + bean.getId());
        ObjectifyService.ofy().delete().entity(bean);

        bookSearchService.unindex(bean);
    }

    public Collection<BookBean> search(String text) {
        if (text == null) {
            throw new IllegalArgumentException("null book text");
        }

        LOGGER.info("Search bean containing " + text);

        return ObjectifyService.ofy().load().type(BookBean.class).ids(bookSearchService.search(text)).values();
    }
}
