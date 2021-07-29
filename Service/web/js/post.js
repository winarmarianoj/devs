var user = JSON.parse(sessionStorage.getItem('user'));

// Al presionar el boton de publicar Photos
function formPhotos(btnPhotos) {
	var dataPostPhotos = document.getElementById("Textarea1").value;
	Textarea1.value = "";
	if (dataPostPhotos === ""){msg.danger();} else{createPostPhotos(dataPostPhotos);}
}

// Funcion que envia el Texto publicado, con o sin foto
function createPostPhotos(dato){
	const postPhoto = localStorage.getItem("recent-image");
	localStorage.removeItem("recent-image");		

	let postFoto = {
		id: user.id,
		text: dato,
		photo: postPhoto
	}
	var post = JSON.stringify(postFoto)

	fetch('http://localhost:8080/Devs/rest/post/new', {
		method: 'POST',
		body: post
	})
	.then((response) => {
		if (response.ok) {
			response.json().then(data=>{
				if(data){
					window.location.href="index.html"
				}
			})			
		} else {
			throw 'Error en la llamada a Ajax';
		}
	})														
	.catch(function (err) {
		op.saveErrorsList(err);
		msg.danger()
	});		
}

// Funcion GET pide Lista de Posteos de Texto y Fotos


// Funcion que toma Get lista de Posteos, recorre y envia a producir objetos
var takeListPosted = async () => {
	const url = 'http://localhost:8080/Devs/rest/post/list/' + user.id;
	fetch(url)
		.then(response => response.json())
		.then(data => {
			if (data === null) {
				msg.linksEmpty();
				op.saveErrorsList('Lista de Links vacía');
			} else {
				data.forEach(element => {
					ui.addPostPhotos(element)
				});
			}
		})
}

// DOM EVENTS

// funcion que capta la img y la guarda en localStorage
document.querySelector('#fileFoto').addEventListener('change', (e)=>{	
	const reader = new FileReader();	
	reader.addEventListener("load", () => {
		localStorage.setItem("recent-image", reader.result); 
	});
	reader.readAsDataURL(e.target.files[0]);
});


// funcion que al presionar eliminar en algun objeto de Post : lo destruye
document.getElementById('publicacionesPost').addEventListener('click', function (e) {	
	let objPostTextDelete = e.target;
	let numIdPostText;
	if (objPostTextDelete.name === 'delete') {
		numIdPostText = objPostTextDelete.parentElement.parentElement.parentElement.parentElement.id;
	}
	var postTextDelete = {
		userId: user.id,
		postId: numIdPostText
	}
	let body = JSON.stringify(postTextDelete);
	fetch('http://localhost:8080/Devs/rest/post/delete', {
		method: 'POST',
		body: body
	})
		.then((response) => {
			if (response.ok) {
				response.json().then(data=>{
					if(data){
						window.location.href = "index.html"
					}
				})				
			} else {
				throw 'Error en la llamada a Ajax';
			}
		})
		.catch(function (err) {
			op.saveErrorsList(err);
			msg.danger()
		});
	e.preventDefault();
});

takeListPosted()
ui.addProfile(user)
