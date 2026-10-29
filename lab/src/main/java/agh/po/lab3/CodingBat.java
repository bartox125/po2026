package agh.po.lab3;

public class CodingBat {
    public String missingChar(String str, int n) {
        String a = str.substring(0,n);
        String b = str.substring(n+1,str.length());
        return a+b;
    }
    public boolean monkeyTrouble(boolean aSmile, boolean bSmile){
        if(aSmile & bSmile){
            return true;
        } else if (!aSmile & !bSmile) {
            return true;
        }else{
            return false;
        }
    }
    public int countEvens(int[] nums) {
        int even=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]%2==0){
                even++;
            }
        }
        return even;
    }
    public String right2(String str) {
        String lastTwo = str.substring(str.length()-2,str.length());
        String withoutTwo = str.substring(0,str.length()-2);
        return lastTwo+withoutTwo;
    }
    public static void main(String[] args){
        CodingBat codingBat = new CodingBat();
        System.out.println(codingBat.missingChar("kitten",1));
        System.out.println(codingBat.right2("java"));
        System.out.println(codingBat.countEvens(new int[]{1,2,3,4,5,6}));
        System.out.println(codingBat.monkeyTrouble(true,true));
    }

}
