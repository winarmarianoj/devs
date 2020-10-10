/*
function formLinks(btnLinks) {
	alert('hola soy Links')
}
*/
function formHistory(btnHistory) {
	alert('hola soy History')
}
/*
function formPosted(btnPosted) {
	alert('hola soy Post')
}
*/
function formPhotos(btnPhotos) {
	alert('hola soy Photos')
}

function formMessage(btnMessage) {
	alert('hola soy Message')
}

/*
class dataLinks {
	dataLinkName;
}

class UI {
	addLinks(link) {
		const linksList = document.getElementById('links-list');
		const element = document.createElement('div');
		element.innerHTML = `
            			<div class="card-body text-center mb-2">
				<strong>Link Name</strong>: ${link}
				<a href="#" class="btn btn-danger" name="delete">Delete</a>
			</div>
        `;
		linksList.appendChild(element);		
	}

	deleteLinks(element) {
		if (element.name === "delete") {
      		element.parentElement.parentElement.remove();
      		this.showMessage("Link Deleted Succsssfully", "success");
    	}
	}

	resetForm() {
    	document.getElementById("links-form").reset();
  	}

	showMessage(info, cssClass) {
		const linksList = document.getElementById('links-list');
		const element = document.createElement("div");
	    element.className = `alert alert-${cssClass} mt-2`;
	    element.innerHTML = info;
	    linksList.appendChild(element);

	    // Remove the Message after al segundo y medio
	    setTimeout(function () {
	      document.querySelector(".alert").remove();
	    }, 3000);
	}
}

// DOM EVENTS

//Funcion que toma la accion del boton Save y guarda el contenido en una variable
document.getElementById('links-form').addEventListener('submit', function(e) {	
	e.preventDefault();	
	dataLinkName = 	document.getElementById('linkName').value;		
});

// funcion que al presionar eliminar link lo destruye
document.getElementById('print-links').addEventListener('click', function(e) {
	const ui = new UI();
	ui.deleteLinks(e.target);
	e.preventDefault();
});

// funcion que me trae del HTML central a formLinks	
function formLinks(btnLinks) {	
	window.location.href = "../forms/formLinksAdd.html";
}

// funcion que nos lleva de nuevo al HTML central
function formReturn(btnReturn) {
	window.location.href = "../index.html";
	printLinks();
	alert("DONDE ESTOY")
}

// funcion que imprime el link en el espacio de Links HTML Central
function printLinks() {
	const ui = new UI();
	if (this.dataLinkName === "") {
		ui.showMessage("Please insert data in this field", "danger");
		danger();
	}else{				
		ui.addLinks(this.dataLinkName);	
		ui.showMessage("Link Added Succsssfully", "success");		
		correct();		
	}		
}



/* BOTONES Y FUNCIONES DE MENSAJES DE RESPUESTA */

function danger(){
	Swal.fire({
    //error
    type: 'error',
    title: 'Error',
    text: '¡Algo salió mal!',  
	});
}

function correct(){
	Swal.fire({        
        type: 'success',
        title: 'Éxito',
        text: '¡Perfecto!',        
	});
	ui.addLinks(data);	
}

$("#btn0").click(function(){
    alert("Mensaje con Alert");    
});

//ejemplo básico
$("#btn1").click(function(){
    Swal.fire('Ejemplo basico de Sweet Alert 2');
});	

//con opción de TYPE  //tipos de popups: error, success, warning, info, question
$("#btn2").click(function(){
    /*Swal.fire({
        //error
        type: 'error',
        title: 'Error',
        text: '¡Algo salió mal!',        
    });*/
    Swal.fire({        
        type: 'success',
        title: 'Éxito',
        text: '¡Perfecto!',        
    });
});
