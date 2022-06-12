const mysql = require("mysql");

let conn = mysql.createConnection({
    host: "project-db-stu.ddns.net",
    user: "smhrd_SB",
    password: "4321",
    port: "3307",
    database: "smhrd_SB",
    multipleStatements: true
});

module.exports = conn;