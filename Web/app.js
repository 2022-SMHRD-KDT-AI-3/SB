const express = require("express");
const app = express();
const bodyparser = require('body-parser');
const router = require('./routes/router.js');
const ejs = require("ejs");
const session = require("express-session");
const session_mysql_save = require("express-mysql-session");

let db_info = {
    host: 'project-db-stu.ddns.net',
    user: 'smhrd_SB',
    password: '4321',
    port: '3307',
    database: 'smhrd_SB'
}

let session_info = new session_mysql_save(db_info);

app.use(bodyparser.urlencoded({extended : false}));

// ejs에 static css 적용
app.use(express.static('public'));

app.use(session({
    secret : "zizi",
    resave : false,
    saveUninitialized : true,
    store : session_info
}));

app.use(router);
app.set("view engine", "ejs");

app.listen(3000);