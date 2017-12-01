'use strict';

angular.module('book')
    .service('book', function ($http) {
        return {
            get: function (id, success) {
                return $http.get("/rest/book/" + id).then(success);
            },
            list: function (success) {
                return $http.get("/rest/book").then(success);
            },
            save: function (test, success) {
                return $http.post("/rest/book", test).then(success);
            },
            delete: function (id, success) {
                return $http.delete("/rest/book/" + id).then(success);
            }
        };
    });
