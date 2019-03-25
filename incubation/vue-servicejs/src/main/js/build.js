var fs = require('fs');
var uglify = require("uglify-js");

fs.writeFileSync("dist/vue-servicejs.min.js", uglify.minify({
    "vue-servicejs.js": fs.readFileSync("src/main/webapp/js/vue-servicejs.js", "utf8")
}, {}).code, "utf8");

fs.createReadStream('src/main/webapp/js/vue-servicejs.js').pipe(fs.createWriteStream('dist/vue-servicejs.js'));

fs.createReadStream('dist/vue-servicejs.min.js').pipe(fs.createWriteStream('src/main/webapp/js/vue-servicejs.min.js'));
