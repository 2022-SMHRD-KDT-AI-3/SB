const express = require("express");
const router = express.Router();
const conn = require("../config/db.js");

const multer = require("multer"); // multer 모듈 불러오기(파일 업로드)

let localData = 100;

var storage = multer.diskStorage({ // 업로드한 파일의 이름을 유지하기 위해서 storage 변수를 생성함
  destination(req, file, cb) {
    cb(null, "upload"); // 파일의 저장 경로
  },
  filename(req, file, cb) {
    cb(null, `${Date.now()}_${file.originalname}`); // 파일의 이름이 중복되는 것을 방지하기 위해서 파일명 앞에 시간을 라벨링함
  },
});
var uploadWithOriginalFilename = multer({storage : storage});

////
// 단일 파일 업로드 라우터
router.post('/uploadFileWithOriginalFilename', uploadWithOriginalFilename.single('attachment'), function(req,res){ // 5

  console.log(req.file);

  res.render('confirmation', {
    file: req.file,
    files: null
  });
});

// 다중 파일 업로드 라우터
router.post('/uploadFilesWithOriginalFilename', uploadWithOriginalFilename.array('attachments'), function(req,res){ // 7
  res.render('confirmation', {
    file: null,
    files: req.files
  });
});
////

// 로그인 라우터
router.post("/login", function (request, response) {
  let user_id = request.body.user_id;

  let sql = "select * from user where user_id = ?";
  conn.query(sql, [user_id], function (err, rows) {
    console.log("test333");

    if (rows.length > 0) {

      console.log("123123123123");
      request.session.user = {
        "user_id": rows[0].user_id,
        "doggy_name": rows[0].doggy_name,
        "diary_id": rows[0].diary_id
      }

      response.redirect("http://127.0.0.1:3000/mainDiary");
    } else {
      console.log("로그인 실패");
    }
  });
});

// 일기 작성 라우터(동영상 포함, 플라스크랑 주고 받아야 함) 1
// router.post("/writeDiary", function (request, response) {
//   let user_id = request.body.user_id;

//   let sql = "select * from user where user_id = ?";
//   conn.query(sql, [user_id], function (err, rows) {

//     if (rows.length > 0) {
//       request.session.user = {
//         "user_id": rows[0].user_id,
//         "doggy_name": rows[0].doggy_name,
//         "diary_id": rows[0].diary_id
//       }

//       response.redirect("http://127.0.0.1:3000/mainDiary");
//     } else {
//       console.log("로그인 실패");
//     }
//   });
// });

// 일기 작성 라우터(동영상 포함, 플라스크랑 주고 받아야 함) 2
// 단일 파일 업로드 라우터
// 파일을 올리고 그 파일 경로를 플라스크로 주면 분석하고 결과값을 반환함
router.post('/writeDiary', uploadWithOriginalFilename.single('chooseFile'), function (request, response) {

  let diary_id = request.session.user.diary_id;
  let diary_title = request.body.diary_title;
  let diary_content = request.body.diary_content;
  let video_path = request.file.path;

  // 파일 업로드 부분 미리보기 기능 수정해야 함

  let sql = 'insert into diary(diary_id, title, content, date, video_path) values(?, ?, ?, now(), ?)'

  ////
 
  conn.query(sql, [diary_id, diary_title, diary_content, video_path], function(err, rows){

  });

  response.redirect("http://127.0.0.1:5000/emotion");
  
});//라우터


router.get('/check', function (request, response) {

  console.log("제발 와라");
  let sql2 = 'select * from diary';

  conn.query(sql2, function(err, rows){
    console.log(rows);
  });
});//라우터



router.get("/refresh", function (request, response) {
  console.log("옴?");
  response.render("mainDiary.ejs",{
  
  });

  console.log("여기는 옴???????옴?");


  // response.redirect("http://127.0.0.1:3000/mainDiary");

});

// router.get('/action', uploadWithOriginalFilename.single('attachment'), function (request, response) {

//   console.log(request.file);

//   // response.redirect("http://127.0.0.1:3000/mainDiary");
// });

// router.get('/emotion', uploadWithOriginalFilename.single('attachment'), function (request, response) {

//   console.log(request.file);

//   // response.redirect("http://127.0.0.1:3000/mainDiary");
// });

// 메인 페이지 라우터(ejs 변환, 이따 고치기)
router.get("/mainDiary", function (request, response) {
  let sql = "select * from user;" + "select * from diary;" + "select * from comment;";
  conn.query(sql, function(err, rows) {

    response.render("mainDiary.ejs",{
      myInfo : request.session.user,
      userInfo : rows[0],
      diary : rows[1],
      comment : rows[2]
    }); // 메인 페이지가 랜더링될 때 유저 세션 정보, 전체 일기 정보, 전체 댓글 정보가 다 전달됨(출력은 for문, if문으로)

    

  });
});

// 마이 페이지 라우터(ejs 변환, 이따 고치기)
router.get("/myDiary", function (request, response) {
  let sql = "select * from diary where diary_id = ?;" + "select * from comment;";
  conn.query(sql, [request.session.user.diary_id], function(err, rows) {

    response.render("myDiary.ejs",{
      user : request.session.user,
      diary : rows[0],
      comment : rows[1]
    }); // 마이 페이지가 랜더링될 때 유저 세션 정보, 내 일기 정보, 내 일기의 댓글 정보가 다 전달됨
  });
});

// 댓글 작성 라우터
router.post("/writeComment", function (request, response) {
  let user_id = request.session.user_id;

  let sql = "insert into comment(diary_id, user_id, content) values(?, ?, ?);";
  conn.query(sql, [aa, aa, aa], function (err, rows) {

    if (rows.length > 0) {
      request.session.user = {
        "user_id": rows[0].user_id,
        "doggy_name": rows[0].doggy_name,
        "diary_id": rows[0].diary_id
      }

      response.redirect("http://127.0.0.1:3000/mainDiary");
    } else {
      console.log("로그인 실패");
    }
  });
});

module.exports = router;