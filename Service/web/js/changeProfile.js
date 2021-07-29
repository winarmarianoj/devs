// funcion que me trae del Login HTML 
function formChangeProfile(btnChangeProfile) {		    

    var changeProfile = {
            id : user.id,
            name : document.querySelector('#firstNameProfile').value,
            lastname : document.querySelector('#lastNameProfile').value,
            birthdate: {
                year : document.querySelector('#yearProfile').value,
                month : document.querySelector('#monthProfile').value,
                day : document.querySelector('#dayProfile').value,
            }
        };	
            
    let resProfile = op.isNotNullEmpty(changeProfile.name, "El Nombre");
    resProfile &= op.isNameCorrect(changeProfile.name, "El Nombre");    
    resProfile &= op.isNotNullEmpty(changeProfile.lastname, "El Apellido");
    resProfile &= op.isNameCorrect(changeProfile.lastname, "El Apellido");
    resProfile &= op.isNotNullEmpty(changeProfile.birthdate.year);
    resProfile &= op.isNumber(changeProfile.birthdate.year);
    resProfile &= op.isNotNullEmpty(changeProfile.birthdate.month);
    resProfile &= op.isNumber(changeProfile.birthdate.month);
    resProfile &= op.isNotNullEmpty(changeProfile.birthdate.day);
    resProfile &= op.isNumber(changeProfile.birthdate.day);
    resProfile &= op.isFechaComplete(changeProfile);


    if (resProfile){sendingChangeProfile(JSON.stringify(changeProfile));}    
}

function sendingChangeProfile(newProfile) {
    fetch('http://localhost:8080/Devs/rest/user/edit', {
        method: 'POST',
        body: newProfile
    })
    .then((response) => {
        if(response.ok){
            response.json().then(data=>{
                if(data){
                    sessionStorage.removeItem("user")
                    sessionStorage.setItem("user", JSON.stringify(data))
                    msg.correct()
                    addProfileData(newProfile)        
                }
            })            
        } else {
            throw 'Error en la llamada a Ajax';
        }
    })   
    .catch(function(err) {
        op.saveErrorsList(err);
        msg.danger()
    });
}

function addProfileData(newProfile){
    var prof = JSON.parse(newProfile)
    var newImage = new Image();
    newImage.src = prof.img;

    ui.addProfile(prof)
    ui.addPhotoProfileUser();

    document.querySelector('#idUser').value = prof.id;
    document.querySelector('#firstNameProfile').value = prof.name;
    document.querySelector('#lastNameProfile').value = prof.lastName;
    document.querySelector('#yearProfile').value = prof.birthday.year;
    document.querySelector('#monthProfile').value = prof.birthday.month;
    document.querySelector('#dayProfile').value = prof.birthday.day;

    sessionStorage.removeItem('elementProfile');
    let listChangeProfile = []
    listChangeProfile.push(prof)
    sessionStorage.setItem('elementProfile', JSON.stringify(listChangeProfile));    
}
