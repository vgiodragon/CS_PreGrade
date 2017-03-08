<?php
$response = array();

$conexion=mysqli_connect("localhost","u588311430_user","123456","u588311430_ocsm");

if (mysqli_connect_errno($conexion))
{
   echo "Error al conectar con MySQL: " . mysqli_connect_error();
}

$IdAlumno = $_POST['IdAlumno'];

$sql = "SELECT A.nombresAlumno,A.apellidoP,A.apellidoM,A.codigoAlumno , INF.ciclo, F.nombreFacultad , INF.especialidad, INF.celular, INF.mail
FROM Alumno A
INNER JOIN Facultad F ON A.IdFacultad = F.IdFacultad
INNER JOIN InfoAlumno INF ON A.codigoAlumno = '$IdAlumno'
AND A.IdFacultad = INF.IdFacultad
";

$result = mysqli_query($conexion, $sql); 

if(!$result){
  echo array('data'=> "error de tabla");
  return;
}

$row = mysqli_fetch_array($result);
$nombres      = $row[0];
$apellidoP    = $row[1];
$apellidoM    = $row[2];
$codigo       = $row[3];
$ciclo        = $row[4];
$facultad     = $row[5];
$especialidad = $row[6];
$cel          = $row[7];
$mail         = $row[8];


if ($row) {
  $response["success"] = 1;
  $response["message"] = "Informacion obtenida correctamente .";
  $arr = array('nombres' => $nombres,'apellidoP'=>$apellidoP,'apellidoM'=>$apellidoM,'codigo'=>$codigo,'ciclo'=>$ciclo,'facultad'=>$facultad,'especialidad'=>$especialidad,'cel'=>$cel,'mail'=>$mail,'success'=>$response["success"],'message' =>$response["message"]);
} else{
  $response["success"] = 0;
  $response["message"] = "No se pudo obtener informacion .";
  $arr = array('data' => "vacio",'success'=>$response["success"],'message' =>$response["message"]);
}
echo json_encode($arr);

mysqli_close($conexion);
?>