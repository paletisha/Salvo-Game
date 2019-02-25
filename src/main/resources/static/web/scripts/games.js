var twotables = new Vue({
    el: '#tablevue',
    data: {

        games: {},
        scores: {},
        player1: "",
        player2: "",
        date: "",
        users: "",
        gapId:""
    },
    methods: {

        getGames() {
            fetch('/api/games')
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    this.games = data;
                    console.log(this.games);
                    this.users = data.player.userName;
                    console.log(this.users);

                });
        },
        giveMeId: function (gameplayers) {
            if (gameplayers[0].player.userName == this.users) {
                return gameplayers[0].id
            } else {
                return gameplayers[1].id
            }
        },
goToNewGame: function(){


},
        getScores() {
            fetch('/api/leaderboard')
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    this.scores = data;
                    console.log(this.scores);

                });
        },




        getDates: function formatDate(date) {
            date = new Date;
            var monthNames = [
                "Jan", "Feb", "Mar",
                "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct",
                "Nov", "Dec"
            ];

            var day = date.getDate();
            var monthIndex = date.getMonth();
            var year = date.getFullYear();

            return day + ' ' + monthNames[monthIndex] + ' ' + year;
        },

        logOut: function () {
            fetch('/api/logout', {
                method: 'POST',
            }).then(function (response) {
                location.replace("http://localhost:8080/web/index.html")
                return response.json();
            });
        },

        createGame: function () {
            fetch('/api/games', {
                method: 'POST',
            }).then(function (response) {
                /*  location.replace("http://localhost:8080/web/game.html?gp='+ giveMeId(game.gameplayers)")  */
                return response.json();
            }).then(function (data) {
                 this.gapId = data.gpId;
                 console.log(this.gapId);
                 window.location = "game.html?gp=" + this.gapId;
               return data.gpId;
        });

    },

    joinGame: function (gameId) {
    fetch('/api/game/' + gameId + '/players', {
        method: 'POST',
    }).then(function (response) {
        /*  location.replace("http://localhost:8080/web/game.html?gp='+ giveMeId(game.gameplayers)")  */
        return response.json();
    }).then(function (data) {
         
         window.location = "game.html?gp=" + data.joinId;
       
});

},
    },
    created() {
        this.getGames();
        this.getScores();
    }

});

























/* getData();
getScores();
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

   function getScores() {
      fetch("http://localhost:8080/api/leaderboard", {
      }).then(function (result) {
          return result.json()
      }).then(function (datta) {
   data = datta;
   
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

function createTable() {




} */