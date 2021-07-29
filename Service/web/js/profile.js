// Funcion que solicita al Back los datos del perfil cuando el usuario 
// toca el boton de Perfil en la barra de menú del index.html
// Inserta los datos en el archivo profile.html
var requestProfileUser = () => {
    
    var user = JSON.parse(sessionStorage.getItem('user'));
  
    document.getElementById('firstNameProfile').value = user.name;
    document.getElementById('lastNameProfile').value = user.lastname;
    document.getElementById('yearProfile').value = user.birthdate.year;
    document.getElementById('monthProfile').value = user.birthdate.month;
    document.getElementById('dayProfile').value = user.birthdate.day;

    ui.addPhotoProfileUser();
}
requestProfileUser()
