<?php
    require_once("connection.php");

    $id_member = $_POST['id_member'];
    $sql = "SELECT * FROM pinjam WHERE id_member = $id_member";

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