function validate(){
    var login=document.getElementById("login");
    var pass=document.getElementById("pass");

var regex=/.{3}.*/;

var result = true;

if(!regex.test(login.value)){
login.style.background = "#ff0000";
result=false;
}else{
login.style.background = "#ffffff";
}
if(!regex.test(pass.value)){
pass.style.background = "#ff0000";
result=false;
}else{
pass.style.background = "#ffffff";
}
return result;
}