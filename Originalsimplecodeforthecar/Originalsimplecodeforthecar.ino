//pins for right side motors(Motor A1-front and Motor A2-back)
const int MotorA1 = 7;
const int MotorA2 = 8;

//pins for left side motors(Motor B1-front and Motor B2-back)
const int MotorB1 = 2;
const int MotorB2 = 4;

//pins for untrasonic sensors 
const int trig = 13;
const int echo = 12;

//others
long duration, distance;

void setup() {
 //Motors
 pinMode (MotorA1,OUTPUT);
 pinMode (MotorA2,OUTPUT);
 pinMode (MotorB1,OUTPUT);
 pinMode (MotorB2,OUTPUT);
 
 //sensor
 Serial.begin (9600); 
 pinMode (trig , OUTPUT);
 pinMode (echo , INPUT);
  // initially the motors and the sensor  are off
 digitalWrite(MotorA1, LOW);
 digitalWrite(MotorA2, LOW);
 digitalWrite(MotorB1, LOW);
 digitalWrite(MotorB2, LOW);


}

void reverse(){
  digitalWrite(MotorA1, LOW);
  digitalWrite(MotorA2, HIGH);
  digitalWrite(MotorB1, LOW);
  digitalWrite(MotorB2, HIGH);
}

void go(){
  digitalWrite(MotorA1, HIGH);
  digitalWrite(MotorA2, LOW);
  digitalWrite(MotorB1, HIGH);
  digitalWrite(MotorB2, LOW);
  delayMicroseconds(3000000);
}


void leftturn(){
  digitalWrite(MotorA1, HIGH);
  digitalWrite(MotorA2, LOW);
  digitalWrite(MotorB1, LOW);
  digitalWrite(MotorB2, HIGH);
  delayMicroseconds(3000000);
}

void rightturn(){
  digitalWrite(MotorA1, LOW);
  digitalWrite(MotorA2, HIGH);
  digitalWrite(MotorB1, HIGH);
  digitalWrite(MotorB2, LOW);
  delayMicroseconds(3000000);
}

void stopmove(){
  digitalWrite(MotorA1, LOW);
  digitalWrite(MotorA2, LOW);
  digitalWrite(MotorB1, LOW);
  digitalWrite(MotorB2, LOW);
  delayMicroseconds(3000000);  
}

void loop() {
  //geting signals from the sensor and giving instructions to the drive
  
  digitalWrite(trig, LOW);
  delayMicroseconds(2);
  digitalWrite(trig, HIGH);
  duration = pulseIn (echo, HIGH);
  distance = (duration/2)* 0.034; //(340 m/s * 1s/10e6 ms * 10e2cm/1m = 0.034 cm/ms) bcz sensor measures in milliseconds and we need the out put in cm
  
  if (distance <= 3){
    stopmove();
    //reverse();
    Serial.println(distance);
    //stopmove();
  }
  else if (distance <= 10 && distance > 3) {
   reverse();
   //rightturn();
   Serial.println(distance);
  }
  else 
  {
    go();
    Serial.println(distance);
  }
  
  
}
