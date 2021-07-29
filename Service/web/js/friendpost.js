function friendListPost (){

    var friend = JSON.parse(sessionStorage.getItem("friend"))
    console.log(friend)
    const url = 'http://localhost:8080/Devs/rest/post/list/' + friend.id;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data === null) {
                msg.linksEmpty();
                op.saveErrorsList('Lista de Links vacía');
            } else {
                data.forEach(element => {
                    addFriendPost(element, friend.name, friend.lastname)
                });
            }
        })
}
friendListPost()

function addFriendPost(post, firstname, lastname) {
    const postLists = document.getElementById('friendpost');
    const postPhoto = document.createElement('div');

    var name = firstname + ' ' + lastname

    if(post.imagePath === ''){
    postPhoto.innerHTML = `
            <div id="${post.postId}" name="postId" class="card my-3">
                <div class="card userName d-flex justify-content-center">
                    <p><b>${name}</b></p>
                </div>               
                <div class="card my-3 card-body pt-0 pb-2">
                    ${post.text}
                </div>
                <div class="card-footer bg-white border-0 p-0">                                
                    <div class="d-flex justify-content-between align-items-center my-1">
                        <div class="col">
                            <p>Fecha: ${post.date.date.year}:${post.date.date.month}:${post.date.date.day}</p>    
                        </div>
                        <div class="col">
                            <p>Hora: ${post.date.time.hour}:${post.date.time.minute}:${post.date.time.second}</p>    
                        </div>                        
                    </div>
                </div>				
            </div>
        `;
}else{
    postPhoto.innerHTML = `
            <div id="${post.postId}" name="postId" class="card my-3">
                <div class="card userName d-flex justify-content-center">
                    <p><b>${name}</b></p>
                </div>
                <div class="card-img">							
                    <img src=${post.imagePath} width="800" height="450" alt="">
                </div>
                <div class="card my-3 card-body pt-0 pb-2">
                    ${post.text}
                </div>
                <div class="card-footer bg-white border-0 p-0">                                
                    <div class="d-flex justify-content-between align-items-center my-1">
                        <div class="col">
                            <p>Fecha: ${post.date.date.year}:${post.date.date.month}:${post.date.date.day}</p>    
                        </div>
                        <div class="col">
                            <p>Hora: ${post.date.time.hour}:${post.date.time.minute}:${post.date.time.second}</p>    
                        </div>                       
                    </div>
                </div>				
            </div>
        `;
}
postLists.appendChild(postPhoto);
}
