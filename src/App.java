import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in) ;
        String line1 = sc.nextLine() ;
        int n = Integer.parseInt(line1) ;
        tryBigInt(n);


    }

    public static void tryBigInt(int n){
        BigINT a = new BigINT(new StringBuffer(String.valueOf(n))) ;
        /* BigINT b = new BigINT (new StringBuffer("6")) ;
        BigINT c = new BigINT (new StringBuffer("1234967676767")) ;
        BigINT d = new BigINT (new StringBuffer("77777987546466")) ;  */       
        BigINT z = new BigINT(new StringBuffer("")) ;
        z.expression.append(a.factorial()) ;
		System.out.println(z);
    }

}