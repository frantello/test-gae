'use strict';

angular.module('book')
    .controller('ListCtrl', function ($scope, book) {

        $scope.load = function() {
            book.list(function (list) {
                $scope.list = list.data;
            });
        }

        $scope.save = function() {
            book.save($scope.form, function() {
                $scope.form = {};
                $scope.load();
            });
        }

        $scope.delete = function(id) {
            book.delete(id, function() {
                $scope.load();
            });
        }

        $scope.form = {};

        $scope.load();
    });
