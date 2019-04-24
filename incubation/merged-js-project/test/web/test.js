function onBodyLoad() {
    var element = jsdi.get().serviceA.getElement('output');
    element.innerHTML = jsdi.get('serviceA').getText();
}
