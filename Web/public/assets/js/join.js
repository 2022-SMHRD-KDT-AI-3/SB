function send() {
    if(document.join.loginId.value.length==0) {
    alert('아이디를 입력하세요');
    document.join.id.focus();
    return;
    }
    document.join.submit();
    }