[{
    idx: 1,
    subject: '',
    readCount: 0,
    content: '',
    inputUserName: '',
    inputDate: 'YYYY-MM-DD',
    updateDate: 'YYYY-MM-DD'
}]



var BBS = {

};
BBS.getTestData = function() {
    var rows = [], row = {};
    row.idx = 1;
    row.subject = '테스트 게시물 입니다.';
    row.readCount = 1;
    row.content = '테스트 게시물 본문 입니다.';
    row.inputUserName = '홍길동';
    row.inputDate = '2015-01-01';
    row.updateDate = '2015-01-01';
    rows.push(row);
    rows.push(row);
    return rows;}
    
    BBS.display = function(rows) {
        var li = null, a = null, row1 = null, row2 = null;
        var listArea = $('#bbsListArea');
        for (var inx=0; inx<rows.length; inx++) {
            li = $('<li>');
            a = $('<a>');
            row1 = $('<div>');
            row1.text(rows[inx].subject + ' (' + rows[inx].updateDate + ')');
            row2 = $('<div>');
            row2.text(rows[inx].inputUserName + ' (' + rows[inx].readCount + ')');
            a.append(row1);
            a.append(row2);
            li.append(a);
            listArea.append(li);
        }
    }
    $(document).ready(function() {
        BBS.display(BBS.getTestData());});
