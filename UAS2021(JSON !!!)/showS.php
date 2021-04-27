<?php 
include 'connect.php';

$query = "SELECT * FROM mahasantri";
$result = mysqli_query($con, $query);

if (mysqli_num_rows($result)>0) {
    $response = array();
    $response["mahasantri"] = array();

    while ($x = mysqli_fetch_array($result)) {
        $y['id'] = $x["id"];
        $y['nim'] = $x["nim"];
        $y['nama'] = $x["nama"];
        
        array_push($response["mahasantri"], $y);
    }

    echo json_encode($response, JSON_PRETTY_PRINT);
}else{
    $response["message"]="no data found";
    echo json_encode($response);
}

?>