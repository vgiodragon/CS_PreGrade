<?php
$response = array();

$conexion=mysqli_connect("localhost","u588311430_user","123456","u588311430_ocsm");

if (mysqli_connect_errno($conexion))
{
   echo "Error al conectar con MySQL: " . mysqli_connect_error();
}

$IdAlumno = $_POST['IdAlumno'];

$sqlCursos="SELECT SAL.nombreCurso,SAL.practica_1,SAL.practica_2,SAL.practica_3,SAL.practica_4,SAL.parcial,SAL.final, prom_final(SAL.Prom_PC, SAL.parcial, SAL.final) AS Prom_Curso FROM
(
SELECT DC.Curso_Ano, DC.Periodo, C.nombreCurso,NC.practica_1, NC.practica_2, NC.practica_3, NC.practica_4,NC.parcial,NC.final,NC.IdCurso,NC.codigoAlumno, 
prom_practicas(NC.practica_1, NC.practica_2, NC.practica_3, NC.practica_4) AS Prom_PC FROM Alumno A
INNER JOIN NotasCurso NC ON A.codigoAlumno = NC.codigoAlumno 
INNER JOIN Curso C ON NC.IdCurso = C.IdCurso AND A.IdFacultad = C.IdFacultad
INNER JOIN D_Curso DC ON A.codigoAlumno = DC.codigoAlumno AND C.IdCurso = DC.IdCurso AND DC.Curso_ano = 2016 AND DC.Periodo = 1
ORDER BY Curso_Ano, Periodo
) SAL WHERE SAL.codigoAlumno='$IdAlumno'";

$sqlJalado = "SELECT FINAL.codigoAlumno, SUM(NCre) AS Cre_Des FROM (
SELECT SAL.codigoAlumno, SAL.Curso_Ano, SAL.Periodo, SAL.IdCurso, SAL.nombreCurso, SAL.NCre, 
prom_final(SAL.Prom_PC, SAL.parcial, SAL.final) AS Prom_Curso FROM
(
SELECT A.IdFacultad, DC.Curso_Ano, DC.Periodo, C.nombreCurso, C.NCre, NC.*, 
prom_practicas(NC.practica_1, NC.practica_2, NC.practica_3, NC.practica_4) AS Prom_PC FROM Alumno A
INNER JOIN NotasCurso NC ON A.codigoAlumno = NC.codigoAlumno 
INNER JOIN Curso C ON NC.IdCurso = C.IdCurso AND A.IdFacultad = C.IdFacultad
INNER JOIN D_Curso DC ON A.codigoAlumno = DC.codigoAlumno AND C.IdCurso = DC.IdCurso
ORDER BY codigoAlumno, IdCurso, Curso_Ano, Periodo
) SAL GROUP BY codigoAlumno, SAL.nombreCurso HAVING Prom_Curso < 10.0
) FINAL WHERE FINAL.codigoAlumno = '$IdAlumno'
";

$sqlAprobado = "SELECT FINAL.codigoAlumno, SUM(NCre) AS Cre_Apr FROM (
SELECT SAL.codigoAlumno, SAL.Curso_Ano, SAL.Periodo, SAL.IdCurso, SAL.nombreCurso, SAL.NCre, 
prom_final(SAL.Prom_PC, SAL.parcial, SAL.final) AS Prom_Curso FROM
(
SELECT A.IdFacultad, DC.Curso_Ano, DC.Periodo, C.nombreCurso, C.NCre, NC.*, 
prom_practicas(NC.practica_1, NC.practica_2, NC.practica_3, NC.practica_4) AS Prom_PC FROM Alumno A
INNER JOIN NotasCurso NC ON A.codigoAlumno = NC.codigoAlumno 
INNER JOIN Curso C ON NC.IdCurso = C.IdCurso AND A.IdFacultad = C.IdFacultad
INNER JOIN D_Curso DC ON A.codigoAlumno = DC.codigoAlumno AND C.IdCurso = DC.IdCurso
ORDER BY codigoAlumno, IdCurso, Curso_Ano, Periodo
) SAL GROUP BY codigoAlumno, SAL.nombreCurso HAVING Prom_Curso >= 10.0
) FINAL WHERE FINAL.codigoAlumno = '$IdAlumno'
";

$sqlTotal = "SELECT FINAL.codigoAlumno, SUM(NCre) AS Cre_Tot FROM
(
SELECT SAL.codigoAlumno, SAL.Curso_Ano, SAL.Periodo, SAL.IdCurso, SAL.nombreCurso, SAL.NCre,
prom_final(SAL.Prom_PC, SAL.parcial, SAL.final) AS Prom_Curso FROM
(
SELECT A.IdFacultad, DC.Curso_Ano, DC.Periodo, C.nombreCurso, C.NCre, NC.*, 
prom_practicas(NC.practica_1, NC.practica_2, NC.practica_3, NC.practica_4) AS Prom_PC FROM Alumno A
INNER JOIN NotasCurso NC ON A.codigoAlumno = NC.codigoAlumno 
INNER JOIN Curso C ON NC.IdCurso = C.IdCurso AND A.IdFacultad = C.IdFacultad
INNER JOIN D_Curso DC ON DC.codigoAlumno = A.codigoAlumno AND C.IdCurso = DC.IdCurso
ORDER BY codigoAlumno, IdCurso, Curso_Ano, Periodo
) SAL 
) FINAL WHERE FINAL.codigoAlumno = '$IdAlumno'
";

$resultAprobado = mysqli_query($conexion, $sqlAprobado);
$resultJalado = mysqli_query($conexion, $sqlJalado);
$resultTotal = mysqli_query($conexion, $sqlTotal);
$resultCursos = mysqli_query($conexion, $sqlCursos);

if(!$resultAprobado && !$resultJalado && !$resultTotal && !$resultCursos){
  echo array('data'=> "error de tabla");
  return;
}

$rowAprob = mysqli_fetch_array($resultAprobado);
$rowJal = mysqli_fetch_array($resultJalado);
$rowTot = mysqli_fetch_array($resultTotal);

if (mysqli_num_rows($resultCursos) > 0) {
  $response["cursos"] = array();

  $i=0;
  while ($row = mysqli_fetch_array($resultCursos)) {
    $product = array();
    $product["nombreCurso"] = $row["nombreCurso"];
    $product["practica_1"] = $row["practica_1"];
    $product["practica_2"] = $row["practica_2"];
    $product["practica_3"] = $row["practica_3"];
    $product["practica_4"] = $row["practica_4"];
    $product["parcial"] = $row["parcial"];
    $product["final"] = $row["final"];
    $i++;
    array_push($response["cursos"], $product);
  }
  $response["success"] = 1;
} else {
  $response["success"] = 0;
}

$aprobado = $rowAprob[1];
$jalado   = $rowJal[1];
$total    = $rowTot[1];


if ($aprobado) {
  $response["success"] = 1;
  $response["message"] = "Informacion obtenida correctamente .";
  $response["total"] = $total;
  $response["aprobados"]=$aprobado;
  $response["jalado"]=$jalado;
//  $arr = array('total' => $total,'aprobados'=>$aprobado,'jalado'=>$jalado);
} else{
  $response["success"] = 0;
  $response["message"] = "No se pudo obtener informacion .";
//  $arr = array('data' => "vacio");
}

echo json_encode($response);

mysqli_close($conexion);
?>