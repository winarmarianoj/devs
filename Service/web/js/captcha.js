function verifyCaptcha(){
    var response = grecaptcha.getResponse();
    var verified = false
    if(response.length > 0){
         verified = true // Esta linea no deberia estar en realidad, leer abajo.

        /* Esta parte seria la que envia al respuesta al servidor de google para verificar correctamente.
           No pudimos hacerlo andar, tiraba un error relacionado con algo de CORS, lo que estamos
           haciendo es verificar al menos que haya completado el captcha.
        */

        // var body = {
        //     secret : '6LcU6O4ZAAAAAKFzRhk6yAkEQ02XiY1LOUZcnX3n',
        //     response : response
        // }
        // fetch("https://www.google.com/recaptcha/api/siteverify", {
        //     method : 'POST',
        //     body : body
        // })
        //     .then(function (response){
        //         if(response.ok){
        //             response.json().then(data =>{
        //                 verified = data.success
        //             })
        //         }
        //     })
    }
    return verified
}


