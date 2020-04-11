function create_table(size) {
    var total_string = "";
    var total_string_head = "";

    //total_string_head += "<tr> <th></th>";
    /*for (var k = 0; k < size; k++)
    {
        total_string_head += "<th>" + (k + 1) + "</th>";
    }
        total_string_head += "</tr>";
*/
    for (var i = 0; i < size; i++)
    {
        total_string += "<tr> ";//<th>" + (i + 1) + "</th>";
        for (var j = 0; j < size; j++)
        {
            if ((j+i)%2 == 1)
            {
                total_string += "<td id='"+i +"-"+j+"' class='cell-grey'> </td>";
            }
            else
            {
                total_string += "<td id='"+i +"-"+j+"'> </td>";
            }
        }
        total_string += "</tr>";
    }
    tbody.innerHTML = total_string;
    thead.innerHTML = total_string_head;
}
