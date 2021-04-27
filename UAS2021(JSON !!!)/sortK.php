<?php
require "connectS.php";

$sql = "SELECT * FROM mahasantri";
$sql2 = "SELECT * FROM kegemaran m inner join hobby m1 on m.id=m1 WHERE id='.'";
$result = mysqli_query($con, $query);
$result2 = mysqli_query($con, $sql2);

if (mysqli_num_rows($result)>0) {
    $response = array();
    $response["mahasantri"] = array();

    while ($x = mysqli_fetch_array($result)) {
        $y['nama'] = $x["nama"];
        
        array_push($response["mahasantri"], $y);
    }

    echo json_encode($response, JSON_PRETTY_PRINT);
}else{
    $response["message"]="no data found";
    echo json_encode($response);
}

?>