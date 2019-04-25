1. Build should build two files:
    a) servicesjs.js (not minified)
    b) servicesjs.min.js (minified)

2. And that one file should be usable in:
    a) web application, old way: <script src="servicesjs.js"></script>
        to hold development without dependencies - only editor and browser are needed to start development
    b) node applications adding as module
        to make development same way as in old browsers
    c) webpack, if possible.

3. serviceB injections should work as:
    var serviceA = {
        serviceB: null
    };

    or

    var serviceA = {
        inject: ['serviceB']
    };

4. Services should be usable without changes in browser and in node - hold similarity.
   No need to do context switching in mind and learn something else, when making development in web or node.

5. Base common services are going to another library: servedjs

TODO:

4. Webpack integration
5. 