	.data
resultado:
	.asciiz "El promedio de los numeros es: "
lista:
	.byte 9,7,4,3,11,12,6,0   #0 indica el final de la lista de numeros
	
	.text
	
main:
	la $s6,lista		#
	move $s0,$s6
while:
	li $f0,($s0)
	li $a0,$f0
	b $a0,0,salir
	
salir:
