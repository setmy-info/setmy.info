var express = require('express');
var router = express.Router();

router.get('/picture', function (req, res) {
    var security = {

    };
    for (var header in req.headers) {
        console.log(JSON.stringify(header));
        if (header.toUpperCase() === "REFERER") {
            if (req.headers[header].indexOf("web.site") === -1) {
                security.CSRF_ATTACK = true;
            }
        } else if (header.toUpperCase() === "ORIGIN") {
            if (req.headers[header].indexOf("web.site") === -1) {
                security.CSRF_ATTACK = true;
            }
        }
    }
    res.send({headers: req.headers, security: security});
});

module.exports = router;
