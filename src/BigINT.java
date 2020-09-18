import java.util.ArrayList;

// subtract still has some problems
public class BigINT {
    StringBuffer expression ;
	int length ;

	public BigINT(StringBuffer num) {
		expression = new StringBuffer("0");
		expression.setLength(0);
		expression.append(num);
		length = expression.length() ;
	}
	public BigINT () {
		
	}
    
    public BigINT add (BigINT num2) {
		StringBuffer result = new StringBuffer("") ;
		expression.reverse();
		num2.expression.reverse ();
		int miniLen = Math.min(expression.length(), num2.expression.length()) ;
		int remainder = 0 , sum = 0 ;
		//System.out.println("minilen = " + miniLen);
		//System.out.println("len1 = " + this.expression.length() + "  -  len2 = " + num2.length);
		int n = Math.max (expression.length(), num2.expression.length() ) ;
		for(int i = 0 ; i < n ; i++) {
			if(i < miniLen) {
				sum += (int)(expression.charAt(i) - '0')  + (int)(num2.expression.charAt(i) - '0' );
			}
			else {
				if(this.expression.length()  > num2.expression.length() ) {
					sum += (int)(expression.charAt(i) - '0')  ;
				}
				else {
					sum += (int)(num2.expression.charAt(i) - '0')  ;
				}
			}
			remainder = sum % 10 ;
			result = result.append(remainder) ;
            if(sum > 10){ 
                // in this case there's a value to send to the next digit if any
                sum /= 10 ; //sum = remainder ;  
            }
			else if (sum % 10 == 0)	sum /= 10 ;
			else sum = 0 ;
			remainder = 0 ;
			if((i+1) == n && sum > 0){
				result = result.append(sum) ;
			}
		}
		expression.reverse() ;
		num2.expression.reverse() ;
		result.reverse() ;
        BigINT output = new BigINT(result) ;
        //System.out.println(result.toString());
		output.clean();
		return output ;
	}
	
	/* 
    public BigINT subtract (BigINT num2) {
		StringBuffer result = new StringBuffer("") ;
		boolean isNegative = false ;
		if(length < num2.length)	isNegative = true ;
		expression.reverse();
		num2.expression.reverse ();
		int miniLen = Math.min(expression.length(), num2.length) ;
		int sum = 0 ;
		for(int i = 0 ; i < Math.max (this.length, num2.length) ; i++) {
			if(i < miniLen) {
				int x = (int)(expression.charAt(i) - '0'), y = (int)(num2.expression.charAt(i) - '0' ) ;
				if(x < y)	{
					if(length > i+1 ) { // if length admits borrowing 10 from beside
						String newValue = String.valueOf((int)(expression.charAt(i) - '0') - 1 ) ;
						expression.replace(i + 1, i + 1, newValue) ;
						sum = x - y + 10 ;
					}
					else {
						sum = Math.abs( x  - y );
					}
					isNegative = true ;
				}
				else	sum = x - y ;
				result = result.append(sum) ;
			}
			else {
				if(this.length > num2.length) {
					sum = (int)(expression.charAt(i) - '0')  ;
					result = result.append(sum) ;
					sum = 0 ;
				}
				else {
					sum = (int)(num2.expression.charAt(i) - '0')  ;
					result = result.append(sum) ;
				}
			}
			sum = 0 ;
		}
		expression.reverse() ;
		num2.expression.reverse() ;
		if(isNegative)	result = result.append('-') ;
		result.reverse() ;
		BigINT output = new BigINT(result) ;
		output.clean();
		return output ;
    }	
     */
	
	 @Override
	public String toString() {
		return this.expression.toString();
		
	}
    
    public BigINT multiply (BigINT num2) {
		BigINT result = new BigINT (new StringBuffer("0")) ;
		String n1 = this.expression.toString() ;
		String n2 = num2.expression.toString() ;
		if(n1 == "0" || n2 == "0")	return result ;
        ArrayList<BigINT> list1 = divideNumber() ;
		ArrayList<BigINT> list2 =  num2.divideNumber() ;
		//System.out.println("\t" + list2);
		for(int i = 0 ; i < list1.size() ; i++){
            for(int j = 0 ; j < list2.size() ; j++){
                //System.out.println("i = " + i + " - j = " + j);
				//System.out.println("Before :  " + list1.get(i).expression + "  -  " + list2.get(j).expression);
				//System.out.println("\t" + list2);
				StringBuffer exp1 = new  StringBuffer();
				exp1.append(list1.get(i).expression);
				StringBuffer exp2 = new  StringBuffer() ;
				exp2.append(list2.get(j).expression);
                BigINT sum = list1.get(i).MakeSimpleMultiplication(list2.get(j)) ;
				//System.out.println("Sum = " + sum);
				list1.get(i).expression.setLength(0);
				list1.get(i).expression.append(exp1) ;
                list2.get(j).expression.setLength(0);
				list2.get(j).expression.append(exp2) ;
                //System.out.println("After :  " + list1.get(i).expression + "/" + exp1 + "  -  " + list2.get(j).expression + "/" + exp2);

				StringBuffer tempResult = new StringBuffer();
				tempResult.append(result.expression) ;
				result.expression.setLength(0);
				BigINT tempSum = new BigINT(new StringBuffer("0"));
				tempSum.expression.setLength(0);
				tempSum.expression.append(sum.add(new BigINT(tempResult)).expression) ;
				result.expression.append(tempSum.expression) ;
                //System.out.println("Result = " + result);
            }
        }
		return result ;
    }

    public BigINT MakeSimpleMultiplication(BigINT num2){
		//System.out.println("When it got in : " + num2);
        BigINT tempNumber = new BigINT(new StringBuffer("")) ;
        int numOfZeros1 = this.countNumberOfConsecutiveZeros() ;
        int numOfZeros2 = num2.countNumberOfConsecutiveZeros() ;
        eliminateZeros(numOfZeros1);
        num2.eliminateZeros(numOfZeros2);
        int numOfZeros = numOfZeros1 + numOfZeros2 ;
        for(int i = 0 ; i < numOfZeros ; i++) {   
            tempNumber.expression.append("0") ;
		}
		//System.out.println("exp1 = " + this + " -  exp2 = " + num2);
        int n1 = Integer.parseInt(this.expression.toString()) ;
        int n2 = Integer.parseInt(num2.expression.toString()) ;
        int result = n1 * n2 ;
        tempNumber.expression.insert(0, result) ;

        return tempNumber ;
    }

    public void eliminateZeros (int n){
		if(expression.length() == 1)	return ;
        this.expression.reverse() ;
        this.expression.delete(0, n) ;
        this.expression.reverse() ;
    }
    
    public ArrayList<BigINT> divideNumber(){
        // Assuming the number isn't reversed
        ArrayList<BigINT> NumbersList = new ArrayList();
        int NumberLength = this.expression.length();
        for(int i = 0 ; i < expression.length() ; i++){
            int digit = (int)( expression.charAt(i) - '0')  ;
            String numberExpression = String.valueOf(digit);
            for(int j = 0 ; j < NumberLength-1 ; j++){
                numberExpression = numberExpression.concat("0") ; 
            }
            BigINT tempNumber = new BigINT(new StringBuffer(numberExpression)) ;
            NumbersList.add(tempNumber);
            NumberLength-- ;
        }

        return NumbersList;
    }
    
    public int countNumberOfConsecutiveZeros(){
		String val = expression.toString() ;
		if(val.endsWith("0") && val.startsWith("0"))	return 0 ;
        this.expression.reverse() ;
        int count = 0 ;
        for (int i = 0 ; i < this.expression.length(); i++){
            int digit = (int)( expression.charAt(i) - '0')  ;
            if(digit == 0){
                count++ ;
            }
            else{
                break ;
            }
        }
        this.expression.reverse() ;
        return count ;
    }

    public BigINT factorial () {
		BigINT result = new BigINT(new StringBuffer(""));
		int value = Integer.parseInt(this.expression.toString()) ;
		if(value <= 9){
			result.expression.setLength(0);
			result.expression.append(simpleFactorial().expression) ;
			return result; 
		}
		else{
			int value2 = value-1 ;
			result.expression.setLength(0);
			result.expression.append(String.valueOf(value2)) ;
			return this.multiply(result.factorial()) ;
		}
	}

	private BigINT simpleFactorial(){
		BigINT result = new BigINT(new StringBuffer(""));
		int value = Integer.parseInt(expression.toString()) ;
		int smallFactorial = measureFactorial(value) ;
		result.expression.setLength(0);
		result.expression.append(String.valueOf(smallFactorial)) ;
		return result ;
	}

	private int measureFactorial(int n){
		if(n==1)	return 1 ;
		else	return n * measureFactorial(n-1) ;
	}
    
    private void clean() {
		if (this.expression.charAt(0) == '0' && this.expression.length() > 1) {
			this.expression = expression.deleteCharAt(0) ;
		}
	}
	
}
