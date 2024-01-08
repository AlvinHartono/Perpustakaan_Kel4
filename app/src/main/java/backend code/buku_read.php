<?php
    require_once("connection.php");

    $sql = "SELECT buku.id_buku, buku.judul_buku, buku.penerbit, buku.pengarang,buku.tahun_terbit, kategori.nama_kategori, buku.image_buku 
    FROM buku 
    JOIN kategori ON buku.id_kategori = kategori.id_kategori";
    $result = mysqli_query($CON, $sql);
    
        // var_dump($result);
    
    
        $rows = array();

        //fetch all rows and store them in the $rows array
        while ($row = mysqli_fetch_assoc($result)){


            array_push($rows, array(
                "id_buku" => $row["id_buku"],
                "judul_buku" => $row["judul_buku"],
                "penerbit" => $row["penerbit"],
                "pengarang" => $row["pengarang"],
                "tahun_terbit" => $row["tahun_terbit"],
                "nama_kategori" => $row["nama_kategori"],
                "image_buku" => base64_encode($row['image_buku']),
            ));
        }
        echo json_encode($rows);

    mysqli_close($CON);
?>