
var signU = false;
var signOff = document.getElementById("userName");
var signOn = document.getElementById("signChange");
var logInput = document.getElementById("logInput");
var signInput = document.getElementById("signBtn");
var logBack = document.getElementById("backLogin");
signOn.addEventListener("click", showSign);
logBack.addEventListener("click", backLog);


function showSign () {
    signU = true;
    hideSign();
    }

function backLog(){
    signU = false;
    hideSign();
}

function hideSign (){
  
     
    if (signU == false){
signOff.style.display = "none";
signOn.style.display = "block";
signInput.style.display= "none";
logBack.style.display="none";
logInput.style.display = "block";
    } else {
        signOff.style.display = "block";
        signOn.style.display = "none";
        signInput.style.display= "block";
        logInput.style.display = "none";
        logBack.style.display="block";

    }

 


}

hideSign();


console.log(signU)


function logIn() {

var ourData = {
    email: document.getElementById("email").value,
    pwd: document.getElementById("password").value
}
 
 
fetch("/api/login", {
                credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        method: 'POST',
        body: getBody(ourData)
    })
    .then(function (data) {
        console.log('Request success: ', data);
        if (data.status==200){
            alert("logged in") ;
            redirect();
        } else {alert("try again")};

    })
    .catch(function (error) {
        console.log('Request failure: ', error);
    });
 
function getBody(json) {
    var body = [];
    for (var key in json) {
        var encKey = encodeURIComponent(key);
        var encVal = encodeURIComponent(json[key]);
        body.push(encKey + "=" + encVal);
    }
    return body.join("&");
}

};

function signUp() {
    fetch('/api/players' , {
        credentials: 'include',
        method: 'POST',
        headers: {
 
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userName : document.getElementById("userName").value,
            email : document.getElementById("email").value,
            password : document.getElementById("password").value
        })
    }).then(function(response) {
        return response.json();
    }).then(function(json) {
        console.log('parsed json', json)
 logIn();
    }).catch(function(ex) {
        console.log('parsing failed', ex)
    });
 }

function redirect (){
    location.replace("http://localhost:8080/web/games.html")
};