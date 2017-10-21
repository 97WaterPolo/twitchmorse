
int ledPin = 9;
int val = 0,output = 2555;
bool onLed = true;
String message = "Test";

const int TIME_UNIT = 100;
int DOT = TIME_UNIT;
int DASH = TIME_UNIT*3;
int SYMBOL_SPACE = TIME_UNIT;
int LETTER_SPACE = TIME_UNIT*3;
int WORD_SPACE = TIME_UNIT*7;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  //Serial.println("Hello World!");
  delay(1000); // wait a bit between buzzes
  if (Serial.available()){
    char ch = Serial.read();
    Serial.println(ch);
    if (ch == ' '){
      analogWrite(ledPin,0);
      delay(WORD_SPACE);
    }else{
        printLetter(ch);
        delay(LETTER_SPACE);
    }
  }
}

void printLetter(char c){
  c = tolower(c);
  switch(c){
    case 'a':
       playMorseCode("._");
       break;
    case 'b':
       playMorseCode("_...");
       break;
    case 'c':
       playMorseCode("_._.");
       break;
    case 'd':
       playMorseCode("_..");
       break;
    case 'e':
       playMorseCode(".");
       break;
    case 'f':
       playMorseCode(".._.");
       break;       
    case 'g':
       playMorseCode("__.");
       break;
    case 'h':
       playMorseCode("....");
       break;
    case 'i':
       playMorseCode("..");
       break;       
    case 'j':
       playMorseCode(".___");
       break;
    case 'k':
       playMorseCode("_._");
       break;
    case 'l':
       playMorseCode("._..");
       break;       
    case 'm':
       playMorseCode("__");
       break;
    case 'n':
       playMorseCode("_.");
       break;
    case 'o':
       playMorseCode("___");
       break;     
    case 'p':
       playMorseCode(".__.");
       break;
    case 'q':
       playMorseCode("__._");
       break;
    case 'r':
       playMorseCode("._.");
       break;       
    case 's':
       playMorseCode("...");
       break;
    case 't':
       playMorseCode("_");
       break;
    case 'u':
       playMorseCode(".._");
       break;       
    case 'v':
       playMorseCode("..._");
       break;
    case 'w':
       playMorseCode(".__");
       break;
    case 'x':
       playMorseCode("_.._");
       break;       
    case 'y':
       playMorseCode("_.__");
       break;
    case 'z':
       playMorseCode("__..");
       break;                   
    case '1':
       playMorseCode(".___");
       break;
    case '2':
       playMorseCode("..___");
       break;
    case '3':
       playMorseCode("...__");
       break;       
    case '4':
       playMorseCode("...._");
       break;
    case '5':
       playMorseCode(".....");
       break;
    case '6':
       playMorseCode("_....");
       break;       
    case '7':
       playMorseCode("__...");
       break;
    case '8':
       playMorseCode("___..");
       break;
    case '9':
       playMorseCode("____.");
       break;       
    case '0':
       playMorseCode("_____");
       break;                                                   
    default:
       Serial.println("Illegal Character");
  }
}

void playMorseCode(String s){
  for (int i = 0; i < s.length();i++){
    char c = s[i];
    analogWrite(ledPin,1000);
    if (c == '.'){
      delay(DOT);
    }else if (c == '_'){
      delay(DASH);
    }
    analogWrite(ledPin,0);
    delay(SYMBOL_SPACE); //Delay inbetween symbols
  }

  
}

