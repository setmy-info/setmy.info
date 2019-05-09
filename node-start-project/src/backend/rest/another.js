const express = require('express');
const router = express.Router();

router.get('/another', function (req, res) {
    res.send({message: "Another Hello World!"});
});

module.exports = router;
