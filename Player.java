/******************************************************************************
 *  Compilation:  javac Player.java
 *  Execution:    java Player
 *
 *  Has code to create a player object, and a tostring method.
 *
 *  Jackson Evans
 ******************************************************************************/

public class Player{
  //player class

  //attributes
  int rank;
  String name;
  String pos;
  int age;
  String Tm;
  double G;
  double GS;
  double MP;
  double FG;
  double FGA;
  double FGp;
  double threep;
  double threepav;
  double threepp;
  double twop;
  double twopav;
  double twopp;
  double eFG;
  double FT;
  double FTA;
  double FTAp;
  double ORB;
  double DRB;
  double TRB;
  double AST;
  double STL;
  double BLK;
  double TOV;
  double PF;
  double PTS;

  public Player(String[] arr){
    //player constructor
                  rank=Integer.parseInt(arr[0]);
                  name = arr[1].split("\\\\")[0];
                  pos = arr[2];
                  age=Integer.parseInt(arr[3]);
                  Tm=arr[4];
                  G=Double.parseDouble(arr[5]);
                  GS=Double.parseDouble(arr[6]);
                  MP=Double.parseDouble(arr[7]);
                  FG=Double.parseDouble(arr[8]);
                  FGA=Double.parseDouble(arr[9]);
                  FGp=Double.parseDouble(arr[10]);
                  threep=Double.parseDouble(arr[11]);
                  threepav=Double.parseDouble(arr[12]);
                  threepp=Double.parseDouble(arr[13]);
                  twop=Double.parseDouble(arr[14]);
                  twopav=Double.parseDouble(arr[15]);
                  twopp=Double.parseDouble(arr[16]);
                  eFG=Double.parseDouble(arr[17]);
                  FT=Double.parseDouble(arr[18]);
                  FTA=Double.parseDouble(arr[19]);
                  FTAp=Double.parseDouble(arr[20]);
                  ORB=Double.parseDouble(arr[21]);
                  DRB=Double.parseDouble(arr[22]);
                  TRB=Double.parseDouble(arr[23]);
                  AST=Double.parseDouble(arr[24]);
                  STL=Double.parseDouble(arr[25]);
                  BLK=Double.parseDouble(arr[26]);
                  TOV=Double.parseDouble(arr[27]);
                  PF=Double.parseDouble(arr[28]);
                  PTS=Double.parseDouble(arr[29]);
                }

    public String toString(){
      //tostring method
      return(String.format("%s FGp: %f, 3PTp: %f, FTAp: %f, AST: %f, TRB: %f, STL: %f, BLK: %f, PTS: %f\n", name, FGp, threepp, FTAp, AST, TRB, STL, BLK, PTS));
    }
}
