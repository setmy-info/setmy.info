#!/usr/bin/env node

require('./example-module-foo');
require('./example-module-bar');

console.log(jsdi.exampleModuleFooService.foo());
console.log(jsdi.exampleModuleBarService.bar());
