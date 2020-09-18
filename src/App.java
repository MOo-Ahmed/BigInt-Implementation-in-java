import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /* Scanner sc = new Scanner(System.in) ;
        String line1 = sc.nextLine() ;
        String line2 = sc.nextLine() ;
        int p = Integer.parseInt(line2) ;
        int n = Integer.parseInt(line1) ;
        int result = solve(n, p);
        System.out.println(result); */
        //System.out.println((int)Math.pow(10, 5));
        tryBigInt();


    }

    public static void tryBigInt(){
        BigINT a = new BigINT(new StringBuffer("0")) ;
        BigINT b = new BigINT (new StringBuffer("22")) ;
        BigINT c = new BigINT (new StringBuffer("1234967676767")) ;
        BigINT d = new BigINT (new StringBuffer("77777987546466")) ;        
        BigINT z = new BigINT(new StringBuffer("")) ;
        z.expression.append((a.multiply(b)).expression) ;
		System.out.println("Result = " + z);
    }

}