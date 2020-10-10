class Elements{
	dataLinkName;
	dataPostName;
}

class UI {
	addLinks(link) {
		const linksList = document.getElementById('links-list');
		const element = document.createElement('div');
		element.innerHTML = `
            			<div class="card-body text-center mb-2">
						 ${link}
				<a href="#" class="btn btn-danger" name="delete">Delete</a>
			</div>
        `;
		linksList.appendChild(element);		
	}

	addPost(dato) {
		const postLists = document.getElementById('publicacionesPost');
		const element = document.createElement('div');
		element.innerHTML = `
			<div class="card my-3">
						<div class="card userName d-flex justify-content-center">
                            <p><b>Nombre del Usuario</b></p>
                    	</div>
						<div class="card my-3 card-body pt-0 pb-2">
							${dato}
                        </div>
						<div class="card-footer bg-white border-0 p-0">                                
                            <div class="d-flex justify-content-between align-items-center my-1">
                                <div class="col">
                                    <button id="meGusta" type="button" class="btn btn-fbook btn-block btn-sm"> <i class="fa fa-thumbs-up"
										aria-hidden="true"></i> Me gusta
									</button>
                                </div>
                                <div class="col">
                                    <button id="comentar" type="button" class="btn btn-fbook btn-block btn-sm"><i class="fa fa-comment"
												aria-hidden="true"></i> Comentar
									</button>
                                </div>
                                <div class="col">
                                    <button id="compartir" type="button" class="btn btn-fbook btn-block btn-sm"><i class="fa fa-share"
												aria-hidden="true"></i> Compartir
									</button>
								</div>
								<div class="col">
									<a href="#" class="btn btn-danger" name="delete">Delete</a>
								</div>
                            </div>
                        </div>				
			</div>
        `;
		postLists.appendChild(element);
	}

	deleteLinks(element) {
		if (element.name === "delete") {
      		element.parentElement.parentElement.remove();
			  //this.showMessage("Link Deleted Succsssfully", "success");
			correct();
    	}
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

// funcion que al presionar eliminar post lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function(e) {
	const ui = new UI();
	ui.deleteLinks(e.target);
	e.preventDefault();
});

// funcion que realiza de acuerdo al tipo de publicacion que debe hacer
function take(numero){
	const ui = new UI();
	const asignaciones = new Elements();
	asignaciones.dataLinkName = document.getElementById("Textarea1").value;		
	asignaciones.dataPostName = document.getElementById("Textarea1").value;
	Textarea1.value = "";	
	switch(numero){
		case 1:
			if (asignaciones.dataLinkName === "") {
				ui.showMessage("Please insert data in this field", "danger");
				danger();
			}else{				
				ui.addLinks(asignaciones.dataLinkName);	
				ui.showMessage("Link Added Succsssfully", "success");		
				correct();		
			}			
			break;
		case 2:
			if (asignaciones.dataPostName === "") {
				ui.showMessage("Please insert data in this field", "danger");
				danger();
			}else{				
				ui.addPost(asignaciones.dataPostName);	
				ui.showMessage("Link Added Succsssfully", "success");		
				correct();		
			}
			break;
		default:
			danger();
			break;
	}
};

// funcion que me trae del HTML central a formLinks	
function formLinks(btnLinks) {	
	//window.location.href = "../forms/formLinksAdd.html";	
	take(1);
};

// funcion que me trae del HTML central a formPost	
function formPosted(btnPosted) {
	take(2);
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
