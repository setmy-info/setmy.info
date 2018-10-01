'use strict';

webAppServices.factory('personService', function ($q, $http, Person, REST_RESOURCES_PREFIX) {
/**
 * angular.getService('personService').save({firstName: 'Imre', lastName: 'Tabur'});
 * angular.getService('personService').save({firstName: 'Jaana', lastName: 'Lind'});
 * angular.getService('personService').findAll();
 * 
 * */
    var personService = {

        get: function (id) {
            var deferred = $q.defer(), person = Person.get({personId: id}, function () {
                deferred.resolve(person);
            });
            return deferred.promise;
        },

        save: function (person) {
            var deferred = $q.defer(), newPerson;
            newPerson = new Person(person);
            newPerson.$save().then(function (savedPerson) {
                deferred.resolve(savedPerson);
            });
            return deferred.promise;
        },

        findByFirstName: function (firstName) {
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: REST_RESOURCES_PREFIX + '/person/firstName/' + firstName
            }).then(function (response) {
                
            });
            return deferred.promise;
        },

        findAll: function () {
            var deferred = $q.defer(), allPersons = Person.query(function () {
                deferred.resolve(allPersons);
            });
            return deferred.promise;
        }

    };

    return personService;
});

