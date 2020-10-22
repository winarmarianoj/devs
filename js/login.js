












/* REVISA PASS  regex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/; */



/*
const data = new FormData(document.getElementById(formLogin));
fetch('../post.php', {
	method: 'POST',
	body: data
 })
 .then(function(response) {
	if(response.ok) {
		return response.text()
	} else {
		throw "Error en la llamada Ajax";
	}
 
 })
 .then(function(texto) {
	console.log(texto);
 })
 .catch(function(err) {
	console.log(err);
 });

*/

//DOM

/*
FUNCION DEL PDF JAVASCRIPT QUE NO FUNCIONA LA API NOSE 
document.getElementById('formLogin').addEventListener('submit', function(e) {
	e.preventDefault();
	// ahora a recuperar los datos ingresados en el form	
	let logins = new Logins();
	logins.email = document.getElementById('email').value;
	logins.pass = document.getElementById('password').value;

	const usersPromise = fetch('/api', {
		method : 'POST',
		body : JSON.stringify(logins)
	}).then(response => {
		if (!response.ok){
			throw new Error("no funciona response con Api.Server");
		}
		return response.json();
	}).then(responseData => {
		return responseData.users;
	});

	usersPromise.then(users => {
		console.log("Know users: ", users);
	}, error => {
		console.error("Faileeeeeeeeeeeeeeeeeeeed", error);
	});

}); //login o register

*/

/*
function ajax(file, params, callback) {
	var url = file + '?';
	// loop through object and assemble the url
	var notFirst = false;
	for (var key in params) {
	if (params.hasOwnProperty(key)) {
	url += (notFirst ? '&' : '') + key + "=" + params[key];
	}
	notFirst = true;
	}
	// create a AJAX call with url as parameter
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	callback(xmlhttp.responseText);
	}
	};
	xmlhttp.open('GET', url, true);
	xmlhttp.send();
}


*/

	



/*
	var jsonLogin = JSON.stringify(logins);
	
	
	
	
	
	// convierto objeto en tipo Json	
	ajax(jsonLogin);						// envio a funcion

	e.preventDefault(); //para evitar el refresco de pantalla
	
}
*/

/*
document.getElementById('formLogin').addEventListener('submit', function(e) {
	let logins = new Logins();
	logins.email = document.getElementById('email').value;
	logins.pass = document.getElementById('password').value;
	let logJson = JSON.stringify(logins);
	let childWindow = window.open("https://jsfiddle.net/ozzan/wmxf9fs2/show", "_blank");

	if (!logJson || !logJson.length) return;
	childWindow.postMessage(JSON.stringify({
		logJson: logJson,
		time: new Date()
	}), "https://jsfiddle.net/");
	document.getElementById("response").innerHTML = t;
	console.log(logJson);
});

*/

/*
function ajax(jsonLogin){
	const http = new XMLHttpRequest();
	const url = 'http://127.0.0.1:5500/login.html';  // aca deberia ir la url de java

	http.onreadystatechange = function(){

		if (this.readyState === 4 && this.status === 200){
			console.log(jsonLogin);
		}				
	}
	http.open("GET", url, true);
	http.send();
};
*/

// OTRA FUNCION QUE DEBERIA FUNCIONAR

/*
function sendMessage(message) {
    if (!message || !message.length) return;
    childWindow.postMessage(JSON.stringify({     // EN EL EJEMPLO ES UNA PAGINA EN BLANCO
        message: message,
        time: new Date()
    }), "https://jsfiddle.net/");
    console.log("sent");
}

var childWindow = window.open("https://jsfiddle.net/ozzan/wmxf9fs2/show", "_blank");

*/




/*

	var http = new XMLHttpRequest();
	var url = "tu_url";
	http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	http.open("POST", url, true);

	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) { 
		//aqui obtienes la respuesta de tu peticion
		alert(http.responseText);
		}
	}
	http.send(jsonLogin);    

http.send(JSON.stringify({email:email, password: password}));

*/