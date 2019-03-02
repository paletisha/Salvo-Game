var vue = new Vue({
    el: '#gridvue',
    data: {
        letters: [
            "",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J"
        ],
        numbers: [
            "",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        ],
        gp: "",
        data: "",
        gamePlayerName_1: "",
        gamePlayerName_2: "",
        shipLocation: [
            {
                type: "battleship",
                location: []
            }, {
                type: "submarine",
                location: []
            }, {
                type: "patrol-boat",
                location: []
            }, {
                type: "carrier",
                location: []
            }, {
                type: "destroyer",
                location: []
            }
        ],
        salvoLocation: [],
        shipLength: "",
        hover: "false",
        noRepeat: "false",
        overlap: false,
        vertical: false,
        si: false,
        sHover: false,
        fireBtn: "",
    
    },
    computed: {
        allShips: function () {
            let shipsPlaced = this
                .shipLocation
                .filter(ship => ship.location.length > 0);
            return shipsPlaced.length == 5;

        }
    },
    methods: {
        getURL: function () {
            var url = new URL(window.location.href);
            this.gp = url
                .searchParams
                .get("gp");
            this.getData();
            console.log(this.gp);

        },

        getData: function () {
            fetch("/api/game_view/" + this.gp)
                .then(function (response) {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error(response.statusText);
                })
                .then(function (json) {
                    var data = json;
                    vue.data = data;
                    console.log(data);
                    vue.getShips(data);
                    vue.getNames(data);
                    vue.getSalvoes(data);
                    vue.getEnemySalvoes(data);
                    console.log(vue.data);

                    //functions to call
                })
                .catch(function (error) {
                    console.log("Request failed: " + error.message);
                })
            },
        getShips: function (data) {
            console.log(data.Ships.length);
            if (data.Ships.length == 0) {
                this.si = true;
            } else {
                this.si = false;
            }
            for (var i = 0; i < data.Ships.length; i++) {
                console.log("in for 1")
                for (var j = 0; j < data.Ships[i].location.length; j++) {
                    console.log("in for 2", data.Ships[i].location[j])
                    document
                        .getElementById(data.Ships[i].location[j])
                        .className += "shipColor2";
                }
            };

        },
        getNames: function (data) {

            for (var x = 0; x < data.Gameplayers.length; x++) {
                if (data.Gameplayers[x].id == this.gp) {
                    this.gamePlayerName_1 = data
                        .Gameplayers[x]
                        .player
                        .userName;
                } else {
                    this.gamePlayerName_2 = data
                        .Gameplayers[x]
                        .player
                        .userName;
                }
                if (data.Gameplayers.length == 1) {
                    this.gamePlayerName_2 = "Waiting for oponent...";
                }

            }
            console.log(this.gamePlayerName_1);
        },
        getSalvoes: function (data) {
            for (i = 0; i < data.Salvoes.length; i++) {
                for (j = 0; j < data.Salvoes[i].position.length; j++) {
                    document
                        .getElementById(data.Salvoes[i].position[j] + "s")
                        .innerHTML = data
                        .Salvoes[i]
                        .turn;
                    //document.getElementById(data.Salvoes[i].position[j]).classList.add("salvoes");
                    if (document.getElementById(data.Salvoes[i].position[j] + "s").classList.contains("shipPosition")) {
                        var img = document.createElement("img");
                        img.className = "bomb";
                        img.src = "styles/pixelexplosion.gif";
                        document
                            .getElementById(data.Salvoes[i].position[j] + "s")
                            .append(img);
                    } else {
                        var img = document.createElement("img");
                        img.className = "water";
                        img.src = "styles/water.gif";
                        document
                            .getElementById(data.Salvoes[i].position[j] + "s")
                            .append(img);
                    }

                }

            }
        },

        getEnemySalvoes: function (data) {

            for (i = 0; i < data.EnemySalvoes.length; i++) {
                console.log("for1")
                for (j = 0; j < data.EnemySalvoes[i].position.length; j++) {
                    document
                        .getElementById(data.EnemySalvoes[i].position[j])
                        .innerHTML = data
                        .EnemySalvoes[i]
                        .turn;
                    console.log("for2")
                    console.log(data.EnemySalvoes[i].position[j])

                    document
                        .getElementById(data.EnemySalvoes[i].position[j])
                        .classList
                        .add("enemySalvoes");

                    var img = document.createElement("img");
                    img.className = "bomb";
                    img.src = "styles/pixelexplosion.gif";
                    document
                        .getElementById(data.EnemySalvoes[i].position[j])
                        .append(img);
                    console.log(data.EnemySalvoes[i].position[j])
                }
            }
        },
        getSpaceships: function () {
            fetch('/api/games/players/' + this.gp + '/ships', {
                credentials: 'include',
                method: 'POST',
                headers: {

                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.shipLocation)
            })
                .then(function (response) {
                    return response.json();
                })
                .then(function (json) {
                    console.log('parsed json', json)

                    location.reload()
                })
                .catch(function (ex) {
                    console.log('parsing failed', ex)
                });

        },
        getBattleship: function () {

            this.shipLength = 1;
            this.hover = true;
            console.log(this.shipLength)

        },

        getSubmarine: function () {

            this.shipLength = 2;
            this.hover = true;
            console.log(this.shipLength)

        },

        getPatrolboat: function () {

            this.shipLength = 3;
            this.hover = true;
            console.log(this.shipLength)

        },

        getCarrier: function () {

            this.shipLength = 4;
            this.hover = true;
            console.log(this.shipLength)

        },

        getDestroyer: function () {

            this.shipLength = 5;
            this.hover = true;
            console.log(this.shipLength)

        },

        btnVertical: function () {
            this.vertical = true;
        },

        btnHorizontal: function () {
            this.vertical = false;
        },

        shipHover: function () {

            this.findIndex();
            this.overlap = false;

            if (this.hover == true) {
                var letter = event
                    .target
                    .id
                    .substr(0, 1);
                var number = event
                    .target
                    .id
                    .substr(1, 2);

                if (this.vertical == false) {
                    for (var i = 0; i < this.shipLength; i++) {

                        var id = letter + (Number(number) + i);

                        if (!document.getElementById(id)) {
                            this.overlap = true;
                        }

                        if (document.getElementById(id).classList.contains("shipColor2")) {
                            console.log("hay barco");
                            this.overlap = true;
                        }

                    }

                    if (!this.overlap) {
                        console.log(this.overlap);
                        for (var i = 0; i < this.shipLength; i++) {

                            var id = letter + (Number(number) + i);
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor")

                        }
                    }

                }
                if (this.vertical == true) {

                    for (var i = 0; i < this.shipLength; i++) {

                        var id = this.letters[
                            this
                                .letters
                                .indexOf(letter) + i
                        ] + number;

                        if (!document.getElementById(id)) {
                            this.overlap = true;
                        }

                        if (document.getElementById(id).classList.contains("shipColor2")) {
                            console.log("hay barco");
                            this.overlap = true;
                        }

                    }

                    if (!this.overlap) {
                        console.log(this.overlap);
                        for (var i = 0; i < this.shipLength; i++) {

                            var id = this.letters[
                                this
                                    .letters
                                    .indexOf(letter) + i
                            ] + number;
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor")

                        }
                    }

                }
            }

        },
        shipClean() {
            if (this.vertical == false) {
                var letter = event
                    .target
                    .id
                    .substr(0, 1);
                var number = event
                    .target
                    .id
                    .substr(1, 2);
                for (var i = 0; i < this.shipLength; i++) {
                    var id = letter + (Number(number) + i);

                    document
                        .getElementById(id)
                        .classList
                        .remove("shipColor")
                }
            }
            if (this.vertical == true) {
                var letter = event
                    .target
                    .id
                    .substr(0, 1);
                var number = event
                    .target
                    .id
                    .substr(1, 2);
                for (var i = 0; i < this.shipLength; i++) {
                    var id = this.letters[
                        this
                            .letters
                            .indexOf(letter) + i
                    ] + number;

                    document
                        .getElementById(id)
                        .classList
                        .remove("shipColor")
                }

            }
        },

        shipStay: function () {

            if (this.hover) {

                console.log(this.hover);
                var letter = event
                    .target
                    .id
                    .substr(0, 1);
                var number = event
                    .target
                    .id
                    .substr(1, 2);

                if (this.vertical == false) {
                    for (var i = 0; i < this.shipLength; i++) {
                        var id = letter + (Number(number) + i);

                        console.log(id);
                        console.log(this.emptyArray);

                        if (document.getElementById(id).classList.contains("shipColor2")) {
                            console.log("hay barco");
                            this.overlap = true;
                        }

                        if (!this.overlap) {
                            if (this.shipLength == 1) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn1");
                                btn.style.display = "none";
                                this
                                    .shipLocation[0]
                                    .location
                                    .push(id);

                            }
                            if (this.shipLength == 2) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn2");
                                btn.style.display = "none";
                                this
                                    .shipLocation[1]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 3) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn3");
                                btn.style.display = "none";
                                this
                                    .shipLocation[2]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 4) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn4");
                                btn.style.display = "none";
                                this
                                    .shipLocation[3]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 5) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn5");
                                btn.style.display = "none";
                                this
                                    .shipLocation[4]
                                    .location
                                    .push(id);
                            }
                        }

                    }
                }
                if (this.vertical == true) {
                    for (var i = 0; i < this.shipLength; i++) {
                        var id = this.letters[
                            this
                                .letters
                                .indexOf(letter) + i
                        ] + number;

                        console.log(id);
                        console.log(this.emptyArray);

                        if (document.getElementById(id).classList.contains("shipColor2")) {
                            console.log("hay barco");
                            this.overlap = true;
                        }

                        if (!this.overlap) {
                            if (this.shipLength == 1) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn1");
                                btn.style.display = "none";
                                this
                                    .shipLocation[0]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 2) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn2");
                                btn.style.display = "none";
                                this
                                    .shipLocation[1]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 3) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn3");
                                btn.style.display = "none";
                                this
                                    .shipLocation[2]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 4) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn4");
                                btn.style.display = "none";
                                this
                                    .shipLocation[3]
                                    .location
                                    .push(id);
                            }
                            if (this.shipLength == 5) {
                                document
                                    .getElementById(id)
                                    .classList
                                    .add("shipColor2");
                                var btn = document.getElementById("btn5");
                                btn.style.display = "none";
                                this
                                    .shipLocation[4]
                                    .location
                                    .push(id);
                            }
                        }

                    }

                }
                this.hover = false;

            }
        },

        findIndex: function () {
            var letter = event
                .target
                .id
                .substr(0, 1);
            console.log(this.letters.indexOf(letter))

        },

        shipHoverVertical: function () {

            this.findIndex();
            this.overlap = false;

            if (this.hover == true) {
                var letter = event
                    .target
                    .id
                    .substr(0, 1);
                var number = event
                    .target
                    .id
                    .substr(1, 2);
                for (var i = 0; i < this.shipLength; i++) {

                    var id = this.letters[
                        this
                            .letters
                            .indexOf(letter) + i
                    ] + number;
                    console.log(id)

                    if (!document.getElementById(id)) {
                        this.overlap = true;
                    }

                    if (document.getElementById(id).classList.contains("shipColor2")) {
                        console.log("hay barco");
                        this.overlap = true;
                    }

                }

                if (!this.overlap) {
                    console.log(this.overlap);
                    for (var i = 0; i < this.shipLength; i++) {

                        var id = this.letters[
                            this
                                .letters
                                .indexOf(letter) + i
                        ] + number;
                        document
                            .getElementById(id)
                            .classList
                            .add("shipColor")

                    }
                }

            }

        },

        shipCleanVertical() {

            var letter = event
                .target
                .id
                .substr(0, 1);
            var number = event
                .target
                .id
                .substr(1, 2);
            for (var i = 0; i < this.shipLength; i++) {
                var id = this.letters[
                    this
                        .letters
                        .indexOf(letter) + i
                ] + number;

                document
                    .getElementById(id)
                    .classList
                    .remove("shipColor")
            }

        },

        shipStayVertical: function () {

            if (this.hover) {

                console.log(this.hover);
                var letter = event
                    .target
                    .id
                    .substr(0, 1);
                var number = event
                    .target
                    .id
                    .substr(1, 2);
                for (var i = 0; i < this.shipLength; i++) {
                    var id = this.letters[
                        this
                            .letters
                            .indexOf(letter) + i
                    ] + number;

                    console.log(id);
                    console.log(this.emptyArray);

                    if (document.getElementById(id).classList.contains("shipColor2")) {
                        console.log("hay barco");
                        this.overlap = true;
                    }

                    if (!this.overlap) {
                        if (this.shipLength == 1) {
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor2");
                            var btn = document.getElementById("btn1");
                            btn.style.display = "none";
                        }
                        if (this.shipLength == 2) {
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor2");
                            var btn = document.getElementById("btn2");
                            btn.style.display = "none";
                        }
                        if (this.shipLength == 3) {
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor2");
                            var btn = document.getElementById("btn3");
                            btn.style.display = "none";
                        }
                        if (this.shipLength == 4) {
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor2");
                            var btn = document.getElementById("btn4");
                            btn.style.display = "none";
                        }
                        if (this.shipLength == 5) {
                            document
                                .getElementById(id)
                                .classList
                                .add("shipColor2");
                            var btn = document.getElementById("btn5");
                            btn.style.display = "none";
                        }
                    }

                }
                this.hover = false;
            }
        },

        salvoHover: function () {

            this.overlap = false;

            


            if (this.sHover == true) {
                var id = event.target.id;

                if (document.getElementById(id).classList.contains("salvoColor2")) {
                    this.overlap = true;
                }

                if (!this.overlap) {
                document
                    .getElementById(id)
                    .classList
                    .add("salvoColor");

            }
        }

            console.log(id);

        },

        salvoClean: function () {

            var id = event.target.id;

            if (this.sHover == true) {
                document
                    .getElementById(id)
                    .classList
                    .remove("salvoColor");
            }

        },

        salvoCaller1: function () {
            this.sHover = true;
            this.fireBtn = "one";

        },

        salvoCaller2: function () {
            this.sHover = true;
            this.fireBtn = "two";
        },

        salvoCaller3: function () {
            this.sHover = true;
            this.fireBtn = "three"
        },

        salvoStay: function () {

            var id = event.target.id;

            if (this.sHover == true) {

                if (document.getElementById(id).classList.contains("salvoColor2")) {
                    console.log("hay barco");
                    this.overlap = true;
                }

                if (!this.overlap) {

                document
                    .getElementById(id)
                    .classList
                    .add("salvoColor2");

                if (this.fireBtn == "one") {
                    document
                        .getElementById("salvo1")
                        .style
                        .display = "none";
                if(id.length == 3){
                this.salvoLocation.push(id.substr(0,2));
                } else {
                    this.salvoLocation.push(id.substr(0,3));
                }

                console.log(this.salvoLocation)
                
                }

                if (this.fireBtn == "two") {
                    document
                        .getElementById("salvo2")
                        .style
                        .display = "none";
                        if(id.length == 3){
                            this.salvoLocation.push(id.substr(0,2));
                            } else {
                                this.salvoLocation.push(id.substr(0,3));
                            }
                        console.log(this.salvoLocation)

                }

                if (this.fireBtn == "three") {
                    document
                        .getElementById("salvo3")
                        .style
                        .display = "none";
                        if(id.length == 3){
                            this.salvoLocation.push(id.substr(0,2));
                            } else {
                                this.salvoLocation.push(id.substr(0,3));
                            }
                        console.log(this.salvoLocation)

                }
            }
        }

            this.sHover = false;

        },

        fireSalvos: function(){
            fetch('/api/games/players/' + this.gp + '/salvos', {
                credentials: 'include',
                method: 'POST',
                headers: {

                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({"position": this.salvoLocation})
            })
                .then(function (response) {
                    return response.json();
                })
                .then(function (json) {
                    console.log('parsed json', json)

                     location.reload()
                })
                .catch(function (ex) {
                    console.log('parsing failed', ex)
                });
        }

    },
    created: function () {
        this.getURL();

    }
});
