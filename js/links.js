// funcion que me trae del HTML central a formLinks	
function formLinks(btnLinks) {		
	var dataLinkName = document.getElementById("Textarea1").value;	
	Textarea1.value = "";	

	if (dataLinkName === "" ){
		ui.showMessage("Please insert data in this field", "danger");
		//ui.danger();
	} else {
		ui.addLinks(dataLinkName);
		ui.showMessage("Link Added Succsssfully", "success");
		ui.correct();		
	}	
};


// funcion que al presionar eliminar post lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function(e) {
	ui.deletePost(e.target);
	ui.correct();
	e.preventDefault();
});

