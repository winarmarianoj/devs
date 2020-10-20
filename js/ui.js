class UI {
    addLinks(link) {
        const linksList = document.getElementById('links-list');
        const element = document.createElement('div');
        element.innerHTML = `
						<div class="card-body text-center mb-2">						
						${link}
				<a href="#" class="btn btn-danger" id="btnDelete" name="delete">X</a>
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
									<a href="#" class="btn btn-danger" id="btnDelete" name="delete">X</a>
								</div>
                            </div>
                        </div>				
			</div>
        `;
        postLists.appendChild(element);
    }

    addHistoryPhotos(dato) { }


    deleteLinks(element) {
        if (element.name === "delete") {
            element.parentElement.parentElement.remove();
        }
    }

    deletePost(element) {
        if (element.name === "delete") {
            element.parentElement.parentElement.parentElement.parentElement.remove();
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

    /* BOTONES Y FUNCIONES DE MENSAJES DE RESPUESTA */

    danger() {
        Swal.fire({
            type: 'error',
            title: 'Error',
            text: '¡Algo salió mal! Debes completar el campo',
        });
    }

    correct() {
        Swal.fire({
            type: 'success',
            title: 'Éxito',
            text: '¡Perfecto!',
        });
    }

    noStorage() {
        Swal.fire({
            type: 'error',
            title: 'Error',
            text: '¡No puedes utilizar Local Storage para almacenar imágenes :(',
        });
    }
}
