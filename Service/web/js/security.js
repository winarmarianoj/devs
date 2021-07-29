var user = JSON.parse(sessionStorage.getItem('user'));

function checkLogIn(){
    if(user === null){
        window.location.href="../login.html"
    }
}
checkLogIn();