var fs = require('fs');
var uglify = require("uglify-js");

fs.writeFileSync("dist/servicejs.min.js", uglify.minify({
    "servicejs.js": fs.readFileSync("src/main/webapp/js/servicejs.js", "utf8")
}, {}).code, "utf8");

fs.createReadStream('src/main/webapp/js/servicejs.js').pipe(fs.createWriteStream('dist/servicejs.js'));

fs.createReadStream('dist/servicejs.min.js').pipe(fs.createWriteStream('src/main/webapp/js/servicejs.min.js'));
