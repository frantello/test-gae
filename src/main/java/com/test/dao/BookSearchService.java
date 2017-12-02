package com.test.dao;

import com.google.appengine.api.search.*;
import com.test.data.BookBean;

import java.util.ArrayList;
import java.util.List;

public class BookSearchService {

    private static final String INDEX_BOOK = "Book";

    private static final int TOKEN_MIN_SIZE = 2;

    private Index index;

    private PartialTextTokenizer partialTextTokenizer;

    public BookSearchService() {
        IndexSpec indexSpec = IndexSpec.newBuilder().setName(INDEX_BOOK).build();
        index = SearchServiceFactory.getSearchService().getIndex(indexSpec);

        partialTextTokenizer = new PartialTextTokenizer(TOKEN_MIN_SIZE);
    }

    public void index(BookBean bean) {
        Document doc = Document.newBuilder()
                .setId(String.valueOf(bean.getId()))
                .addField(Field.newBuilder().setName(BookBean.FIELD_NAME)
                        .setText(partialTextTokenizer.tokenize(bean.getName())))
                .addField(Field.newBuilder().setName(BookBean.FIELD_AUTHOR)
                        .setText(partialTextTokenizer.tokenize(bean.getAuthor())))
                .build();

        index.put(doc);
    }

    public void unindex(BookBean bean) {
        index.delete(String.valueOf(bean.getId()));
    }

    public List<Long> search(String text) {
        String query = new StringBuilder()
                .append(BookBean.FIELD_NAME).append(" = ").append(text)
                .append(" OR ")
                .append(BookBean.FIELD_AUTHOR).append(" = ").append(text)
                .toString();

        Results<ScoredDocument> results = index.search(query);

        List<Long> ids = new ArrayList<>();
        for (ScoredDocument document : results) {
            ids.add(Long.valueOf(document.getId()));
        }

        return ids;
    }
}
