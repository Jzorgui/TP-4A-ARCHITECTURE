var     tbody = document.getElementById("tbody");
var     thead = document.getElementById("thead"); 
var     obj = null //jsoon;


function create_table(size) {
    var total_string = "";
    var total_string_head = "";

    for (var i = 0; i < size; i++)
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
        playerName = obj.player.name
        switch(nomTouche) {
            case "f" :
                url='http://localhost:8080/'+playerName+'/'+nomTouche;
                x=new XMLHttpRequest();
                x.open("patch", url);
                x.send(null);
            case "b" :
                url='http://localhost:8080/'+playerName+'/'+nomTouche;
                x=new XMLHttpRequest();
                x.open("patch", url);
                x.send(null);
                break
            case "l" :
                url='http://localhost:8080/'+playerName+'/'+nomTouche;
                x=new XMLHttpRequest();
                x.open("patch", url);
                x.send(null);
                break
            case "r" :
                url='http://localhost:8080/'+playerName+'/'+nomTouche;
                x=new XMLHttpRequest();
                x.open("patch", url);
                x.send(null);
                break
            case "s" :
                url='http://localhost:8080/'+playerName+'/'+nomTouche;
                x=new XMLHttpRequest();
                x.open("patch", url);
                x.send(null);
                break
        }
    //alert(`Vous avez appuyez sur ${nomTouche}`);
    }
}, false);

function create_element(type, x, y, orientation = "") {

    element_instanciated = document.createElement("div");

    if (type == "obstacles")
    {
        element_instanciated.className = "adaptative_heigh obstacles";
    }
    else if (type == "ennemies")
    {
        element_instanciated.className = "adaptative_heigh enemies"    
    }
    else
    {
        element_instanciated.className = "adaptative_heigh player";
    }
    if (orientation != "")
    {
        element_instanciated.classList.add(orientation.toString())
    }
    mysize = obj.localmap.size;
    mysize /=2;
    x += mysize;
    y += mysize;
    document.getElementById(x + '-' + y).appendChild(element_instanciated);   
}


function    create_map(){

    players = obj.localmap.players
    obstacles = obj.localmap.obstacles
    create_table(obj.localmap.size)

    players.forEach(el => {
        create_element("ennemies", el.x, el.y, el.direction)
    });

    obstacles.forEach(el => {
        create_element("obstacles", el.x, el.y)
    });

    create_element("", obj.player.position.x, obj.player.position.y, obj.player.position.direction)
}

function    received_newJson(){
    if (obj == null)
    {
        url='http://localhost:8080/api/player/{playername}';
        x=new XMLHttpRequest();
                x.open("get", url);
                x.responseType = 'text';
        obj = x;
        create_map()
    }
    else
    {
        url='http://localhost:8080/api/player/{playername}';
        x=new XMLHttpRequest();
                x.open("get", url);
                x.responseType = 'text';
        if (x != obj)
        {
            refresh
            obj = x
            createmap()
        }      
    }
}

window.setInterval(received_newJson, 1000);