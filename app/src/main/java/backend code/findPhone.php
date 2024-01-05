<?php
    require_once("connection.php");

    $no_telp = $_GET["no_telp"];
    $sql = "SELECT * FROM member WHERE no_telp = $no_telp";
    // $result = mysqli_fetch_row($sql);

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