function login(){
	var usuario
	var password	
	usuario = document.getElementById("txtusuario").value;
	password = document.getElementById("txtpassword").value;

	if(usuario=="adama" && password=="12345"){
 		location.href="http://localhost:8080/dashboard"
	}else{
		
		alert("error al ingresar")
	}
}

function salir(){
	window.location.replace("http://localhost:8080/login")
}