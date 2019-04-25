
require('../js/serviceA');
require('../js/serviceB');
require('../js/serviceC');

require('./example-module-foo');
require('./example-module-bar');

console.log(jsdi.exampleModuleFooService.foo());
console.log(jsdi.exampleModuleBarService.bar());
console.log("First example: " + jsdi.get('serviceA').getText());
console.log("Second example: " + jsdi.get().serviceA.getText());
