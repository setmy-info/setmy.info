const express = require('express');
const router = express.Router();

router.get('/example', function (req, res) {
    res.send({message: "Hello World!"});
});

module.exports = router;
