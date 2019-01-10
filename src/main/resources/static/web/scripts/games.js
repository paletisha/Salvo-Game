getData();

function getData() {
   fetch("http://localhost:8080/api/games", {
   }).then(function (result) {
       return result.json()
   }).then(function (datta) {
data = datta;
createList();
console.log(data);
   })
   }


function createList() {
var ol = document.getElementById("idlist");

for (var i=0; i< data.length; i++) {
var newLi = document.createElement("li");
var newLi2 = document.createElement("li");
var date2 = new Date(data[i].date)
newLi.append("Creation date: " + date2.toLocaleDateString());


for ( x = 0; x< data[i].gameplayers.length; x++) {
var player = data[i].gameplayers[x].player;
newLi.append(" Player emails: " + player.email + " Player Names: "+ player.userName);
}
ol.append(newLi);
}

};

