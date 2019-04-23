#!/usr/bin/env node

var fooModule = require('./example-module-foo');
var barModule = require('./example-module-bar');

console.log(fooModule.exampleModuleFooService.foo());
console.log(barModule.bar());
console.log(globalVariable);
