<?php
    require_once("connection.php");

    $id_kategori = $_POST["id_kategori"];
    $sql = "SELECT * FROM kategori WHERE id_kategori = $id_kategori";

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