<?php
$response = array();

require_once __DIR__ . '/db_config.php';

$conexion = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

$sql = " SELECT * FROM empresa";

$result = mysqli_query($conexion, $sql); 

if (mysqli_num_rows($result) > 0) {
  $response["empresas"] = array();

  while ($row = mysqli_fetch_array($result)) {
    $product = array();
    $product["id"] = $row["id"];
    $product["nombre"] = $row["nombre"];
    array_push($response["empresas"], $product);
  }
  $response["success"] = 1;
  echo json_encode($response);
} else {
  $response["success"] = 0;
  $response["message"] = "No products found";
  echo json_encode($response);
}
?>