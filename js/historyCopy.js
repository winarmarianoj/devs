class ElementsFotos {
	dataHistory;
	dataPostPhotos;
}

class UIFotos {
	addHistoryPhotos(dato, foto) { }

	deleteLinks(element) {
		if (element.name === "delete") {
			element.parentElement.parentElement.remove();
			correct();
		}
	}

}
// PODRIA SER UNA FOTO, FOTO Y TEXTO, O TEXTO SOLO

function formHistory(btnHistory) {
	alert('hola soy History');
	postPhotos(1);
}
function formPhotos(btnPhotos) {
	alert('hola soy Photos');
	postPhotos(2);
}

// SUPERVISA LOCAL STORAGE DEL PC
function postPhotos(num) {
	if (storageAvailable('localStorage')) {
		// Yippee! We can use localStorage awesomeness
		taked(num);
	}
	else {
		noStorage();
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
	const ui = new UIFotos();
	const publicaciones = new ElementsFotos();
	publicaciones.dataHistory = document.getElementById("Textarea1").value;
	publicaciones.dataPostPhotos = document.getElementById("Textarea1").value;
	Textarea1.value = "";

	switch (numero) {
		case 1:
			if (publicaciones.dataHistory === "") {
				danger();
			} else {
				ui.addHistoryPhotos(publicaciones.dataHistory);
				correct();
			}
			break;
		case 2:
			if (publicaciones.dataPostPhotos === "") {
				danger();
			} else {
				addPostPhotos(publicaciones.dataPostPhotos);
				correct();
			}
			break;
		default:
			danger();
			break;
	}
};

function addPostPhotos(dato) {
	const postLists = document.getElementById('publicacionesPost');
	const element = document.createElement('div');

	element.innerHTML = `
			<div class="card my-3">
						<div class="card userName d-flex justify-content-center">
                            <p><b>Nombre del Usuario</b></p>
						</div>
						<div class="card-img">							
							<img id="imgPreview" src="" alt="Preview">
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
									<a href="#" class="btn btn-danger" id="btnDelete" name="delete">X</a>
								</div>
                            </div>
                        </div>				
			</div>
		`;
	postLists.appendChild(element);
	addFotoDiv();
}

function addFotoDiv() {
	const recentImageDataUrl = localStorage.getItem("recent-image");

	if (recentImageDataUrl) {
		document.querySelector("#imgPreview").setAttribute("src", recentImageDataUrl);
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
	const ui = new UIFotos();
	ui.deleteLinks(e.target);
	e.preventDefault();
});

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fileFoto').addEventListener('change', (e) => {
	const reader = new FileReader();

	reader.addEventListener("load", () => {
		localStorage.setItem("recent-image", reader.result);
	});

	reader.readAsDataURL(e.target.files[0]);
});

/* BOTONES Y FUNCIONES DE MENSAJES DE RESPUESTA */

function noStorage() {
	Swal.fire({
		type: 'error',
		title: 'Error',
		text: '¡No puedes utilizar Local Storage para almacenar imágenes :(',
	});
}

function danger() {
	Swal.fire({
		type: 'error',
		title: 'Error',
		text: '¡Algo salió mal! Debes completar el campo :(',
	});
}

function correct() {
	Swal.fire({
		type: 'success',
		title: 'Éxito',
		text: '¡Perfecto! :)',
	});
}
