const emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
const passRegex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
const namesRegex = /^([a-zA-Z])+$/;

// funcion que me trae del Login HTML 
function formLogin(btnLogin) {		
	log();
};

// funcion que me trae del Forgot HTML
function formForgot(btnForgot) {
	forgoted();
};

// funcion que me trae del Register HTML
function formRegister(btnRegister) {
	registed();
};

// Funcion que atiende al Login
function log(){
    var loged = {};
	loged.email = document.getElementById('email');
	loged.pass = document.getElementById('password');

	let resLogin = isNullEmpty(loged.email);
    resLogin &= isNullEmpty(loged.pass);
    resLogin &= isEmailCorrect(loged.email);
	resLogin &= isPassCorrect(loged.pass);

	if (resLogin){
		$.ajax({
			url: "rest/restServices",
			type: "POST",
			data: JSON.stringify(loged),
			contentType: "application/json",
			complete: resultado
		});					
		ui.withImg();
	}else{
		ui.danger();
	}	
	
	// FALTA DEVOLVER AL FRONT Y CONTINUAR AL SITIO O QUE LO INGRESE NUEVAMENTE
}


function forgoted(){
    var forgot = {};
	forgot.email = document.getElementById('email');
    forgot.pass1 = document.getElementById('password1');
    forgot.pass2 = document.getElementById('password2');

    let resForgot = isNullEmpty(forgot.email);
    resForgot &= isNullEmpty(forgot.pass1);
    resForgot &= isEmailCorrect(forgot.email);
    resForgot &= isPassCorrect(forgot.pass1);
    resForgot &= forgot.pass1 == forgot.pass2 ? true : false;

    if (resForgot) {
        $.ajax({
            url: "rest/rest/restServices",
            type: "POST",
            data: JSON.stringify(forgot),
            contentType: "application/json",
            complete: resultado
        });
        ui.withImg();
    } else {
        ui.danger();
    }

    // FALTA DEVOLVER AL FRONT Y CONTINUAR AL SITIO O QUE LO INGRESE NUEVAMENTE
}

function registed(){
    var checkin = {};
	checkin.name = document.getElementById('firstName');
    checkin.lastName = document.getElementById('lastName');
    checkin.email1 = document.getElementById('email1');
    let email2 = document.getElementById('email2');
    checkin.address1 = document.getElementById('address1');
    let address2 = document.getElementById('address2');
    checkin.year = document.getElementById('year');
    checkin.month = document.getElementById('month');
    checkin.day = document.getElementById('day');
    checkin.photo = localStorage.getItem("myPhoto");

    let resCheck = isNullEmpty(checkin.name);
    resCheck &= isNullEmpty(checkin.lastName);
    resCheck &= isNullEmpty(checkin.email1);
    resCheck &= isNullEmpty(checkin.address1);
    resCheck &= isNullEmpty(checkin.year);
    resCheck &= isNullEmpty(checkin.month);
    resCheck &= isNullEmpty(checkin.day);
    resCheck &= isEmailCorrect(checkin.email1);
    resCheck &= isPassCorrect(checkin.address1);
    resCheck &= isNameCorrect(checkin.name);
    resCheck &= isNameCorrect(checkin.lastName);
    resCheck &= checkin.email1 == email2 ? true : false;
    resCheck &= checkin.address1 == address2 ? true : false;
    resCheck &= isYearCorrect(checkin.year);
    resCheck &= isMonthCorrect(checkin.month);
    resCheck &= isDayCorrect(checkin.day);

    if (resCheck) {
        $.ajax({
            url: "rest/rest/restServices",
            type: "POST",
            data: JSON.stringify(checkin),
            contentType: "application/json",
            complete: resultado
        });
        ui.withImg();
    } else {
        ui.danger();
    }

    // FALTA DEVOLVER AL FRONT Y CONTINUAR AL SITIO O QUE LO INGRESE NUEVAMENTE
}

// FUNCTION AND METHODS

// Funcion que supervisa que el campo no este nulo ni vacío
function isNullEmpty(text) {
    if(text===null || text===' '){ui.danger();}
}

// Funcion que supervisa si el email cumple con los requisitos
function isEmailCorrect(mail){
    let ress=false; 
    if(!emailRegex.test(mail.value){ui.invalidAdd('Email inválido. Vuelve a cargarlo.');
    }else{ress=true;}
    return ress;
}
// // Funcion que supervisa si el password cumple con los requisitos
function isPassCorrect(passView){
    let resPass=false;  
    if(!passRegex.test(passView.value){ui.invalidAdd('Debe contener Números, Letras Mayúsculas y Minúsculas, algunos caracteres.');
    }else{resPass=true;}
    return resPass;
}

// Funcion que supervisa el ingreso de Name y lastName
function isNameCorrect(names) {
    let resName=false;
    if(!namesRegex.test(names.value){ui.invalidAdd('Debe contener solo letras.');}else{resName=true;}
    return resName;
}

// Funcion que supervisa que el año de nacimiento sea válido
function isYearCorrect(year) {
    let resYear=false;
    if(!year>1915 && year<=2020){ui.invalidAdd('Debes ingresar un año válido.');}else{resYear=true;}
    return resYear;
}

// Funcion que supervisa que el mes de nacimiento sea válido
function isMonthCorrect(month) {
    let resMonth=false;
    if(!month>0 && month<=12){ui.invalidAdd('Debes ingresar un mes válido.');}else{resMonth=true;}
    return resMonth;
}

// Funcion que supervisa que el día de nacimiento sea válido
function isDayCorrect(day) {
    let resDay=false;
    if(!day>0 && day<=31){ui.invalidAdd('Debes ingresar un día válido.');}else{resDay=true;}
    return resDay;
}

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fotoPerfil').addEventListener('change', (e)=>{   
    const reader = new FileReader();    
    reader.addEventListener("load", () => {
        localStorage.setItem("myPhoto", reader.result); 
    });
    reader.readAsDataURL(e.target.files[0]);
}); 