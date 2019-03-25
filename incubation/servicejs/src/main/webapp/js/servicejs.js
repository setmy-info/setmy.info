/*
 MIT License
 
 Copyright (c) 2017 Imre Tabur <imre.tabur@eesti.ee>
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 
 https://github.com/Krabi/servicejs
 
 */
"use strict";

var jsdi = (function (jsdi) {

    var isInitialized = false;

    jsdi.services = {
    };

    jsdi.service = function (serviceName, serviceObject) {
        var instance;
        if (typeof serviceObject === 'function') {
            instance = serviceObject();
        } else {
            instance = serviceObject;
        }
        jsdi.services[serviceName] = instance;
    };

    jsdi.initServices = function () {
        jsdi.resolveDependencies();
        var serviceObject, serviceObjectPropertyName;
        for (serviceObjectPropertyName in jsdi.services) {
            serviceObject = jsdi.services[serviceObjectPropertyName];
            if (serviceObject.init) {
                serviceObject.init();
            }
        }
        isInitialized = true;
    };

    jsdi.resolveDependencies = function () {
        var serviceObject, serviceObjectPropertyName;
        for (serviceObjectPropertyName in jsdi.services) {
            serviceObject = jsdi.services[serviceObjectPropertyName];
            jsdi.resolveServiceDependenciesFromList(serviceObject);
            jsdi.resolveServiceDependenciesFromObject(serviceObject);
        }
    };

    jsdi.resolveServiceDependenciesFromList = function (serviceObject) {
        if (serviceObject.inject) {
            var depNamePosition, serviceName;
            for (depNamePosition in serviceObject.inject) {
                serviceName = serviceObject.inject[depNamePosition];
                serviceObject[serviceName] = jsdi.services[serviceName];
            }
        }
    };

    jsdi.resolveServiceDependenciesFromObject = function (serviceObject) {
        var propertyName, property;
        for (propertyName in serviceObject) {
            property = serviceObject[propertyName];
            if (property === null) {
                serviceObject[propertyName] = jsdi.services[propertyName];
            }
        }
    };

    jsdi.getObject = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };

    var getReplacement = function (serviceName) {
        if (serviceName) {
            return this.services[serviceName];
        }
        return this.services;
    };

    jsdi.get = function (serviceName) {
        if (!isInitialized) {
            this.initServices();
        }
        jsdi.get = getReplacement;
        return jsdi.get(serviceName);
    };

    return jsdi;
}(typeof exports === 'undefined' ? {} : exports));
