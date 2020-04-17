var     tbody = document.getElementById("tbody");
var     thead = document.getElementById("thead"); 
var     obj = null; // jsoon;
var    pseudo = "";
var player_exist=false;

function create_table(size) {
	console.log("size"+size)
    var total_string = "";
    var total_string_head = "";

    for (var i = size - 1; i >= 0; i--)
    {
        total_string += "<tr> ";
        for (var j = 0; j < size; j++)
        {
            if ((j+i)%2 == 1)
            {
                total_string += "<td id='"+j +"-"+i+"' class= 'cell cell-grey'> </td>";
            }
            else
            {
                total_string += "<td id='"+j +"-"+i+"'class= 'cell'> </td>";
            }
        }
        total_string += "</tr>";
    }
    tbody.innerHTML = total_string;
    thead.innerHTML = total_string_head;
}



document.addEventListener('keydown', (event) => {
    if (obj != null)
    {	
        const nomTouche = event.key;
		patch_command(pseudo, nomTouche);
        }
    // alert(`Vous avez appuyez sur ${nomTouche}`);
    
}, false);


function create_element(type, x, y, orientation = "") {

    element_instanciated = document.createElement("div");

	
    if (type == "Obstacles")
    {
		console.log("obstacle")
        element_instanciated.className = "adaptative_heigh obstacles";
    }
    else if (type == "ennemies")
    {
		console.log("ennemy")
        element_instanciated.className = "adaptative_heigh enemies";
    }
    else
    {
		console.log("player")
        element_instanciated.className = "adaptative_heigh player";
    }
    if (orientation != "")
    {
        element_instanciated.classList.add(orientation.toString());
    }
    mysize = obj.localmap.size;
    mysize /= 2;
    console.log(mysize);
    x += mysize;
    y += mysize;
    console.log("x"+x+"y"+y);
    console.log
    document.getElementById(x + '-' + y).appendChild(element_instanciated);   
}


function create_map(){
	console.log("create_map"+obj)
	players = obj.localmap.Ennemies
    obstacles = obj.localmap.Obstacles
    create_table(obj.localmap.size)

    players.forEach(el => {
        create_element("ennemies", el.x, el.y, el.direction)
    });
    obstacles.forEach(el => {
        create_element("Obstacles", el.x, el.y)
    });
    create_element("", obj.player.position.x, obj.player.position.y, obj.player.position.direction)
}

function received_newJson(pseudo){
    if (player_exist == false)
    {
        post_player(pseudo);
        player_exist = true;
    }
	if (obj == null){
			post_player(pseudo)
			get_player(pseudo)
			console.log(obj)
	} else {
		console.log("received json else clause")
		get_player(pseudo)
	}
}

function post_player(pseudo){
	url='http://localhost:8080/api/player/'+pseudo;
    x=new XMLHttpRequest();
    x.onload = () => {
		if (x.status == 200) {
   			 	obj = JSON.parse(x.responseText);
   			 	console.log('Player not existing - we create it for you!');
			} else {
				console.log('Player existing - we load it for you!');
				get_player(pseudo);
			}
	};
	
    x.open("post", url);
    x.send(null);
}

function get_player(pseudo){
	url='http://localhost:8080/api/player/'+pseudo;
    x=new XMLHttpRequest();
    x.onload = () => {
		if (x.status == 200) {
   			 	obj = JSON.parse(x.responseText);
   			 	console.log('Loading player success!');
   			 	create_map();
			} else {
	 	  console.log('Fail to load Player !');
			}
	};
	
    x.open("get", url);
    x.send(null);
}

function patch_command(pseudo, command){
	url='http://localhost:8080/api/player/'+pseudo+'/'+command;
    x=new XMLHttpRequest();
    x.onload = () => {
		if (x.status == 200) {
   			 	obj = JSON.parse(x.responseText);
   			 	console.log('You move!');
   			 	console.log(obj);
   			 	create_map();
			} else {
				get_player();
			 	console.log('Failed to move !');
			}
	};
	
    x.open("PATCH", url);
    x.send(null);
    
}

my_launch()

function my_launch(){
	if (player_exist == false){
	    pseudo = prompt("Please enter your name", "");
	    while (pseudo == null || pseudo == "") 
	    {
	        txt = "User cancelled the prompt.";
	        alert(txt)
	        pseudo = prompt("Please enter your name", "");
	    } 
	    player_exist == true;
    }
}


window.setInterval( function() { received_newJson(pseudo); }, 1500);