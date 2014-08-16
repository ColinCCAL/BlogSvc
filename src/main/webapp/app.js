(function(angular) {
    'use strict';

    angular.module('app', ['ngRoute']);

    angular.module('app')
        .config(['$routeProvider',
            function ($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'main.html'
                })
                .when('/about', {
                    templateUrl: 'about.html'
                })
                .when('/contact', {
                    templateUrl: 'contact.html'
                })
                .when('/post', {
                    templateUrl: 'post.html',
                });
        }])

        .controller('BlogController', [
            'blogFactory',
            '$scope',
            function (blogFactory, $scope) {
                blogFactory.get()
                    .success(function(data) {
                        $scope.posts = data;
                    });
                }])

        .controller('BlogPostController', [
            '$scope',
            '$http',
            function($scope, $http) {
                $scope.blogPosted = false;
                $scope.blog = {};
                var blog = $scope.blog;
                $scope.postBlog = function() {
                    $http({
                        url: 'api/blog',
                        method: "POST",
                        data: blog,
                        headers: {'Content-Type': 'application/json'}
                    })
                        .success(function(data) {
                            $http.get('api/blog/' + data)
                                .success(function(data) {
                                    $scope.retrievedBlog = data;
                                    $scope.blogPosted = true;
                                });
                    })
                        .error(function(data) {
                            console.log(data);
                        });
                }


            }
        ])
        .factory('blogFactory', [
            '$http',
            function ($http) {
                return {
                    get: function() {
                        return $http.get('api/blog');
                    },

                    post: function(blog) {
                       return $http({
                            url: 'api/blog',
                            method: "POST",
                            data: blog,
                            headers: {'Content-Type': 'application/json'}
                        });
                    }
                }
            }
        ]);

})
(angular);
