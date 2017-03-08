<?php
$response = array();

$con=mysqli_connect("localhost","u588311430_user","123456","u588311430_ocsm");

if (mysqli_connect_errno($con))
{
   echo "Error al conectar con MySQL: " . mysqli_connect_error();
}

$IdAlumno = $_POST['IdAlumno'];
$passwd = $_POST['passwd'];

$sql="SELECT * FROM Login WHERE codigoAlumno = '$IdAlumno' AND passwd = '$passwd'";

$result = mysqli_query($con,$sql);

if(!$result){
  echo array('data'=> "error de tabla");
  return;
}

$row = mysqli_fetch_array($result);
$data = $row[0];

if ($data) {
  $response["success"] = 1;
  $response["message"] = "Te logeaste correctamente .";
  $arr = array('data' => $data,'success'=>$response["success"],'message' =>$response["message"]);
} else{
  $response["success"] = 0;
  $response["message"] = "usuario o contraseña invalidas ";
  $arr = array('data' => "Alumno no existe",'success'=>$response["success"],'message' =>$response["message"]);
}

 echo json_encode($arr);

mysqli_close($con);
?> 