/**
 *
 * npm init -y
 * npm install express
 * node server.js
 * */
const express = require("express");
const app = express();

app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.use((req, res, next) => {
    console.log("---- Request ----");
    console.log("Method:", req.method);
    console.log("URL:", req.originalUrl);
    console.log("Headers:", req.headers);

    if (req.body && Object.keys(req.body).length > 0) {
        console.log("Body:", req.body);
    } else {
        console.log("Body: (empty or non parsable)");
    }

    next();
});

app.get("/", (req, res) => {
    res.set("Content-Type", "text/html");
    res.send(`
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Hello world</title>
        </head>
        <body>
            <h1>Hello world</h1>
            <p>Node.js server response.</p>
        </body>
        </html>
    `);
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`✅ Server started: http://localhost:${PORT}`);
});
