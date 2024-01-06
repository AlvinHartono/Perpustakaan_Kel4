<?php
    require_once("connection.php");
    $no_telp = $_POST["no_telp"];
    $sql = "SELECT * FROM librarian WHERE no_telp = $no_telp";

    if($result = mysqli_query($CON, $sql)){
        $row = $result-> fetch_assoc();

        //convert the row to JSON format
        $json_data = json_encode($row);

        //output the JSON data
        echo $json_data;
    } else {
        echo "No Results found";

    }
    ?>