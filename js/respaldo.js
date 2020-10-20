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
    	document.getElementById("form-Text-Area").reset();
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
	    }, 2500);
	}
}

// DOM EVENTS

// funcion que al presionar eliminar link lo destruye
document.getElementById('links-list').addEventListener('click', function(e) {
	const ui = new UI();
	ui.deleteLinks(e.target);
	e.preventDefault();
});

// funcion que me trae del HTML central a formLinks	
function formLinks(btnLinks) {	
	//window.location.href = "../forms/formLinksAdd.html";	
	let dataLinkName = 	document.getElementById('Textarea1').value;	
	const ui = new UI();
	if (dataLinkName === "") {
		ui.showMessage("Please insert data in this field", "danger");
		danger();
	}else{				
		ui.addLinks(dataLinkName);	
		ui.showMessage("Link Added Succsssfully", "success");		
		correct();		
	}
	ui.resetForm();
};

function formPosted(btnPosted) {
	alert('estoy en linksssssssss')
}

// funcion que nos lleva de nuevo al HTML central
function formReturn(btnReturn) {
	window.location.href = "../index.html";
	printLinks();
	alert("DONDE ESTOY")
};

/* BOTONES Y FUNCIONES DE MENSAJES DE RESPUESTA */

function danger(){
	Swal.fire({
    //error
    type: 'error',
    title: 'Error',
    text: '¡Algo salió mal! Debes completar el campo',  
	});
}

function correct(){
	Swal.fire({        
        type: 'success',
        title: 'Éxito',
        text: '¡Perfecto!',        
	});
}

$("#btn0").click(function(){
    alert("Mensaje con Alert");    
});

//Básico
$("#btn1").click(function(){
    Swal.fire('Llename con Info');
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
