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
				console.log(i)
			}

		}};
	xhttp.open("GET","https://api.jornalia.net/api/v1/articles?apiKey=ae08dd8c771e4caea2b28c401c68c01b&startDate=2020-11-28&categories=TECNOLOGIA",true);
	xhttp.send();
}

function drawNotice(info) {		
	const bodyNoticias = document.getElementById('noticias');
    const elementosNoticias = document.createElement('div');
	elementosNoticias.innerHTML = `
        <div id="daydata" class="card my-3">
            <div class="card userName d-flex justify-content-center">            	
            	<h5><b>Título : ${info.title}</b></h5>
            	<p>Fecha: ${info.publishedAt}</p>
                <p>Descriptión :${info.description}</p>
                <a Web Site href=${info.sourceUrl} target="_blank">Link a la Noticia</a>                 
                <img width=150 height=150 src=${info.imageUrl}>
            </div>
            
        </div>
    `;
    bodyNoticias.appendChild(elementosNoticias); 
}    
pruebaJornalia();
