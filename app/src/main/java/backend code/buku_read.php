<?php
    require_once("connection.php");

    $id_buku = $_POST["id_buku"];
    $sql = "SELECT * FROM buku WHERE id_buku = $id_buku";

    if($result = mysqli_query($CON, $sql)){
        $row = $result->fetch_assoc();

        // Convert the row to JSON format
        $json_data = json_encode($row);
    
        // Output the JSON data
        echo $json_data;
    } else {
        echo "No results found";
    }

?>