const ui = new UI();
const asignaciones = new Elements();

// funcion que me trae del HTML central a formLinks	
function formLinks(btnLinks) {		
	take(1);
};

// funcion que me trae del HTML central a formPost	
function formPosted(btnPosted) {
	take(2);
};

// funcion que al presionar eliminar link lo destruye
document.getElementById('links-list').addEventListener('click', function(e) {	
	ui.deleteLinks(e.target);
	e.preventDefault();
});

// funcion que al presionar eliminar post lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function(e) {
	ui.deletePost(e.target);
	ui.correct();
	e.preventDefault();
});

// funcion que realiza de acuerdo al tipo de publicacion que debe hacer
function take(numero){		
	asignaciones.dataLinkName = document.getElementById("Textarea1").value;		
	asignaciones.dataPostName = document.getElementById("Textarea1").value;
	Textarea1.value = "";	

	if (asignaciones.dataLinkName === "" || asignaciones.dataPostName === ""){
		ui.showMessage("Please insert data in this field", "danger");
		ui.danger();
	}

	switch(numero){
		case 1:
			ui.addLinks(asignaciones.dataLinkName);
			ui.showMessage("Link Added Succsssfully", "success");
			ui.correct();		
			break;
		case 2:
			ui.addPost(asignaciones.dataPostName);
			ui.showMessage("Link Added Succsssfully", "success");
			ui.correct();
			break;
		default:
			ui.danger();
			break;
	}
};
