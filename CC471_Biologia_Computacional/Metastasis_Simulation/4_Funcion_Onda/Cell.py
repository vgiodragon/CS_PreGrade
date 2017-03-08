#!/usr/bin/python
# -*- coding: utf-8 -*-

from cellVariables import *

class Cell():
  numCells = 1
  ArrayCells = []
  # kind = tipo de celula
  # x = posicion X
  # y = posicion Y
  # size = area (radio)
  # veloProg = velocidad de propagacion
  # colour = color de celula
  # thickness = colorea (0) o solo dibuja linea (1)
  # factorMove = 1.0 factor de movilidad
  # speed = velocidad de movimiento
  # angle = angulo al iniciar movimiento
  # idCell = id de celula
  # numChild = numero de hijos
  # state = determina si ya se dividio
  #       = 2; la celula madre original
  #       = 1; listo para dividir
  #       = 0; recien nacido
  #       = -1; ya se dividio y empieza a morir
  # eat = si puede comer o no
  def __init__(self, name,lifecolour,deadcolour,minsize, maxsize,(x, y),vel,deadFactor,state=2, caneat=False, angle=uniform(0.0,2*math.pi),speed=0.1,numChild=2):
      self.kind = name
      self.x = x
      self.y = y
      self.size = minsize
      self.minsize = minsize
      self.maxsize = maxsize
      self.vel = vel
      self.deadFactor = deadFactor
      self.colour = (255,255,255)
      self.lifecolour = lifecolour
      self.deadcolour = deadcolour
      self.thickness = 0
      self.factorMove = 1.0
      self.speed = speed
      self.angle = angle
      self.idcell = Cell.numCells
      self.numChild = numChild
      self.state = state
      self.caneat = caneat
      Cell.numCells+=1
      Cell.ArrayCells.append(self)

# ciclo de vida de la celula
  def lifeCycleCell(self,t):
    self.move()
    # si esta en el area, come
    # si no consigue comida en un tiempo, muere
    # si llega a llenarse, come
    # si llega a clonarse lo suficiente muere
    # encuentra quien tiene mayor interaccion
    nearAPW = self.findActivationPointNearlyWave(t)
    # encuentra quien esta mas cercana
    nearAP = self.findActivationPointNearly()
    # busca las funciones
    maxWaveAP = self.maxWave(nearAP,nearAPW,t)
    # busca el segundo mas cercano y compara con el mas intenso
    minNearAP = self.minDistance(nearAP,nearAPW)
    if(minNearAP == nearAPW and maxWaveAP == nearAPW):
      followAP = nearAPW
    else:
      followAP = minNearAP
    # evalua estados
    if(self.state==2):
      self.followActivationPoint(nearAP)
    else:
      if(self.state==0 and self.caneat==True):
        self.factorMove = 1.0
        self.colour = self.lifecolour
        self.followActivationPoint(nearAP)
        self.eatCell()
      elif(self.state==0 and self.caneat==False):
        self.factorMove = 2.0
        self.colour = self.deadcolour
        self.followActivationPoint(followAP)
        self.deadCell()
      elif(self.state==1):
        self.colour = self.lifecolour
        self.cellClone()
      elif(self.state==-1):
        self.colour = self.deadcolour
        self.deadCell()

# clonar celula
  def cellClone(self):
    if(self.numChild==2):
      self.numChild = self.numChild-1
      self.state = 0
    elif(self.numChild==1):
      self.numChild = self.numChild-1
      self.state = -1
    copia = Cell(self.kind,self.colour,self.deadcolour,self.minsize, self.maxsize, (self.x,self.y),self.vel,self.deadFactor,0,True)

# cantidad de comida ingerida
# comen segun velocidad de propagacion
  def eatCell(self):
    eat = self.vel*round(random(),2)
    self.size += eat
    if(self.size>=self.maxsize):
      self.size=self.minsize
      self.state=1

# van muriendo lentamente ... :(
  def deadCell(self):
    dead = self.deadFactor*round(random(),2)
    self.size -= dead
    if(self.size<=0):
      print  "celula tipo: ", self.kind,", numero: ",self.idcell," muerta !!"
      Cell.ArrayCells.remove(getIndexCell(self.idcell))

# movimiento de la celula
  def move(self):
    (self.angle, self.speed) = addVectors((self.angle, self.speed), gravity)
    self.x += math.sin(self.angle) * self.speed*self.factorMove
    self.y -= math.cos(self.angle) * self.speed*self.factorMove
    self.speed *= drag

# mostramos la celula
  def display(self,x,y,size):
    if(self.size < 2):
      pygame.draw.rect(screen, self.colour, (x, y, 2, 2))
    else:
      pygame.draw.circle(screen, self.colour, (x, y), size, self.thickness)

# sigue comida
  def followActivationPoint(self,activationPoint):
    if(self.x > activationPoint.x):
      self.x = self.x - 1
    elif(self.x < activationPoint.x):
      self.x = self.x + 1

    if(self.y > activationPoint.y):
      self.y = self.y -1
    elif(self.y < activationPoint.y):
      self.y = self.y + 1
    
    if(self.findActivationPoint(activationPoint) and self.state==2):
      self.state=0
    if(self.findActivationPoint(activationPoint) and self.state==0):
      self.caneat = True
    else:
      self.caneat = False

# maxima intensidad de onda
  def maxWave(self,nearAP,nearAPW,t):
    if(nearAP.function(self.x,self.y,t,nearAP.x,nearAP.y) > nearAPW.function(self.x,self.y,t,nearAPW.x,nearAPW.y)):
      maxWaveAP = nearAP
    else:
      maxWaveAP = nearAPW
    return maxWaveAP

# distancia minima
  def minDistance(self,nearAP,nearAPW):
    activationPoints = getAllActivationPoints()
    miniD = math.hypot(activationPoints[0].x-nearAP.x, activationPoints[0].y-nearAP.y)
    minAP = nearAP
    """
    for p in activationPoints:
      if(p==nearAP):
        continue
      d = math.hypot(p.x-nearAP.x, p.y-nearAP.y)
      if d <= miniD:
        miniD = d
        minAP = p
    """
    if(math.hypot(minAP.x-self.x, minAP.y-self.y) > math.hypot(nearAPW.x-self.x, nearAPW.y-self.y)):
      minNearAP = nearAPW
    else:
      minNearAP = minAP
    return minNearAP

# busca comida
  def findActivationPoint(self,activationPoint):
    dx = self.x - activationPoint.x
    dy = self.y - activationPoint.y
    dist = math.hypot(dx, dy)
    if dist < self.size + activationPoint.size:
#      activationPoint.consumir()
      return True
    else:
      return False

# encuentra la celula con menor distancia
  def findActivationPointNearlyWave(self,t):
    activationPoints = getAllActivationPoints()
    maxiD = activationPoints[0].function(self.x,self.y,t,activationPoints[0].x,activationPoints[0].y)
    for p in activationPoints:
      fap = p.function(self.x,self.y,t,p.x,p.y)
      if fap >= maxiD:
        maxiD = fap
        idfoo = p.idactivationpoint
    return getIndexActivationPoint(idfoo)

# encuentra la celula con menor distancia
  def findActivationPointNearly(self):
    activationPoints = getAllActivationPoints()
    miniD = math.hypot(activationPoints[0].x-self.x, activationPoints[0].y-self.y)
    for p in activationPoints:
      d = math.hypot(p.x-self.x, p.y-self.y)
      if d <= miniD:
        miniD = d
        idfoo = p.idactivationpoint
    return getIndexActivationPoint(idfoo)

# obtiene la celula que esta en el index num en el ArrayCells
def getIndexCell(num):
  cells = getAllCells()
  for cell in cells:
    if(cell.idcell==num):
      return cell
  return None

# obtiene el numero de celulas
def getNumCells():
  return len(Cell.ArrayCells)

# obtiene el array de celulas
def getAllCells():
  return Cell.ArrayCells

# obtiene el numero de celulas segun su tipo
def getTypeCells(name):
  cells = getAllCells()
  count = 0
  for cell in cells:
    if(cell.kind == name):
      count+=1
  return count

# anade los vectores direcciones luego de los choques
def addVectors((angle1, length1), (angle2, length2)):
  x  = math.sin(angle1) * length1 + math.sin(angle2) * length2
  y  = math.cos(angle1) * length1 + math.cos(angle2) * length2
  angle = 0.5 * math.pi - math.atan2(y, x)
  length  = math.hypot(x, y)
  return (angle, length)

# encuentra la celula con menor distancia
def findCell(cells, x, y):
  for p in cells:
    if math.hypot(p.x-x, p.y-y) <= p.size:
      return p
  return None

# choque entre las celulas
def collide(p1, p2):
  dx = p1.x - p2.x
  dy = p1.y - p2.y
  dist = math.hypot(dx, dy)
  if dist < p1.size + p2.size:
    tangent = math.atan2(dy, dx)
    angle = 0.5 * math.pi + tangent
    angle1 = 2*tangent - p1.angle
    angle2 = 2*tangent - p2.angle
    speed1 = p2.speed*elasticidad
    speed2 = p1.speed*elasticidad
    (p1.angle, p1.speed) = (angle1, speed1)
    (p2.angle, p2.speed) = (angle2, speed2)
    p1.x += math.sin(angle)
    p1.y -= math.cos(angle)
    p2.x -= math.sin(angle)
    p2.y += math.cos(angle)

# imprime las celulas y sus caracteristicas
def printCells(cells):
  for cell in cells:
    print "\n#########################################"
    print "#\tCelula N.",cell.idcell,": ", cell.kind,"\t#"
    print "#########################################"
    print "#\tX: ", cell.x,"\t\t\t\t#"
    print "#\tY: ", cell.y,"\t\t\t\t#"
    print "#\tarea: ", cell.area,"\t\t\t#"
    print "#\testado de vida", cell.state,"\t\t#"
    print "#########################################\n\n"

# crecen hasta ser una generacion ( 2^x )
def initGenerations(numcell):
  cellTotal = getAllCells()
  while(len(cellTotal)<(2**numcell)):
    for cell in cellTotal:
      print "\n#########################################"
      print "#\tCelula N.",cell.idcell,": ", cell.kind,"\t#"
      print "#########################################"
      print "#\tX: ", cell.x,"\t\t\t\t#"
      print "#\tY: ", cell.y,"\t\t\t\t#"
      print "#\tnumero de crias: ",cell.numChild,"\t\t#"
      print "#\tarea: ",cell.area,"\t\t\t#"
      print "#\testado: ",cell.state,"\t\t\t#"
      print "#########################################\n\n"
      cell.lifeCycleCell()

# ###########################################################
#  Funciones extras ( uso por ahora )

def divCells(cells):
  cellsaux = []
  for cell in cells:
    cellaux = cell._cellClone()
    cellsaux.append(cellaux)
  return cellsaux

def reprodCells(cells):
  cellsaux = divCells(cells)
  for cell in cellsaux:
    cells.append(cell)
  return cells

def genCells(cells,num):
  for i in range(0,num):
    cells=reprodCells(cells)
  return cells

