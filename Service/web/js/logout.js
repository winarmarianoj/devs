function formLogout(logout) {

    let url = 'http://localhost:8080/Devs/rest/account/logout/' + user.id
    fetch(url)
    sessionStorage.removeItem("user")

}

