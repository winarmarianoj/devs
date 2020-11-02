var photosLists = [];
localStorage.clear();

// PODRIA SER UNA FOTO, FOTO Y TEXTO, O TEXTO SOLO
function formHistory(btnHistory) {
	postPhotos(1);
}
function formPhotos(btnPhotos) {
	postPhotos(2);
}

// SUPERVISA LOCAL STORAGE DEL PC
function postPhotos(num) {
	if (storageAvailable('localStorage')) {
		taked(num);
	}
	else {		
		ui.noStorage();
	}
}
function storageAvailable(type) {
	try {
		var storage = window[type],
			x = '__storage_test__';
		storage.setItem(x, x);
		storage.removeItem(x);
		return true;
	}
	catch (e) {
		return e instanceof DOMException && (
			// everything except Firefox
			e.code === 22 ||
			// Firefox
			e.code === 1014 ||
			// test name field too, because code might not be present
			// everything except Firefox
			e.name === 'QuotaExceededError' ||
			// Firefox
			e.name === 'NS_ERROR_DOM_QUOTA_REACHED') &&
			// acknowledge QuotaExceededError only if there's something already stored
			storage.length !== 0;
	}
}

// funcion que realiza de acuerdo al tipo de publicacion que debe hacer
function taked(numero) {	
	var dataHistory = document.getElementById("Textarea1").value;
	var dataPostPhotos = document.getElementById("Textarea1").value;
	Textarea1.value = "";	

	if (dataHistory === "" || dataPostPhotos === ""){
		ui.danger();
	}

	switch (numero) {
		case 1:
			ui.addHistoryPhotos(dataHistory);
			ui.correct();
			break;
		case 2:
			createPostPhotos(dataPostPhotos);
			/*ui1.addPostPhotos(publicaciones.dataPostPhotos);*/
			ui.correct();
			break;
		default:
			ui.danger();
			break;
	}
};

function createPostPhotos(dato){
	const recentImageDataUrl = localStorage.getItem("recent-image");	
	let postFoto = {
		name: "Mariano",
		texto: dato,
		img: recentImageDataUrl
	}
	photosLists.push(postFoto)
	localStoragePhotosList(photosLists)
	ui.addPostPhotos(dato)
	
	/*
	if (recentImageDataUrl) {
		document.querySelector("#image-preview").setAttribute("src", recentImageDataUrl);
	}
	*/
	
}

function getPhotosList() {
	var storedList = localStorage.getItem('elementList');
	var listPhotos = [];
	if (storedList == null) {
		listPhotos = [];
	} else {
		listPhotos = JSON.parse(storedList);
	}
	return listPhotos;
}

function localStoragePhotosList(photosLists) {
	localStorage.setItem('elementList', JSON.stringify(photosLists));
}




/*
function addObject(element) {

	let idFotos = parseInt(localStorage.getItem("idsFot"));
	console.log(idFotos)

	localStorage.setItem("idFotos", element);
	localStorage.setItem("idsFot", idFotos);
}
*/

function showImages(num) {
	for (let i = 0; i < localStorage.length; i++) {
		if (i === num) {
			let res = localStorage.getItem(localStorage.key(i))
			image.src = res;
		}
	}
}

/*
document.addEventListener("DOMContentLoaded", () => {
		const recentImageDataUrl = localStorage.getItem("recent-image");

		if (recentImageDataUrl) {
			document.querySelector("#imgPreview").setAttribute("src", recentImageDataUrl);
		}

		localStorage.removeItem("recent-image");
	});
*/

// DOM EVENTS

// funcion que al presionar eliminar link lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function (e) {
	ui.deletePost(e.target);
	ui.correct();	
	e.preventDefault();
});

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fileFoto').addEventListener('change', (e)=>{	
	const reader = new FileReader();	
	reader.addEventListener("load", () => {
		localStorage.setItem("recent-image", reader.result); 
	});
	reader.readAsDataURL(e.target.files[0]);
});	

