var xhttp = new XMLHttpRequest();

function pruebaJornalia(){
	var hoy = new Date();
	var dd = parseInt(hoy.getDate());
	var mm = parseInt(hoy.getMonth()+1);
	var yyyy = parseInt(hoy.getFullYear());
	
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			var data = JSON.parse(this.responseText); 	
			
			for (var i = 0; i<=2; i++) {  //for (var i = 0; i<data.articles.length - 1; i++) {
				drawNotice(data.articles[i])
			}

		}};
	xhttp.open("GET","https://api.jornalia.net/api/v1/articles?apiKey=ae08dd8c771e4caea2b28c401c68c01b&startDate=" + yyyy + mm + dd + "&categories=TECNOLOGIA",true);
	xhttp.send();
}

function drawNotice(info) {		
	const bodyNoticias = document.getElementById('noticias');
    const elementosNoticias = document.createElement('div');
	elementosNoticias.innerHTML = `
        <div id="daydata" class="card my-2">
            <div class="card userName d-flex justify-content-center">            	
            	<p><b>${info.title}</b></p>
            	<div class="card-footer bg-white border-0 p-0">                                
	                <div class="d-flex justify-content-between align-items-center my-1">
	                    <div class="col">
	                        <img width=80 height=80 src=${info.imageUrl}>
	                    </div>
	                    <div class="col">
	                        <p>Fecha: ${info.publishedAt}</p>
	                    </div>	                    
	                    <div class="col">
	                        <a Web Site href=${info.sourceUrl} target="_blank">Noticia</a>
	                    </div>
	                </div>
	                <div class="col">
	                    <p>${info.description}</p>
	                </div>
           		</div>           		
            </div>
        </div>
    `;
    bodyNoticias.appendChild(elementosNoticias); 
}    
pruebaJornalia();
