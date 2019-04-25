function onBodyLoad() {
    var element = jsdi.get().serviceA.getElement('output');
    element.innerHTML = jsdi.get('serviceA').getText();

    console.log(jsdi.exampleModuleFooService.foo());
    console.log(jsdi.exampleModuleBarService.bar());
}
